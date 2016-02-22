package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FilenameFilter;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.support.FileWalker;

public class DailySalesReportByRecordsTele extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
//		test("D:/Work/Report/DailyReport/201410");
//		test("D:/Work/Report/DailyReport/201411");
//		test("D:/Work/Report/DailyReport/201412");
		new DailySalesReportByRecordsTele().test("D:/Work/Report/DailyReport/201501");
//		new DailySalesReportByRecordsTele().test(args[0]);
	}

	public void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && (dir.getAbsolutePath().contains("TELE") && !dir.getAbsolutePath().contains("MSIGUOB") && name.contains("Sales_Report_By_Records.xls"));
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");
			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "SalesReportByRecords.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}
			
			log.info(fileName + " --> " + outputFileNameSsis);
			new DailySalesReportByRecordsFileTransform().transform("FileFormat_SSIS_DailySalesReportByRecords-input-TELE.xml", new File(fileName), "FileFormat_SSIS_DailySalesReportByRecords-output.xml", new File(outputFileNameSsis));
		}
		
	}

}
