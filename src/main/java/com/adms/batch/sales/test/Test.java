package com.adms.batch.sales.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import com.adms.batch.sales.support.FileWalker;
import com.adms.utils.FileUtil;

public class Test {

	public static void main(String[] args) throws Exception
	{
		String a = "084-000-0417";
		System.out.println(a.replaceAll("-", ""));
		
//		FileWalker fw = new FileWalker();
//		fw.walk("D:/air/orig", new FilenameFilter()
//		{
//			
//			public boolean accept(File dir, String name)
//			{
//				// TODO Auto-generated method stub
//				return name.contains("_TSRTRA_");
//			}
//		});
//		
//		List<String> l = fw.getFileList();
//		
//		for (String filename : l)
//		{
//			String s = filename.replace("orig", "dest");
//
//			File dir = new File(s.substring(0, s.lastIndexOf('\\')));
//			dir.mkdirs();
//			File newFile = new File(s);
//			
//			FileUtil.getInstance().copyFile(new File(filename), newFile);
//		}

	}

}
