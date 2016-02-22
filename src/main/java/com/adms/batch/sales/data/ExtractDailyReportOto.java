package com.adms.batch.sales.data;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.adms.support.OutlookPstManager;
import com.adms.support.PasswordResolver;
import com.adms.support.PstAttachementFilter;
import com.adms.utils.ZipUtil;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

public class ExtractDailyReportOto {
	private static final String SYSTEM_FILE_SEPARATOR = "file.separator";

	public static void main(String[] args) throws Exception
	{
		String rootPath = args[0]; //"T:\\Business Solution\\Share\\AutomateReport";
		String inputPath = args[1]; //"T:\\Business Solution\\Share\\AutomateReport\\DailyReport\\201508";

		OutlookPstManager pstManager = new OutlookPstManager(rootPath + "\\MailArchive\\ADMSTH_DataAdmin\\ConfirmRPT_OTO.pst",
		new PstAttachementFilter()
		{
			public boolean accept(PSTFolder folder, PSTMessage email)
			{
				boolean acceptedFolder = folder.getDisplayName().toLowerCase().contains("daily oto");
				boolean acceptedSubject = email.getSubject().toLowerCase().contains("confirmrpt");

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
			String specific = inputPath + System.getProperty(SYSTEM_FILE_SEPARATOR) + "OTO";

			PasswordResolver pr = new PasswordResolver()
			{
				String OTOPWD = "aeg@n#yyyy";

				public String resolvePassword(String filename)
				{
					return OTOPWD.replaceAll("#yyyy", filename.substring(filename.indexOf("_Report") - 4, filename.indexOf("_Report")));
				}
			};

			ZipUtil.extractAll(file.getAbsolutePath(), specific, pr.resolvePassword(file.getName()));

			// archive zip files
			File zipArchiveDir = new File(inputPath + System.getProperty(SYSTEM_FILE_SEPARATOR) + "OTO" + System.getProperty(SYSTEM_FILE_SEPARATOR) + "zipfiles");
			if (!zipArchiveDir.exists()) zipArchiveDir.mkdirs();
			file.renameTo(new File(inputPath + System.getProperty(SYSTEM_FILE_SEPARATOR) + "OTO" + System.getProperty(SYSTEM_FILE_SEPARATOR) + "zipfiles" + System.getProperty(SYSTEM_FILE_SEPARATOR) + file.getName()));
		}
	}
}
