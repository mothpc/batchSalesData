package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.service.SalesService;
import com.adms.batch.sales.support.FileWalker;
import com.adms.cs.entity.Customer;
import com.adms.cs.entity.CustomerYesRecord;
import com.adms.cs.service.CustomerService;
import com.adms.cs.service.CustomerYesRecordService;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportFwdYesFile {

	public static final String APPLICATION_CONTEXT_PATH_SALES = "config/application-context-import-salesdb.xml";
	private ApplicationContext applicationContextSales;

	public static final String APPLICATION_CONTEXT_PATH_CS = "config/application-context-import-csdb.xml";
	private ApplicationContext applicationContextCs;

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

	private Customer extractCustomer(DataHolder dataHolder, Customer customer)
			throws Exception
	{
		log.debug("extractCustomer " + dataHolder.printValues());

		String titleName = dataHolder.get("INS_TITLE").getStringValue();
		String firstName = dataHolder.get("INS_FIRSTNAME").getStringValue();
		String lastName = dataHolder.get("INS_LASTNAME").getStringValue();
		String gender = dataHolder.get("INS_GENDER").getStringValue();
		Date dob = (Date) dataHolder.get("INS_DOB").getValue();
		String mobileNo = dataHolder.get("MOBILE_NO").getStringValue();

		customer.setTitle(titleName);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setGender(gender);
		customer.setDob(dob);
		customer.setMobileNo(mobileNo);
		customer.setVisible("Y");

		// TODO: set other fields if required

		return customer;
	}

	private CustomerYesRecord extractCustomerYesRecord(DataHolder dataHolder, Customer customer, CustomerYesRecord customerYesRecord)
	{

		String productplan = dataHolder.get("PRODUCT_PLAN").getStringValue();
		BigDecimal sumAssured = dataHolder.get("SUM_ASSURED").getDecimalValue();
		BigDecimal premium = dataHolder.get("PREMIUM").getDecimalValue();
		Date approveDate = (Date) dataHolder.get("APPROVE_DATE").getValue();

		String address1 = dataHolder.get("INS_ADDR_1").getStringValue();
		String address2 = dataHolder.get("INS_ADDR_2").getStringValue();
		String address3 = dataHolder.get("INS_ADDR_3").getStringValue();
		String address4 = dataHolder.get("INS_ADDR_4").getStringValue();
		String postCode = dataHolder.get("INS_POST_CODE").getStringValue();

		String bene1TitleName = dataHolder.get("BENE_1_TITLE").getStringValue();
		String bene1FirstName = dataHolder.get("BENE_1_FIRSTNAME").getStringValue();
		String bene1LastName = dataHolder.get("BENE_1_LASTNAME").getStringValue();
		BigDecimal bene1Percent = dataHolder.get("BENE_1_PERCENT").getDecimalValue();

		String bene2TitleName = dataHolder.get("BENE_2_TITLE").getStringValue();
		String bene2FirstName = dataHolder.get("BENE_2_FIRSTNAME").getStringValue();
		String bene2LastName = dataHolder.get("BENE_2_LASTNAME").getStringValue();
		BigDecimal bene2Percent = dataHolder.get("BENE_2_PERCENT").getDecimalValue();

		String bene3TitleName = dataHolder.get("BENE_3_TITLE").getStringValue();
		String bene3FirstName = dataHolder.get("BENE_3_FIRSTNAME").getStringValue();
		String bene3LastName = dataHolder.get("BENE_3_LASTNAME").getStringValue();
		BigDecimal bene3Percent = dataHolder.get("BENE_3_PERCENT").getDecimalValue();

		String bene4TitleName = dataHolder.get("BENE_4_TITLE").getStringValue();
		String bene4FirstName = dataHolder.get("BENE_4_FIRSTNAME").getStringValue();
		String bene4LastName = dataHolder.get("BENE_4_LASTNAME").getStringValue();
		BigDecimal bene4Percent = dataHolder.get("BENE_4_PERCENT").getDecimalValue();

		String bene5TitleName = dataHolder.get("BENE_5_TITLE").getStringValue();
		String bene5FirstName = dataHolder.get("BENE_5_FIRSTNAME").getStringValue();
		String bene5LastName = dataHolder.get("BENE_5_LASTNAME").getStringValue();
		BigDecimal bene5Percent = dataHolder.get("BENE_5_PERCENT").getDecimalValue();

		String bankAccountType = dataHolder.get("ACCOUNT_TYPE").getStringValue();
		String bankAccountNo = dataHolder.get("BANK_ACCOUNT_NO").getStringValue();
		String bankAccountExpired = dataHolder.get("ACCOUNT_EXPIRED").getStringValue();

		customerYesRecord.setCustomer(customer);
		customerYesRecord.setBenefit(sumAssured);
		customerYesRecord.setPremium(premium);

		customerYesRecord.setInsuredAddress1(address1);
		customerYesRecord.setInsuredAddress2(address2);
		customerYesRecord.setInsuredAddress3(address3);
		customerYesRecord.setInsuredAddress4(address4);
		customerYesRecord.setInsuredPostCode(postCode);
//
		customerYesRecord.setInsuredAccountType(bankAccountType);
		customerYesRecord.setInsuredAccountNo(bankAccountNo);
		customerYesRecord.setInsuredAccountExpiredDate(bankAccountExpired);
//
		customerYesRecord.setBene1FirstName(bene1FirstName);
		customerYesRecord.setBene1LastName(bene1LastName);
		customerYesRecord.setBene1Percent(bene1Percent);

		customerYesRecord.setBene2FirstName(bene2FirstName);
		customerYesRecord.setBene2LastName(bene2LastName);
		customerYesRecord.setBene2Percent(bene2Percent);

		customerYesRecord.setBene3FirstName(bene3FirstName);
		customerYesRecord.setBene3LastName(bene3LastName);
		customerYesRecord.setBene3Percent(bene3Percent);

		customerYesRecord.setBene4FirstName(bene4FirstName);
		customerYesRecord.setBene4LastName(bene4LastName);
		customerYesRecord.setBene4Percent(bene4Percent);

		customerYesRecord.setBene5FirstName(bene5FirstName);
		customerYesRecord.setBene5LastName(bene5LastName);
		customerYesRecord.setBene5Percent(bene5Percent);

		// TODO: set other fields if required

		return customerYesRecord;
	}

	private void importDataHolder(DataHolder dataHolder, Sales sales)
			throws Exception
	{
		log.debug("sales: " + sales);

		// check existing customer with citizen id
		String citizenId = dataHolder.get("CITIZEN_ID").getStringValue();
		if (StringUtils.isNotEmpty(citizenId))
		{
			citizenId = StringUtils.remove(citizenId, ' ');
		}

		boolean newCustomer = false;
		Customer customer = getCustomerService().find(citizenId);
		if (customer == null)
		{
			customer = new Customer();
			customer.setCitizenId(citizenId);
			newCustomer = true;
		}

		try
		{
			extractCustomer(dataHolder, customer);
			if (newCustomer)
			{
				customer = getCustomerService().add(customer, BATCH_ID);
			}
			else
			{
				customer = getCustomerService().update(customer, BATCH_ID);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		// check existing yes record with referenceNo
		String referenceNo = sales.getxReference();

		boolean newCustomerYesRecord = false;
		CustomerYesRecord customerYesRecord = getCustomerYesRecordService().find(referenceNo);
		if (customerYesRecord == null)
		{
			customerYesRecord = new CustomerYesRecord();
			customerYesRecord.setReferenceNo(referenceNo);
			newCustomerYesRecord = true;
		}

		try
		{
			extractCustomerYesRecord(dataHolder, customer, customerYesRecord);
			customerYesRecord.setImportDate(new Date());
			if (newCustomerYesRecord)
			{
				customerYesRecord = getCustomerYesRecordService().add(customerYesRecord, BATCH_ID);
			}
			else
			{
				customerYesRecord = getCustomerYesRecordService().update(customerYesRecord, BATCH_ID);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	private void importDataHolderList(List<DataHolder> dataHolderList)
			throws Exception
	{
		log.debug("dataHolderList size: " + dataHolderList.size());
		for (DataHolder dataHolder : dataHolderList)
		{
			// for FWD - use customer name and approve date
			String customerFullName = dataHolder.get("INS_FIRSTNAME").getStringValue() + dataHolder.get("INS_LASTNAME").getStringValue();
			Date approveDate = (Date) dataHolder.get("APPROVE_DATE").getValue();
			
			Sales sales = getSalesService().findSalesRecordByCustomerFullNameAndApproveDate(customerFullName, approveDate);
			log.debug("sales: " + sales);

			if (sales != null)
			{
				importDataHolder(dataHolder, sales);
			}
			else
			{
				throw new Exception("sales record not found [customerFullName: " + customerFullName + " and approveDate: " + approveDate + "]");
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
			log.info("Total sheet: " + sheetNames.size());

			for (String sheetName : sheetNames)
			{
				log.info("process sheetName: " + sheetName);
				DataHolder sheet = fileDataHolder.get(sheetName);

				List<DataHolder> dataHolderList = sheet.getDataList("yesRecords");

				importDataHolderList(dataHolderList);
			}
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

	public static final String CONFIG_FILE_LOCATION = "config/importYesFile.properties";
	public static final String LOG_FILE_NAME = "log.fwdYesFile.file.name";

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatLocation = "fileformat/yesfile/FileFormat_YESFILE_FWD.xml";
		String rootPath = "T:\\Business Solution\\Share\\AutomateReport\\YesFiles\\FWDTVD";//args[0] /* "D:/Work/Report/DailyReport/FWD_Daily_Production" */;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && name.contains("YesFile_FWDTVD") && name.contains(".xlsx");
			}
		});

		ImportFwdYesFile batch = new ImportFwdYesFile();
		batch.setLogLevel(Logger.INFO);
		String logFileName = PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(LOG_FILE_NAME);
		batch.setLogFileName(logFileName);

		for (String filename : fw.getFileList())
		{
			batch.importFile(fileFormatLocation, filename);
		}
	}
}
