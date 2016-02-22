package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.List;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.DdopMapping2ndResult;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportDdopMapping2ndResult extends AbstractImportSalesJob {

	private DdopMapping2ndResult extractRecord(DataHolder ddopDataHolder, DdopMapping2ndResult ddopMapping2ndResult)
			throws Exception
	{
//		log.debug("extract Reject Mapping Record " + ddopDataHolder.printValues());

		ddopMapping2ndResult.setRejectDate((Date) ddopDataHolder.get("rejectDate").getValue());
		ddopMapping2ndResult.setLast4DigitDebitCard(ddopDataHolder.get("last4DigitDebitCard").getStringValue());
		ddopMapping2ndResult.setLast4DigitDebitCardNew(ddopDataHolder.get("last4DigitDebitCardNew").getStringValue());
		ddopMapping2ndResult.setRemark(ddopDataHolder.get("remark").getStringValue());
		ddopMapping2ndResult.setCommStatus(ddopDataHolder.get("commStatus").getStringValue());

		return ddopMapping2ndResult;
	}

	private void importRejectMapping(List<DataHolder> ddopDataHolderList)
			throws Exception
	{
		for (DataHolder ddopDataHolder : ddopDataHolderList)
		{
			log.debug("import Reject Mapping Record " + ddopDataHolder.printValues());

			String xReference = ddopDataHolder.get("xReference").getStringValue();

			Sales sales = getSalesService().findSalesRecordByXRefference(xReference);

			if (sales != null)
			{
				DdopMapping2ndResult ddopMapping2ndResult = getDdopMapping2ndResultService().findDdopMapping2ndResultByxReference(xReference);

				boolean newDdopMapping2ndResult = false;
				if (ddopMapping2ndResult == null)
				{
					ddopMapping2ndResult = new DdopMapping2ndResult();
					ddopMapping2ndResult.setxReference(sales);
					newDdopMapping2ndResult = true;
				}
				else
				{
					log.debug("found ddopMapping2ndResult record [" + ddopMapping2ndResult + "]");
				}

				try
				{
					extractRecord(ddopDataHolder, ddopMapping2ndResult);

					if (newDdopMapping2ndResult)
					{
//						ddopMapping2ndResult.setCreateBy(BATCH_ID);
//						ddopMapping2ndResult.setCreateDate(new Date());
						getDdopMapping2ndResultService().addDdopMapping2ndResult(ddopMapping2ndResult, BATCH_ID);
					}
					else
					{
//						ddopMapping2ndResult.setUpdateBy(BATCH_ID);
//						ddopMapping2ndResult.setUpdateDate(new Date());
						getDdopMapping2ndResultService().updateDdopMapping2ndResult(ddopMapping2ndResult, BATCH_ID);
					}
				}
				catch (Exception e)
				{
					log.error("error found on transaction: " + ddopMapping2ndResult);
					log.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
			else
			{
				log.warn("sales record not found [xRef: " + xReference + "]");
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

			List<DataHolder> rejectDataHolderList = fileDataHolder.get("RejectLot-Current").getDataList("dataRejectRecords");

			importRejectMapping(rejectDataHolderList);
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
	public static final String LOG_FILE_NAME = "log.mtiRejectMapping.file.name";

	public static void main(String[] args)
			throws Exception
	{
		System.out.println("main");

		String fileFormatFileName = "fileformat/salesdb/FileFormat_MTI_Reject_Mapping_Result.xml";
		String rootPath = /*args[0]*/  "T:/Business Solution/Share/AutomateReport/CommData/MTI_Reject_Mapping" ;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && name.toLowerCase().contains("reject_mapping_for_batch") && name.toLowerCase().endsWith(".xlsx");
			}
		});

		ImportDdopMapping2ndResult batch = new ImportDdopMapping2ndResult();
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
