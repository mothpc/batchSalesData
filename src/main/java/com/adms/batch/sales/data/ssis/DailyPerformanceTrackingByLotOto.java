package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FilenameFilter;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.support.FileWalker;

public class DailyPerformanceTrackingByLotOto extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
//		new DailyPerformanceTrackingByLotOto().test("D:/Work/Report/DailyReport/201501");
//		new DailyPerformanceTrackingByLotOto().test("D:/Work/Report/DailyReport/201502");
//		new DailyPerformanceTrackingByLotOto().test("D:/Work/Report/DailyReport/201503");
//		new DailyPerformanceTrackingByLotOto().test("D:/Work/Report/DailyReport/201504");
//		new DailyPerformanceTrackingByLotOto().test("D:/Work/Report/DailyReport/201505");
		new DailyPerformanceTrackingByLotOto().test(args[0]);
	}

	public void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && dir.getAbsolutePath().contains("OTO") && ((name.contains("_DAILYP_") && name.contains("_MTD_")) || name.contains("Daily_Performance_Tracking_ByLot.xls") || name.contains("Daily_Performance_Tracking.xls") || name.contains("DailyPerformanceTrackingReport.xlsx") || (name.contains("DailyPerformanceTrackingReport_") && name.contains(".xlsx")));
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");
			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "DailyPerformanceTracking.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}

			log.info(fileName + " --> " + outputFileNameSsis);
			new DailyPerformanceTrackingByLotFileTransform().transform("fileformat/report/ssis/FileFormat_SSIS_DailyPerformanceTrackingByLot-input-OTO.xml", new File(fileName), "fileformat/report/ssis/FileFormat_SSIS_DailyPerformanceTrackingByLot-output.xml", new File(outputFileNameSsis));
		}
	}

}
