package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.BillingResult;
import com.adms.batch.sales.domain.BillingStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.DateUtil;
import com.adms.utils.Logger;

public class ImportMtiBilling extends AbstractImportSalesJob {

	private Map<String, DataHolder> saleDataMap;
	
	private BillingResult extractRecord(DataHolder dataHolder, BillingResult billingResult) throws Exception {
//		log.debug("extractRecord " + dataHolder.printValues());
		DataHolder saleDataHolder = saleDataMap.get(dataHolder.get("policyId").getStringValue());
		log.debug("extractRecord: " + saleDataHolder.printValues());
		
		billingResult.setAccountNo(dataHolder.get("accountNo").getStringValue());
//		billingResult.setAccountExp(dataHolder.get("accountExp").getStringValue());
		billingResult.setPremium(dataHolder.get("premium").getDecimalValue());
		billingResult.setFirstPremium(dataHolder.get("premium").getDecimalValue());

		billingResult.setPaymentFrequency(billingResult.getxReference().getPaymentFrequency());

		Date billingDate = DateUtil.convStringToDate("yyyyMMdd", dataHolder.get("collectionDate").getStringValue());
		billingResult.setBillingDate(billingDate);
		
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

//		String remarkString = dataHolder.get("remark").getStringValue();
//		billingResult.setRemark(remarkString);

		return billingResult;
	}

	private void importDataHolder(DataHolder dataHolder, Sales sales) throws Exception
	{
		Date collectionDate = DateUtil.convStringToDate("yyyyMMdd", dataHolder.get("collectionDate").getStringValue());
		if(collectionDate.after(new Date())) {
			log.error("Billing Date is beyond today:: " + DateUtil.convDateToString("dd-MM-yyyy", collectionDate) 
			+ " xRef: " + dataHolder.get("xRef").getStringValue());
			return;
		}
		
		BillingResult billingResult = getBillingResultService().findBillingResultByxRefAndBillingDate(sales.getxReference(), collectionDate);

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

	private void importDataHolderList(List<DataHolder> dataHolderList) throws Exception
	{
		for (DataHolder dataHolder : dataHolderList)
		{
			String policyId = dataHolder.get("policyId").getStringValue();
			
			if(StringUtils.isNotBlank(policyId)) {
				DataHolder saleDataHolder = saleDataMap.get(policyId);
				String xReference = saleDataHolder.get("keyCode").getStringValue() + saleDataHolder.get("uniqueId").getStringValue();
				
				Sales sales = getSalesService().findSalesRecordByXRefference(xReference);
				log.debug("xReference: " + xReference + ", sales: " + sales);
				
				if(sales != null) {
					importDataHolder(dataHolder, sales);
					
					if (StringUtils.isNotBlank(sales.getxReferenceNew()))
					{
						sales = getSalesService().findSalesRecordByXRefference(sales.getxReferenceNew());

						if (sales != null)
						{
							importDataHolder(dataHolder, sales);
						}
					} else {
						log.warn("sales record not found [xReference: " + xReference + "]");
					}
				}
			}
		}
	}

	private void importFile(String fileFormatFileName, String dataFileLocation)
			throws Exception
	{
		log.info("importFile: " + dataFileLocation);
		InputStream format = null;
		InputStream input = null;
		try
		{
			format = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileFormatFileName);
			ExcelFormat excelFormat = new ExcelFormat(format);

			input = new FileInputStream(dataFileLocation);
			DataHolder fileDataHolder = excelFormat.readExcel(input);
			
			//data is at first sheet
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
	
	public void createSaleDataMapper(String fileFormatName, String dataFileLocation) throws Exception {
		log.info("import sale file: " + dataFileLocation);
		
		InputStream format = null;
		InputStream input = null;
		try {
			format = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileFormatName);
			ExcelFormat excelFormat = new ExcelFormat(format);
			
			input = new FileInputStream(dataFileLocation);
			DataHolder fileDataHolder = excelFormat.readExcel(input);
			
			DataHolder sheetHolder = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0));
			List<DataHolder> dataHolderList = sheetHolder.getDataList("dataRecords");
			
			if(this.saleDataMap == null) {
				saleDataMap = new HashMap<String, DataHolder>();
			}
			
			for(DataHolder data : dataHolderList) {
				if(data.get("status").getStringValue().equals("Y")) {
					saleDataMap.put(data.get("policyNo").getStringValue(), data);
				}
			}
			
		} catch(Exception e) {
			throw e;
		} finally {
			try {format.close();} catch(Exception e) {}
			try {input.close();} catch(Exception e) {}
		}
	}

	public static final String CONFIG_FILE_LOCATION = "config/importSales.properties";

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatSaleReport = "fileformat/salesdb/FileFormat_MTI_Sale_Report.xml";
		String fileFormatBilling = "fileformat/salesdb/FileFormat_MTI_Billing_Result.xml";
		String rootPath = args[0];
		String logFilePath = args[1];
		
		FileWalker fwSale = new FileWalker();
		fwSale.walk(rootPath, new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") 
						&& !dir.getAbsolutePath().toLowerCase().contains("archive") 
						&& name.toLowerCase().contains(".xls")
						&& (name.contains("Sales report"));
			}
		});
		
		FileWalker fwBilling = new FileWalker();
		fwBilling.walk(rootPath, new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") 
						&& !dir.getAbsolutePath().toLowerCase().contains("archive") 
						&& name.toLowerCase().contains(".xls")
						&& name.toLowerCase().contains("billing");
			}
		});
		
		ImportMtiBilling batch = new ImportMtiBilling();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());
		batch.setLogFileName(logFilePath);
		
		for(String filename : fwSale.getFileList()) {
			batch.createSaleDataMapper(fileFormatSaleReport, filename);
		}
		
		for(String filename : fwBilling.getFileList()) {
			batch.importFile(fileFormatBilling, filename);
		}
	}
}
