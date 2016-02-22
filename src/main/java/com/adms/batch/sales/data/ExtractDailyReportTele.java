package com.adms.batch.sales.data;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.adms.support.OutlookPstManager;
import com.adms.support.PasswordResolver;
import com.adms.support.PstAttachementFilter;
import com.adms.utils.DateUtil;
import com.adms.utils.ZipUtil;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

public class ExtractDailyReportTele {

	private static final String SYSTEM_FILE_SEPARATOR = "file.separator";

	public static void main(String[] args) throws Exception
	{
		String rootPath = args[0]; //"T:\\Business Solution\\Share\\AutomateReport";
		String inputPath = args[1]; //"T:\\Business Solution\\Share\\AutomateReport\\DailyReport\\201508";

		OutlookPstManager pstManager = new OutlookPstManager(rootPath + "\\MailArchive\\ADMSTH_DataAdmin\\AUTORPT_TELE.pst",
		new PstAttachementFilter()
		{
			public boolean accept(PSTFolder folder, PSTMessage email)
			{
				boolean acceptedFolder = folder.getDisplayName().toLowerCase().contains("daily tele");
				boolean acceptedSubject = email.getSubject().toLowerCase().contains("autorpt_") && !(email.getSubject().toLowerCase().contains("_app") || email.getSubject().toLowerCase().contains("_yesfiles"));

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.US);
				boolean acceptedDeliveryDate = df.format(email.getMessageDeliveryTime()).equals(df.format(new Date()));

				return acceptedFolder && acceptedSubject && acceptedDeliveryDate;
			}
		},
		new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return name.toLowerCase().contains(".zip");
			}
		});

		for (File file : pstManager.getAttachmentFiles())
		{
			String specific = inputPath + System.getProperty(SYSTEM_FILE_SEPARATOR) + "TELE";
			if (file.getName().contains("MSIG_UOB"))
			{
				specific += System.getProperty(SYSTEM_FILE_SEPARATOR) + "AUTO_MSIGUOB" + System.getProperty(SYSTEM_FILE_SEPARATOR) + file.getName().replace(".zip", "");
			}
			else if (file.getName().contains("MTI"))
			{
				specific += System.getProperty(SYSTEM_FILE_SEPARATOR) + "AUTO_MTIKBANK" + System.getProperty(SYSTEM_FILE_SEPARATOR) + file.getName().replace(".zip", "");
			}
			else if (file.getName().contains("MTL"))
			{
				specific += System.getProperty(SYSTEM_FILE_SEPARATOR) + "AUTO_MTLKBANK" + System.getProperty(SYSTEM_FILE_SEPARATOR) + file.getName().replace(".zip", "");
			}

			PasswordResolver pr = new PasswordResolver()
			{
				String TELEPWD = "Aeg@nReport#yyyyMMdd";

				public String resolvePassword(String filename)
						throws Exception
				{
					Calendar calendar = DateUtil.getCurrentCalendar();
					calendar.setTime(DateUtil.convStringToDate("yyMMdd", filename.substring(filename.indexOf(".zip") - 6, filename.indexOf(".zip"))));
					DateUtil.addDay(calendar, -1);
					return TELEPWD.replaceAll("#yyyyMMdd", DateUtil.convDateToString("yyyyMMdd", calendar.getTime()).replaceAll("0", "@"));
				}
			};

			ZipUtil.extractAll(file.getAbsolutePath(), specific, pr.resolvePassword(file.getName()));

			// archive zip files
			File zipArchiveDir = new File(inputPath + System.getProperty(SYSTEM_FILE_SEPARATOR) + "TELE" + System.getProperty(SYSTEM_FILE_SEPARATOR) + "zipfiles");
			if (!zipArchiveDir.exists()) zipArchiveDir.mkdirs();
			file.renameTo(new File(inputPath + System.getProperty(SYSTEM_FILE_SEPARATOR) + "TELE" + System.getProperty(SYSTEM_FILE_SEPARATOR) + "zipfiles" + System.getProperty(SYSTEM_FILE_SEPARATOR) + file.getName()));
		}
	}
}
