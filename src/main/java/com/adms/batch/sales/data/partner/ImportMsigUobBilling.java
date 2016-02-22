package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.BillingResult;
import com.adms.batch.sales.domain.BillingStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.DateUtil;
import com.adms.utils.FileUtil;
import com.adms.utils.Logger;

public class ImportMsigUobBilling extends AbstractImportSalesJob {
	
	private String excelPwd;
	
	public void setExcelPwd(String excelPwd) {
		this.excelPwd = excelPwd;
	}

	private BillingResult extractRecord(DataHolder dataHolder, BillingResult billingResult)
			throws Exception
	{
		log.debug("extractRecord " + dataHolder.printValues());

//		billingResult.setAccountNo(dataHolder.get("accountNo").getStringValue());
//		billingResult.setAccountExp(dataHolder.get("accountExp").getStringValue());
		billingResult.setPremium(dataHolder.get("premium").getDecimalValue());
		billingResult.setFirstPremium(dataHolder.get("premium").getDecimalValue());

		billingResult.setPaymentFrequency(billingResult.getxReference().getPaymentFrequency());

		//'Paid' because we import only Paid data.
		String billingStatusString = "Paid";

		if (StringUtils.isNotBlank(billingStatusString))
		{
			BillingStatus billingStatus = getBillingStatusService().findBillingStatusByStatus(billingStatusString);
			if (billingStatus == null)
			{
				throw new Exception("BillingStatus not found for billingStatusString [" + billingStatusString + "]");
			}
			billingResult.setBillingStatus(billingStatus);
		}
		billingResult.setBillingDate((Date) dataHolder.get("billingDate").getValue());

		return billingResult;
	}

