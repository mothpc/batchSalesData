package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.SalesProcess;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportGenIssueCaseOto extends AbstractImportSalesJob {

	protected void addSalesProcess(Sales sales, Date statusDate)
			throws Exception
	{
		SalesProcess salesProcess = getSalesProcessService().findSalesProcessByXRefferenceAndStatusDateAndPolicyStatus(sales.getxReference(), statusDate, "Inforce");

		boolean newSalesProcess = false;
		if (salesProcess == null)
		{
			salesProcess = new SalesProcess();
			newSalesProcess = true;
		}

		salesProcess.setFileImport(null);
		salesProcess.setBatchDate(getProcessDate());
		salesProcess.setxReference(sales);
		salesProcess.setStatusDate(statusDate);
		salesProcess.setPolicyStatus("Inforce");

		if (newSalesProcess)
		{
			salesProcess.setCreateBy(BATCH_ID);
			salesProcess.setCreateDate(new Date());
			getSalesProcessService().addSalesProcess(salesProcess, BATCH_ID);
		}
		else
		{
			salesProcess.setUpdateBy(BATCH_ID);
			salesProcess.setUpdateDate(new Date());
			getSalesProcessService().updateSalesProcess(salesProcess, BATCH_ID);
		}
	}

	private void importDataHolderList(List<DataHolder> dataHolderList)
			throws Exception
	{
		for (DataHolder dataHolder : dataHolderList)
		{
			String xReference = dataHolder.get("xReference").getStringValue();
			
			if (StringUtils.isNotBlank(xReference))
			{
				Sales sales = getSalesService().findSalesRecordByXRefference(xReference);
				log.debug("xReference: " + xReference + ", sales: " + sales);

				if (sales != null)
				{
					addSalesProcess(sales, getProcessDate());

					if (StringUtils.isNotBlank(sales.getxReferenceNew()))
					{
						sales = getSalesService().findSalesRecordByXRefference(sales.getxReferenceNew());

						if (sales != null)
						{
							addSalesProcess(sales, getProcessDate());
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

			List<DataHolder> issueDataHolderList = fileDataHolder.get("Issue").getDataList("dataRecords");

			importDataHolderList(issueDataHolderList);
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
	public static final String LOG_FILE_NAME = "log.genIssueCaseOto.file.name";

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatFileName = "fileformat/salesdb/FileFormat_GEN_IssueCase_OTO.xml";
		String rootPath = args[0] /* "T:/Business Solution\Share/AutomateReport/CommData/GEN_Issue_Case" */;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && name.contains("Success_") && name.endsWith(".xls");
			}
		});

		ImportGenIssueCaseOto batch = new ImportGenIssueCaseOto();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new SimpleDateFormat("yyyyMMdd").parse(new SimpleDateFormat("yyyyMMdd").format(new Date())));
		String logFileName = PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(LOG_FILE_NAME);
		batch.setLogFileName(logFileName);

		for (String filename : fw.getFileList())
		{
			batch.importFile(fileFormatFileName, filename);
		}
	}
}
