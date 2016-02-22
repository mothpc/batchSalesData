package com.adms.batch.sales.test;

import java.io.File;
import java.io.FilenameFilter;

import com.adms.batch.sales.data.ssis.DailyTsrProductionFileTransform;
import com.adms.batch.sales.support.FileWalker;

public class DailyTsrProductionTele {

	public static void main(String[] args)
			throws Exception
	{
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTLKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTLKBANK");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTIKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTIKBANK");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MSIGUOB", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MSIGUOB");
//
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/MTLBL", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/MTLBL");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/MSIGBL", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/MSIGBL");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/FWDTVD", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/FWDTVD");

//		test("D:/Work/Report/DailyReport/201410");
//		test("D:/Work/Report/DailyReport/201411");
//		test("D:/Work/Report/DailyReport/201412");
//		test("D:/Work/Report/DailyReport/201501");
		test(args[0]);
	}

	public static void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return dir.getAbsolutePath().contains("TELE") && name.contains("TSR_Production.xls");
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");
			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "TsrProduction.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}
			
			System.out.println(fileName + " --> " + outputFileNameSsis);
			new DailyTsrProductionFileTransform().transform("FileFormat_SSIS_DailyTsrProduction-input-TELE.xml", new File(fileName), "FileFormat_SSIS_DailyTsrProduction-output.xml", new File(outputFileNameSsis));
		}
	}

}
