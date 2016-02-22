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
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.UwDecision;
import com.adms.batch.sales.domain.UwResult;
import com.adms.batch.sales.domain.UwStatus;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportMtlPendingPreUw extends AbstractImportSalesJob {

	private UwResult extractUwRecord(DataHolder uwDataHolder, UwResult uwResult)
			throws Exception
	{
		log.debug("extractUwRecord " + uwDataHolder.printValues());

		uwResult.setAppSubmitDate((Date) uwDataHolder.get("appSubmitDate").getValue());
		uwResult.setItemNo(uwDataHolder.get("itemNo").getIntValue());
		uwResult.setUwSubmitDate((Date) uwDataHolder.get("uwSubmitDate").getValue());
		uwResult.setUwResultDate((Date) uwDataHolder.get("uwResultDate").getValue());

		String acceptFlag = uwDataHolder.get("acceptFlag").getStringValue();
		String declineFlag = uwDataHolder.get("declineFlag").getStringValue();
		String cancelFlag = uwDataHolder.get("cancelFlag").getStringValue();
		String uwDecisionString = null;

		if (StringUtils.isNotBlank(acceptFlag))
		{
			uwDecisionString = "Accept";
		}
		else if (StringUtils.isNotBlank(declineFlag))
		{
			uwDecisionString = "Decline";
		}
		else if (StringUtils.isNotBlank(cancelFlag))
		{
			uwDecisionString = "Cancel";
		}

		if (StringUtils.isNotBlank(uwDecisionString))
		{
			UwDecision uwDecision = getUwDecisionService().findUwDecisionByDecision(uwDecisionString);
			if (uwDecision == null)
			{
				throw new Exception("UW Decision not found for uwDecisionString [" + uwDecisionString + "]");
			}
			uwResult.setUwDecision(uwDecision);
		}

		String uwStatusString = uwDataHolder.get("uwStatus").getStringValue();
		if (StringUtils.isNotBlank(uwStatusString))
		{
			UwStatus uwStatus = getUwStatusService().findUwStatusByDescription(uwStatusString);
			if (uwStatus == null)
			{
				throw new Exception("UW Status not found for uwStatusString [" + uwStatusString + "]");
			}
			uwResult.setUwStatus(uwStatus);
		}

		uwResult.setUwRemark(uwDataHolder.get("remark").getStringValue());

		return uwResult;
	}

	private void importDataHolder(DataHolder uwDataHolder, Sales sales)
			throws Exception
	{
		log.debug("sales: " + sales);

		UwResult uwResult = getUwResultService().findUwResultByxReference(sales.getxReference());

		boolean newUwResult = false;
		if (uwResult == null)
		{
			uwResult = new UwResult();
			uwResult.setxReference(sales);
			newUwResult = true;
		}
		else
		{
			log.debug("found uwResult record [" + uwResult + "]");
		}

		try
		{
			extractUwRecord(uwDataHolder, uwResult);

			if (newUwResult)
			{
//				uwResult.setCreateBy(BATCH_ID);
//				uwResult.setCreateDate(new Date());
				getUwResultService().addUwResult(uwResult, BATCH_ID);
			}
			else
			{
//				uwResult.setUpdateBy(BATCH_ID);
//				uwResult.setUpdateDate(new Date());
				getUwResultService().updateUwResult(uwResult, BATCH_ID);
			}
		}
		catch (Exception e)
		{
			log.error("Error while add/update xRef: " + uwDataHolder.get("xRef").getStringValue());
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	private void importUw(List<DataHolder> uwDataHolderList)
			throws Exception
	{
		log.debug("uwDataHolderList size: " + uwDataHolderList.size());
		for (DataHolder uwDataHolder : uwDataHolderList)
		{
			String xReference = uwDataHolder.get("xRef").getStringValue();
			log.debug("xReference: " + xReference);
			
			Sales sales = getSalesService().findSalesRecordByXRefference(xReference);

			if (sales != null)
			{
				importDataHolder(uwDataHolder, sales);
				
				if (StringUtils.isNotBlank(sales.getxReferenceNew()))
				{
					sales = getSalesService().findSalesRecordByXRefference(sales.getxReferenceNew());
					importDataHolder(uwDataHolder, sales);
				}
			}
			else
			{
				log.warn("sales record not found [xReference: " + xReference + "]");
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
			format = URLClassLoader.getSystemResourceAsStream(fileFormatFileName);
			ExcelFormat excelFormat = new ExcelFormat(format);

			input = new FileInputStream(dataFileLocation);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<String> sheetNames = fileDataHolder.getKeyList();
			log.debug("Total sheet: " + sheetNames.size());

			for (String sheetName : sheetNames)
			{
				log.debug("process sheetName: " + sheetName);
				DataHolder sheet = fileDataHolder.get(sheetName);

				List<DataHolder> uwDataHolderList = sheet.getDataList("uwRecords");

				importUw(uwDataHolderList);
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
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
	public static final String LOG_FILE_NAME = "log.mtlPendingUw.file.name";

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatFileName = "fileformat/salesdb/FileFormat_MTL_Pending_Pre_UW.xml";
		String rootPath = args[0] /* "D:/Work/Report/DailyReport/MTL_Pending_Pre_UW" */;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && name.contains(".xls");
			}
		});

		ImportMtlPendingPreUw batch = new ImportMtlPendingPreUw();
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
