package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.data.partner.model.MappingMTLModel;
import com.adms.batch.sales.service.SalesService;
import com.adms.batch.sales.support.FileWalker;
import com.adms.cs.entity.RejectMapping;
import com.adms.cs.service.CustomerService;
import com.adms.cs.service.CustomerYesRecordService;
import com.adms.cs.service.RejectMappingService;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportMappingFileKbank {

	public static final String APPLICATION_CONTEXT_PATH_SALES = "config/application-context-import-salesdb.xml";
	private ApplicationContext applicationContextSales;

	public static final String APPLICATION_CONTEXT_PATH_CS = "config/application-context-import-csdb.xml";
	private ApplicationContext applicationContextCs;
	
	public static final String FILE_FORMAT_MAPPING_MTI = "fileformat/mapping/FileFormat_Mapping_MTI_KBank.xml";
	
	private final String MTL_DELIM_CHAR = "\\|";
	
	public static final String BATCH_ID = "BATCH_ID";
	
	protected Logger log = Logger.getLogger(Logger.DEBUG);
	
	protected void setLogLevel(int logLevel)
	{
		this.log.setLogLevel(logLevel);
	}

	protected void setLogFileName(String logFileName) throws FileNotFoundException
	{
		this.log.setLogFileName(logFileName);
	}
	
	protected Object getSalesBean(String beanId)
	{
		if (this.applicationContextSales == null)
		{
			this.applicationContextSales = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_PATH_SALES);
		}

		return this.applicationContextSales.getBean(beanId);
	}

	protected Object getCsBean(String beanId)
	{
		if (this.applicationContextCs == null)
		{
			this.applicationContextCs = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_PATH_CS);
		}

		return this.applicationContextCs.getBean(beanId);
	}
	
	protected SalesService getSalesService()
	{
		return (SalesService) getSalesBean("salesService");
	}

	protected CustomerService getCustomerService()
	{
		return (CustomerService) getCsBean("customerService");
	}

	protected CustomerYesRecordService getCustomerYesRecordService()
	{
		return (CustomerYesRecordService) getCsBean("customerYesRecordService");
	}
	
	protected RejectMappingService getRejectMappingService() {
		return (RejectMappingService) getCsBean("rejectMappingService");
	}
	
	public ImportMappingFileKbank() {
		try {
			getSalesService();
			getCustomerYesRecordService();
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void importDataHolderList(List<DataHolder> dataHolderList) throws Exception {
		for (DataHolder dataHolder : dataHolderList) {
			String xRef = dataHolder.get("keyCode").getStringValue() + dataHolder.get("uniqueId").getStringValue();
			String cardNo = dataHolder.get("cardNo").getStringValue();
			String accountType = dataHolder.get("accountType").getStringValue();
			Date rejectDate = (Date) dataHolder.get("rejectDate").getValue();
			importRejectMappingData(xRef, cardNo, accountType, null, "MTI", "KBANK", rejectDate);
		}
	}
	
	private void importRejectMappingData(String xRef, String cardNo, String accountType, String accountNo, String insurer, String source, Date rejectDate) throws Exception {
		RejectMapping rejectMapping = null;
		
		DetachedCriteria rejectCriteria = DetachedCriteria.forClass(RejectMapping.class);
		rejectCriteria.add(Restrictions.eq("referenceNo", xRef));
		List<RejectMapping> list = getRejectMappingService().findByCriteria(rejectCriteria);
		if(list != null && !list.isEmpty()) {
			//Existing Record
			rejectMapping = list.get(0);
			rejectMapping.setImportDate(new Date());
			rejectMapping.setCardNo(cardNo);
			rejectMapping.setAccountType(accountType);
			rejectMapping.setRejectDate(rejectDate);
			getRejectMappingService().update(rejectMapping, BATCH_ID);
		} else {
			//New Record
			rejectMapping = new RejectMapping();
			rejectMapping.setImportDate(new Date());
			rejectMapping.setReferenceNo(xRef);
			rejectMapping.setCardNo(cardNo);
			rejectMapping.setAccountType(accountType);
			rejectMapping.setInsurer(insurer);
			rejectMapping.setSource(source);
			rejectMapping.setRejectDate(rejectDate);
			getRejectMappingService().add(rejectMapping, BATCH_ID);
		}
	}
	
	private List<String[]> extractMTLDataToArrays(String fileLocation) throws Exception {
		Path path = Paths.get(fileLocation);
		return Files.lines(path, Charset.forName("windows-874"))
				.map(m -> m.split(MTL_DELIM_CHAR))
				.collect(Collectors.toList());
	}
	
	public void importForMTL(String filePath) throws Exception {
		log.info("import: " + filePath);
		List<String[]> lines = extractMTLDataToArrays(filePath);
		for(String[] line : lines) {
			MappingMTLModel model = new MappingMTLModel(line);
			if(model.getDataType().equals("D") && model.getStatus().equals("N")) {
				importRejectMappingData(model.getRefNo(), model.getCardNo(), null, model.getAccountNo(), "MTL", "KBANK", null);
			}
		}
	}
	
	public void importForMTI(String filePath) throws Exception {
		log.info("import: " + filePath);
		
		try(InputStream fileFormatStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_FORMAT_MAPPING_MTI)) {
			ExcelFormat excelFormat = new ExcelFormat(fileFormatStream);
			
			try(InputStream excelStream = new FileInputStream(filePath)) {
				DataHolder fileDataHolder = excelFormat.readExcel(excelStream);
				DataHolder sheetHolder = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0));
				List<DataHolder> dataHolderList = sheetHolder.getDataList("rejectRecords");
				importDataHolderList(dataHolderList);
			} catch(Exception e) {
				throw e;
			}
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		String rootPath = args[0];
		String logFile = args[1];
		
		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") 
						&& !dir.getAbsolutePath().toLowerCase().contains("archive")
						&& (name.endsWith(".txt") || name.contains(".xls"));
			}
		});
		
		ImportMappingFileKbank batch = new ImportMappingFileKbank();
		batch.setLogLevel(Logger.INFO);
		batch.setLogFileName(logFile);
		
		for(String filePath : fw.getFileList()) {
			if(filePath.contains("MTL")) {
				batch.importForMTL(filePath);
			} else if(filePath.contains("MTI")) {
				batch.importForMTI(filePath);
			}
		}
		
	}
	
}
