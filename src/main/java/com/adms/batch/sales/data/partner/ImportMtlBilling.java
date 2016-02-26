package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.BillingResult;
import com.adms.batch.sales.domain.BillingStatus;
import com.adms.batch.sales.domain.PaymentFrequency;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.DateUtil;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportMtlBilling extends AbstractImportSalesJob {

	private BillingResult extractRecord(DataHolder dataHolder, BillingResult billingResult)
			throws Exception
	{
		log.debug("extractRecord " + dataHolder.printValues());

		billingResult.setAccountNo(dataHolder.get("accountNo").getStringValue());
		billingResult.setAccountExp(dataHolder.get("accountExp").getStringValue());
		billingResult.setPremium(dataHolder.get("premium").getDecimalValue());
		billingResult.setFirstPremium(dataHolder.get("firstPremium").getDecimalValue());

		String paymentFrequencyString = dataHolder.get("paymentMode").getStringValue();
		if (StringUtils.isNotBlank(paymentFrequencyString))
		{
			PaymentFrequency paymentFrequency = getPaymentFrequencyService().findPaymentFrequencyByFrequencyName(paymentFrequencyString);
			if (paymentFrequency == null)
			{
				throw new Exception("PaymentFrequency not found for paymentFrequencyDescription [" + paymentFrequencyString + "]");
			}
			billingResult.setPaymentFrequency(paymentFrequency);
		}

		billingResult.setBillingDate((Date) dataHolder.get("effDate").getValue());

		String remarkString = dataHolder.get("remark").getStringValue();
		String billingStatusString = null;
		
		billingStatusString = isPaidSheet ? "Paid" : "Reject";
//		if (StringUtils.isBlank(remarkString))
//		{
//			billingStatusString = "Paid";
//		}
//		else
//		{
//			billingStatusString = "Reject";
//		}

		if (StringUtils.isNotBlank(billingStatusString))
		{
			BillingStatus billingStatus = getBillingStatusService().findBillingStatusByStatus(billingStatusString);
			if (billingStatus == null)
			{
				throw new Exception("BillingStatus not found for billingStatusString [" + billingStatusString + "]");
			}
			if(billingResult.getBillingStatus() == null
					|| (billingResult.getBillingStatus() != null && billingResult.getBillingStatus().getBillingStatus().equals("Reject"))) {
				billingResult.setBillingStatus(billingStatus);
			}
		}

		billingResult.setRemark(remarkString);

		return billingResult;
	}

	private void importDataHolder(DataHolder dataHolder, Sales sales)
			throws Exception
	{
		Date billingDate = (Date) dataHolder.get("effDate").getValue();
		//Edited: Need to check, is billing date beyond today?
		if(billingDate.after(new Date())) {
			log.error("Billing Date is beyond today:: " + DateUtil.convDateToString("dd-MM-yyyy", billingDate) 
			+ " xRef: " + dataHolder.get("xRef").getStringValue());
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
//				billingResult.setCreateBy(BATCH_ID);
//				billingResult.setCreateDate(new Date());
				getBillingResultService().addBillingResult(billingResult, BATCH_ID);
			}
			else
			{
//				billingResult.setUpdateBy(BATCH_ID);
//				billingResult.setUpdateDate(new Date());
				getBillingResultService().updateBillingResult(billingResult, BATCH_ID);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void importDataHolderList(List<DataHolder> dataHolderList)
			throws Exception
	{
		for (DataHolder dataHolder : dataHolderList)
		{
			String xReference = dataHolder.get("xRef").getStringValue();
			
			if (StringUtils.isNotBlank(xReference))
			{
				Sales sales = getSalesService().findSalesRecordByXRefference(xReference);
				log.debug("xReference: " + xReference + ", sales: " + sales);

				if (sales != null)
				{
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
				else
				{
					log.warn("sales record not found [xReference: " + xReference + "]");
				}
			}
		}
	}
	
	private boolean isPaidSheet;

	private void importFile(String fileFormatFileName, String dataFileLocation)
			throws Exception
	{
		log.info("importFile: " + dataFileLocation);
		InputStream format = null;
		InputStream input = null;
		try
		{
			format = URLClassLoader.getSystemResourceAsStream(fileFormatFileName);
			ExcelFormat excelFormat = new ExcelFormat(format);

			input = new FileInputStream(dataFileLocation);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<String> sheetNames = fileDataHolder.getKeyList();

			for (String sheetName : sheetNames)
			{
				isPaidSheet = sheetName.toLowerCase().contains("paid") ? true : false;
				DataHolder sheet = fileDataHolder.get(sheetName);

				List<DataHolder> dataHolderList = sheet.getDataList("dataRecords");

				importDataHolderList(dataHolderList);
			}
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
	public static final String LOG_FILE_NAME = "log.mtlBilling.file.name";

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatFileName = "fileformat/salesdb/FileFormat_MTL_Result_1st_Billing.xml";
		String rootPath = args[0] /* "D:/Work/Report/DailyReport/MTL_Billing" */;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && name.contains(".xls");
			}
		});

		ImportMtlBilling batch = new ImportMtlBilling();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());
		String logFileName = PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(LOG_FILE_NAME);
		batch.setLogFileName(logFileName);

		for (String filename : fw.getFileList())
		{
			batch.importFile(fileFormatFileName, filename);
		}
	}
}