	private void importDataHolder(DataHolder dataHolder, Sales sales) throws Exception
	{
		Date billingDate = (Date) dataHolder.get("billingDate").getValue();
		//Edited need to check, is billing date beyond today?
		if(billingDate.after(new Date())) {
			log.error("Billing Date is beyond today:: " + DateUtil.convDateToString("dd-MM-yyyy", billingDate) 
					+ " pol1: " + dataHolder.get("policy1").getStringValue() 
					+ ", pol2: " + dataHolder.get("policy2").getStringValue());
			addUnmappedData(dataHolder, "Billing Date is beyond current Date.");
			return;
		}
		
		BillingResult billingResult = getBillingResultService().findBillingResultByxRefAndBillingDate(sales.getxReference(), billingDate);

		boolean newBillingResult = false;
		if (billingResult == null)
		{
			billingResult = new BillingResult();
			billingResult.setxReference(sales);
			newBillingResult = true;
		}
		else
		{
			log.info("found billingResult record [" + billingResult.getxReference().getxReference() 
			+ " , billingDate: " + DateUtil.convDateToString("yyyy-MM-dd", billingResult.getBillingDate()) + "]");
		}

		try
		{
			extractRecord(dataHolder, billingResult);

			if (newBillingResult)
			{
				getBillingResultService().addBillingResult(billingResult, BATCH_ID);
			}
			else
			{
				getBillingResultService().updateBillingResult(billingResult, BATCH_ID);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private class UnmappedHolder {
		private DataHolder dataHolder;
		private String remark;
		
		public UnmappedHolder(DataHolder dataHolder, String remark) {
			this.dataHolder = dataHolder;
			this.remark = remark;
		}
		
		public DataHolder getDataHolder() {
			return dataHolder;
		}
		
		public String getRemark() {
			return remark;
		}
	}
	
	private List<UnmappedHolder> unmappedData;
	
	private void addUnmappedData(DataHolder dataHolder, String remark) {
		if(unmappedData == null) {
			unmappedData = new ArrayList<UnmappedHolder>();
		}
		unmappedData.add(new UnmappedHolder(dataHolder, remark));
	}

	private void importDataHolderList(List<DataHolder> dataHolderList) throws Exception
	{
		for (DataHolder dataHolder : dataHolderList)
		{
			
			String result = dataHolder.get("result") != null ? dataHolder.get("result").getStringValue() : null;
			if(StringUtils.isNotBlank(result) && result.toUpperCase().equals("SCS")) {
				String cFirstName = dataHolder.get("customerFirstName").getStringValue().trim();
				String cLastName = dataHolder.get("customerLastName").getStringValue().trim();
				
				DetachedCriteria findByName = DetachedCriteria.forClass(Sales.class);
				findByName.add(Restrictions.like("customerFullName", "%" + cFirstName + " " + cLastName));
				
				List<Sales> salesList = getSalesService().findByCriteria(findByName);
				
				if(salesList != null && !salesList.isEmpty()) {
//					BigDecimal premium = dataHolder.get("premium").getDecimalValue();
					String premStr = dataHolder.get("premium").getStringValue();
					BigDecimal premium = new BigDecimal(premStr.substring(0, premStr.length() - 2) + "." + premStr.substring(premStr.length() - 2, premStr.length()));
					List<Integer> foundPosition = new ArrayList<Integer>();
					int n = 0;
					for(Sales sales : salesList) {
						if(premium.setScale(2, BigDecimal.ROUND_HALF_UP)
								.compareTo(sales.getPremium().setScale(2, BigDecimal.ROUND_HALF_UP)) == 0) {
							foundPosition.add(n);
						}
						n++;
					}
					
					if(foundPosition.isEmpty()) {
						addUnmappedData(dataHolder, "Customer founded, but couldn't matched by premium.");
					} else if(foundPosition.size() > 1) {
						addUnmappedData(dataHolder, "Customer founded, but too much matched records by premium.");
					} else {
						Sales sales = salesList.get(foundPosition.get(0));
						importDataHolder(dataHolder, sales);

						if (StringUtils.isNotBlank(sales.getxReferenceNew()))
						{
							sales = getSalesService().findSalesRecordByXRefference(sales.getxReferenceNew());

							if (sales != null)
							{
								importDataHolder(dataHolder, sales);
							}
						}
					}
					
				} else {
					addUnmappedData(dataHolder, "Customer not found.");
				}
			}
			
		}
	}

	private void exportUnmappedData(String rootPath) {
		if(unmappedData == null || unmappedData.isEmpty()) {
			log.info("No Unmapped Data.");
		} else {
			log.info("Found Unmapped Data. Start export report...");
			String fileName = "unmapped_data_{datetime}.xlsx";
			String outFileName = rootPath + File.separator + "unmapped" + File.separator;
			File file = null;
			Date date = new Date();
			
			FileUtil.getInstance().createDirectory(outFileName);
			
			fileName = fileName.replace("{datetime}", DateUtil.convDateToString("yyyyMMddHHmm", date));
			file = new File(outFileName + File.separator + fileName);
			
			try {
				file.createNewFile();
			} catch(Exception e) {
				log.error(e.getMessage());
			}
			
			Workbook wb = null;
			try {
				wb = new XSSFWorkbook();
				Sheet sheet = wb.createSheet("Data");
				int r = 1;
				CellStyle cellStyle = wb.createCellStyle();
				CreationHelper helper = wb.getCreationHelper();
				cellStyle.setDataFormat(helper.createDataFormat().getFormat("dd/MM/yyyy"));
				
				Row row = sheet.createRow(0);
				row.createCell(0).setCellValue("billing date");
				row.createCell(1).setCellValue("prem");
				row.createCell(2).setCellValue("pol_1");
				row.createCell(3).setCellValue("pol_2");
				row.createCell(4).setCellValue("result");
				row.createCell(5).setCellValue("CLIENT NAME");
				row.createCell(6).setCellValue("CLIENT SURNAME");
				row.createCell(7).setCellValue("Remark");
				
				for(UnmappedHolder u : unmappedData) {
					DataHolder dataHolder = u.getDataHolder();
					String remark = u.getRemark();
					
					row = sheet.createRow(r);
					Cell cell = row.createCell(0);
					cell.setCellValue((Date) dataHolder.get("billingDate").getValue());
					cell.setCellStyle(cellStyle);
					
					row.createCell(1).setCellValue(dataHolder.get("premium").getDecimalValue().doubleValue());
					row.createCell(2).setCellValue(dataHolder.get("policy1").getStringValue());
					row.createCell(3).setCellValue(dataHolder.get("policy2").getStringValue());
					row.createCell(4).setCellValue(dataHolder.get("result").getStringValue());
					row.createCell(5).setCellValue(dataHolder.get("customerFirstName").getStringValue());
					row.createCell(6).setCellValue(dataHolder.get("customerLastName").getStringValue());
					row.createCell(7).setCellValue(remark);
					
					r++;
				}
				
				wb.write(new FileOutputStream(file));
			} catch(Exception e) {
				log.error(e.getMessage());
			} finally {
				try { wb.close();} catch(Exception e) {}
			}
		}
	}
	
	private void importFiles(String fileFormatFileName, List<String> dataFileLocation) throws Exception {
		for (String filename : dataFileLocation)
		{
			importFile(fileFormatFileName, filename);
		}
	}
	
	private void importFile(String fileFormatFileName, String dataFileLocation) throws Exception
	{
		log.info("importFile: " + dataFileLocation);
		InputStream format = null;
		InputStream input = null;
		try
		{
			format = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileFormatFileName);
			ExcelFormat excelFormat = new ExcelFormat(format);
			
			input = new FileInputStream(dataFileLocation);
			log.info("decrypt document with password: " + this.excelPwd);
			DataHolder fileDataHolder = excelFormat.readExcel(input, this.excelPwd);
			
			DataHolder sheetHolder = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0));
			List<DataHolder> dataHolderList = sheetHolder.getDataList("dataRecords");
			
			importDataHolderList(dataHolderList);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				format.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	public static final String CONFIG_FILE_LOCATION = "config/importSales.properties";

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatFileName = "fileformat/salesdb/FileFormat_MSIG_UOB_Billing_Result.xml";
		String rootPath = args[0];
		String logFilePath = args[1];
		String excelPwd = args[2];

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$")
						&& !dir.getAbsolutePath().toLowerCase().contains("archive")
						&& !name.contains("unmapped_")
						&& name.toLowerCase().contains(".xls");
			}
		});

		ImportMsigUobBilling batch = new ImportMsigUobBilling();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());
		batch.setLogFileName(logFilePath);
		batch.setExcelPwd(excelPwd);

		batch.importFiles(fileFormatFileName, fw.getFileList());
		batch.exportUnmappedData(rootPath);
	}
}
