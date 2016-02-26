package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.data.partner.model.YesRecordMTIModel;
import com.adms.batch.sales.data.partner.model.YesRecordMTLModel;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.service.SalesService;
import com.adms.batch.sales.support.FileWalker;
import com.adms.cs.entity.Customer;
import com.adms.cs.entity.CustomerYesRecord;
import com.adms.cs.service.CustomerService;
import com.adms.cs.service.CustomerYesRecordService;
import com.adms.utils.Logger;

public class ImportKbankYesFile {

	public static final String APPLICATION_CONTEXT_PATH_SALES = "config/application-context-import-salesdb.xml";
	private ApplicationContext applicationContextSales;

	public static final String APPLICATION_CONTEXT_PATH_CS = "config/application-context-import-csdb.xml";
	private ApplicationContext applicationContextCs;
	
	public static final String MTI_CONFIG_DATA_PATH = "config/YesMTIConfig.xlsx";
	private Map<String, String> relationshipMap;
	private Map<String, String> maritalStatusMap;
	
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
	
	public ImportKbankYesFile() {
		getSalesService();
		getCustomerYesRecordService();
		try {
			initialConfigData();
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void importMTLDataList(List<String[]> lines) throws Exception {
		if(lines != null && !lines.isEmpty()) {
			for(String[] line : lines) {
				importMTLData(line);
			}
		}
	}
	
	private void importMTIDataList(List<String> lines) throws Exception {
		if(lines != null && !lines.isEmpty()) {
			for(String line : lines) {
				importMTIData(line);
			}
		}
	}
	
	private void importMTLData(String[] data) {
		YesRecordMTLModel record = null;
		try {
			record = new YesRecordMTLModel(data);
			
			Sales sales = getSalesService().findSalesRecordByXRefference(record.getxReferenceNo());
			if(sales != null) {
				boolean isNewCustomerData = false;
				Customer insured = null;
				
				if((insured = getCustomerByCitizenId(record.getIdCard())) == null) {
					insured = getCustomerByFirstAndLastName(record.getFirstName(), record.getLastName());
				}
				
				if(insured == null) {
					insured = new Customer();
					isNewCustomerData = true;
				}
				
				extractInsured(record, insured);
				if(isNewCustomerData) {
					insured = getCustomerService().add(insured, BATCH_ID);
				} else {
					insured = getCustomerService().update(insured, BATCH_ID);
				}
				
				boolean isNewYesRecord = false;
				CustomerYesRecord yesRecord = getCustomerYesRecordService().find(record.getxReferenceNo());
				if(yesRecord == null) {
					yesRecord = new CustomerYesRecord();
					yesRecord.setReferenceNo(record.getxReferenceNo());
					yesRecord.setSequenceNo(record.getSequenceNo());
					isNewYesRecord = true;
				}
				
				extractCustomerYesRecord(record, insured, yesRecord);
				yesRecord.setImportDate(new Date());
				if(isNewYesRecord) {
					getCustomerYesRecordService().add(yesRecord, BATCH_ID);
				} else {
					getCustomerYesRecordService().update(yesRecord, BATCH_ID);
				}
			} else {
				throw new Exception("Sales record not found [xRef: " + record.getxReferenceNo() + "]");
			}
			
		} catch(Exception e) {
			log.error(e.getMessage() + " | " + "Error when importing data: " + Arrays.toString(data));
		}
	}
	
	private void importMTIData(String line) throws Exception {
		YesRecordMTIModel record = null;
		
		try {
			record = new YesRecordMTIModel(line);
			String xRef = record.getKeyCode() + record.getUniqueId();
			
			Sales sales = getSalesService().findSalesRecordByXRefference(xRef);
			if(sales != null) {
				boolean isNewCustomerData = false;
				
				Customer insured = null;
				
				if((insured = StringUtils.isNotBlank(record.getPrimaryIdCard()) ? getCustomerByCitizenId(record.getPrimaryIdCard()) : null) == null) {
					insured = getCustomerByFirstAndLastName(record.getPrimaryInsuredFirstName(), record.getPrimaryInsuredLastName());
				}
				
				if(insured == null) {
					insured = new Customer();
					isNewCustomerData = true;
				}
				
				extractInsured(record, insured);
				if(isNewCustomerData) {
					insured = getCustomerService().add(insured, BATCH_ID);
				} else {
					insured = getCustomerService().update(insured, BATCH_ID);
				}

				boolean isNewYesRecord = false;
				CustomerYesRecord yesRecord = getCustomerYesRecordService().find(xRef);
				if(yesRecord == null) {
					yesRecord = new CustomerYesRecord();
					yesRecord.setReferenceNo(xRef);
					isNewYesRecord = true;
				}
				
				extractCustomerYesRecord(record, insured, yesRecord);
				yesRecord.setImportDate(new Date());
				if(isNewYesRecord) {
					getCustomerYesRecordService().add(yesRecord, BATCH_ID);
				} else {
					getCustomerYesRecordService().update(yesRecord, BATCH_ID);
				}
			} else {
				throw new Exception("Sales record not found [xRef: " + xRef + "]");
			}
		} catch(Exception e) {
			log.error(e.getMessage() + " | " + "Error when importing data: " + line);
		}
		
	}
	
	private Customer extractInsured(YesRecordMTLModel record, Customer insured) throws Exception {
		if(record.getRecordType().equals(Integer.valueOf("1"))) {
			insured.setCitizenId(record.getIdCard());
			insured.setDob(record.getDob());
			insured.setMobileNo(StringUtils.isBlank(record.getMobileNo()) ? null : record.getMobileNo().contains("/") ? record.getMobileNo().replaceAll("-", "") : record.getMobileNo());
			insured.setMarital(record.getMaritalStatus());
		}
		insured.setTitle(record.getTitle());
		insured.setFirstName(record.getFirstName());
		insured.setLastName(record.getLastName());
		insured.setGender(record.getSex().equals("Female") ? "F" : "M");
		insured.setVisible("Y");
		return insured;
	}
	
	private Customer extractInsured(YesRecordMTIModel record, Customer insured) throws Exception {
		
		insured.setCitizenId(getValidatedString(record.getPrimaryIdCard()));
		insured.setDob(record.getPrimaryInsuredDob());
		insured.setMobileNo(getValidatedString(record.getPhoneAreaCode() + record.getPhoneNo()));
		String marital = StringUtils.isNotBlank(record.getPrimaryMaritalStatus()) ? maritalStatusMap.get(record.getPrimaryMaritalStatus()) : "Not Capture";
		insured.setMarital(getValidatedString(marital));
		
		insured.setTitle(getValidatedString(record.getPrimaryInsuredTitile()));
		insured.setFirstName(getValidatedString(record.getPrimaryInsuredFirstName()));
		insured.setLastName(getValidatedString(record.getPrimaryInsuredLastName()));
		insured.setGender(getValidatedString(record.getPrimaryInsuredGender()));
		insured.setVisible("Y");
		return insured;
	}
	
	private String getValidatedString(String val) {
		return StringUtils.isNotBlank(val) ? val : null;
	}
	
	private void extractCustomerYesRecord(YesRecordMTLModel record, Customer insured, CustomerYesRecord yesRecord) throws Exception {
		
		if(record.getRecordType().equals(Integer.valueOf("1"))) {
			yesRecord.setCustomer(insured);
			yesRecord.setPremium(new BigDecimal(record.getPolicyPremium().doubleValue()));
			
			yesRecord.setInsuredMobileNo(record.getMobileNo());
			yesRecord.setInsuredEmail(record.getEmail());
			yesRecord.setInsuredOccupation(record.getOccupationalCategory());
			
			yesRecord.setInsuredAddress1(record.getAddress1());
			yesRecord.setInsuredAddress2(record.getAddress2());
			yesRecord.setInsuredAddress3(record.getAddress3());
			yesRecord.setInsuredAddress4(record.getAddress4());
			yesRecord.setInsuredPostCode(record.getPostCode());
			
			yesRecord.setInsuredAccountType(record.getAccountType());
			yesRecord.setInsuredAccountNo(record.getAccountNo());
			yesRecord.setInsuredAccountExpiredDate(record.getExpiryDate());
			
			yesRecord.setEffectiveDate(record.getPolicyEffectiveDate());
			yesRecord.setProductCode(record.getProductCode());
			yesRecord.setBenefitLevel(record.getBenefitLevel());
			yesRecord.setCoverCode(record.getCoverCode());
			
			yesRecord.setBillFrequency(record.getPaymentFrequency());
			
			yesRecord.setCampaignName(record.getCampaignCode());
			
			yesRecord.setPayeeName(record.getPayeeName());
			yesRecord.setPayeeCitizenId(record.getPayeeIdCardNo());
			yesRecord.setPayeeBankCode(record.getBankCode());
			yesRecord.setPayeeBranchCode(record.getBranchCode());
			
		} else if(record.getRecordType().equals(Integer.valueOf("4"))) {
			extractBeneficiary(record, yesRecord);
		}
		
	}
	
	private void extractCustomerYesRecord(YesRecordMTIModel record, Customer insured, CustomerYesRecord yesRecord) throws Exception {
		yesRecord.setCustomer(insured);
		yesRecord.setPolicyNo(record.getPolicyNo());
		yesRecord.setEffectiveDate(record.getEffectiveDate());
		
		yesRecord.setCampaignName(getValidatedString(record.getKbankCampaignCode()));
		yesRecord.setProductCode(getValidatedString(record.getProductLineCode()));
		yesRecord.setBenefitLevel(getValidatedString(record.getSoldPlanCode()));
		
		yesRecord.setInsuredAccountType(getValidatedString(record.getAccountType()));
		String accNo = StringUtils.isNotBlank(record.getCreditCardAccNo()) ? record.getCreditCardAccNo()
				: StringUtils.isNotBlank(record.getBankAccNo()) ? record.getBankAccNo() : null;
		yesRecord.setInsuredAccountNo(accNo);
		yesRecord.setInsuredAccountExpiredDate(getValidatedString(record.getCreditCardExpiryDate()));
		yesRecord.setInsuredBankCode(getValidatedString(record.getBankCode1()));
		
		yesRecord.setInsuredMobileNo(insured.getMobileNo());
		yesRecord.setInsuredPostCode(record.getPostCode());
		
		yesRecord.setBillFrequency(getValidatedString(record.getSoldBillingFreq()));
		yesRecord.setPremium(StringUtils.isNotBlank(record.getSoldPremium()) ? new BigDecimal(record.getSoldPremium()) : null);
		
		yesRecord.setInsuredProvince(getValidatedString(record.getProvince()));
		yesRecord.setInsuredPostCode(getValidatedString(record.getPostCode()));
		
		String[] benefitciaryArray = getBenefitciaryName(record.getBeneficiaryName1());
		if(benefitciaryArray != null) {
			yesRecord.setBene1Title(benefitciaryArray[0]);
			yesRecord.setBene1FirstName(benefitciaryArray[1]);
			yesRecord.setBene1LastName(benefitciaryArray[2]);
			String relation = relationshipMap.get(record.getBeneficiaryRelation1());
			yesRecord.setBene1Relation(StringUtils.isNotBlank(relation) ? relation : null);
			yesRecord.setBene1Percent(StringUtils.isNotBlank(record.getBeneficiaryPercent1()) ? new BigDecimal(record.getBeneficiaryPercent1()).setScale(10) : null);
		}
		
		benefitciaryArray = getBenefitciaryName(record.getBeneficiaryName2());
		if(benefitciaryArray != null) {
			yesRecord.setBene2Title(benefitciaryArray[0]);
			yesRecord.setBene2FirstName(benefitciaryArray[1]);
			yesRecord.setBene2LastName(benefitciaryArray[2]);
			String relation = relationshipMap.get(record.getBeneficiaryRelation2());
			yesRecord.setBene2Relation(StringUtils.isNotBlank(relation) ? relation : null);
			yesRecord.setBene2Percent(StringUtils.isNotBlank(record.getBeneficiaryPercent2()) ? new BigDecimal(record.getBeneficiaryPercent2()).setScale(10) : null);
		}
		
		benefitciaryArray = getBenefitciaryName(record.getBeneficiaryName3());
		if(benefitciaryArray != null) {
			yesRecord.setBene3Title(benefitciaryArray[0]);
			yesRecord.setBene3FirstName(benefitciaryArray[1]);
			yesRecord.setBene3LastName(benefitciaryArray[2]);
			String relation = relationshipMap.get(record.getBeneficiaryRelation3());
			yesRecord.setBene3Relation(StringUtils.isNotBlank(relation) ? relation : null);
			yesRecord.setBene3Percent(StringUtils.isNotBlank(record.getBeneficiaryPercent3()) ? new BigDecimal(record.getBeneficiaryPercent3()).setScale(10) : null);
		}
		
		benefitciaryArray = getBenefitciaryName(record.getBeneficiaryName4());
		if(benefitciaryArray != null) {
			yesRecord.setBene4Title(benefitciaryArray[0]);
			yesRecord.setBene4FirstName(benefitciaryArray[1]);
			yesRecord.setBene4LastName(benefitciaryArray[2]);
			String relation = relationshipMap.get(record.getBeneficiaryRelation4());
			yesRecord.setBene4Relation(StringUtils.isNotBlank(relation) ? relation : null);
			yesRecord.setBene4Percent(StringUtils.isNotBlank(record.getBeneficiaryPercent4()) ? new BigDecimal(record.getBeneficiaryPercent4()).setScale(10) : null);
		}
		
		benefitciaryArray = getBenefitciaryName(record.getBeneficiaryName5());
		if(benefitciaryArray != null) {
			yesRecord.setBene5Title(benefitciaryArray[0]);
			yesRecord.setBene5FirstName(benefitciaryArray[1]);
			yesRecord.setBene5LastName(benefitciaryArray[2]);
			String relation = relationshipMap.get(record.getBeneficiaryRelation5());
			yesRecord.setBene5Relation(StringUtils.isNotBlank(relation) ? relation : null);
			yesRecord.setBene5Percent(StringUtils.isNotBlank(record.getBeneficiaryPercent5()) ? new BigDecimal(record.getBeneficiaryPercent5()).setScale(10) : null);
		}
		
		if(StringUtils.isNotBlank(record.getSpouseFirstName())) {
			yesRecord.setSpTitle(getValidatedString(record.getSpouseTitle()));
			yesRecord.setSpFirstName(getValidatedString(record.getSpouseFirstName()));
			yesRecord.setSpLastName(getValidatedString(record.getSpouseLastName()));
			yesRecord.setSpGender(getValidatedString(record.getSpouseGender()));
			yesRecord.setSpDob(record.getSpouseDob());
			yesRecord.setSpOccupation(getValidatedString(record.getSpouseInsuredOccCode()));
			yesRecord.setSpCitizenId(getValidatedString(record.getSpouseIdCard()));
		}
	}
	
	private void initialConfigData() {
		try(Workbook wb = WorkbookFactory.create(Thread.currentThread().getContextClassLoader().getResourceAsStream(MTI_CONFIG_DATA_PATH))) {
			relationshipMap = new HashMap<>();
			maritalStatusMap = new HashMap<>();
			
			Sheet sheet = wb.getSheetAt(0);
			for(int r = 1; r < sheet.getLastRowNum(); r++) {
				Row row = sheet.getRow(r);
				Cell catCell = row.getCell(CellReference.convertColStringToIndex("A"), Row.CREATE_NULL_AS_BLANK);
				if(catCell != null) {
					if(catCell.getStringCellValue().equals("RELATIONSHIP")) {
						relationshipMap.put(getStringValFromCell(row.getCell(CellReference.convertColStringToIndex("B")))
								, getStringValFromCell(row.getCell(CellReference.convertColStringToIndex("C"))));
					} else if(catCell.getStringCellValue().equals("MARITAL_STATUS")) {
						maritalStatusMap.put(getStringValFromCell(row.getCell(CellReference.convertColStringToIndex("B")))
								, getStringValFromCell(row.getCell(CellReference.convertColStringToIndex("C"))));
					} else {
						log.error("Uncategorize data: " + getStringValFromCell(catCell));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	private String getStringValFromCell(Cell cell) {
		String result = null;
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC : result = new BigDecimal(cell.getNumericCellValue()).setScale(0).toString(); break;
		case Cell.CELL_TYPE_STRING : result = cell.getStringCellValue(); break;
		case Cell.CELL_TYPE_BLANK : result = null; break;
		case Cell.CELL_TYPE_FORMULA : result = cell.getCellFormula(); break;
		default : break;
		}
		return result;
	}
	
	private String[] getBenefitciaryName(String beneName) throws Exception {
		String[] benefitciaryArray;
		String[] result = null;
		if((benefitciaryArray = StringUtils.isNotBlank(beneName) ? beneName.split(" ") : null) != null) {
			result = new String[3];
			switch(benefitciaryArray.length) {
			case 2 : 
				result[0] = null;
				result[1] = benefitciaryArray[0];
				result[2] = benefitciaryArray[1];
			case 3 :
				result[0] = benefitciaryArray[0];
				result[1] = benefitciaryArray[1];
				result[2] = benefitciaryArray[2];
				break;
			case 4 :
				result[0] = benefitciaryArray[0];
				result[1] = benefitciaryArray[1];
				result[2] = benefitciaryArray[3];
				break;
			default : throw new Exception("Cannot extract Beneficiary Name from: " + Arrays.toString(benefitciaryArray) + ". "
					+ "due to Beneficiary name need at least first and last name.");
			}
		}
		return result;
	}
	
	private void extractBeneficiary(YesRecordMTLModel record, CustomerYesRecord yesRecord) throws Exception {
		if(record.getBeneficiaryPercentage().equals(Double.parseDouble("100"))) {
			yesRecord.setBene1FirstName(record.getFirstName());
			yesRecord.setBene1LastName(record.getLastName());
			yesRecord.setBene1Age(record.getBeneficiaryAge().toString());
			yesRecord.setBene1Relation(record.getBeneficaryRelationship());
			yesRecord.setBene1Percent(new BigDecimal(record.getBeneficiaryPercentage().doubleValue()));
		} else {
			if(StringUtils.isBlank(yesRecord.getBene1FirstName())) {
				yesRecord.setBene1Title(record.getTitle());
				yesRecord.setBene1FirstName(record.getFirstName());
				yesRecord.setBene1LastName(record.getLastName());
				yesRecord.setBene1Age(record.getBeneficiaryAge().toString());
				yesRecord.setBene1Relation(record.getBeneficaryRelationship());
				yesRecord.setBene1Percent(new BigDecimal(record.getBeneficiaryPercentage().doubleValue()));
				
			} else if(StringUtils.isBlank(yesRecord.getBene2FirstName())) {
				yesRecord.setBene2Title(record.getTitle());
				yesRecord.setBene2FirstName(record.getFirstName());
				yesRecord.setBene2LastName(record.getLastName());
				yesRecord.setBene2Age(record.getBeneficiaryAge().toString());
				yesRecord.setBene2Relation(record.getBeneficaryRelationship());
				yesRecord.setBene2Percent(new BigDecimal(record.getBeneficiaryPercentage().doubleValue()));
				
			} else if(StringUtils.isBlank(yesRecord.getBene3FirstName())) {
				yesRecord.setBene3Title(record.getTitle());
				yesRecord.setBene3FirstName(record.getFirstName());
				yesRecord.setBene3LastName(record.getLastName());
				yesRecord.setBene3Age(record.getBeneficiaryAge().toString());
				yesRecord.setBene3Relation(record.getBeneficaryRelationship());
				yesRecord.setBene3Percent(new BigDecimal(record.getBeneficiaryPercentage().doubleValue()));
				
			} else if(StringUtils.isBlank(yesRecord.getBene4FirstName())) {
				yesRecord.setBene4Title(record.getTitle());
				yesRecord.setBene4FirstName(record.getFirstName());
				yesRecord.setBene4LastName(record.getLastName());
				yesRecord.setBene4Age(record.getBeneficiaryAge().toString());
				yesRecord.setBene4Relation(record.getBeneficaryRelationship());
				yesRecord.setBene4Percent(new BigDecimal(record.getBeneficiaryPercentage().doubleValue()));
				
			} else if(StringUtils.isBlank(yesRecord.getBene5FirstName())) {
				yesRecord.setBene5Title(record.getTitle());
				yesRecord.setBene5FirstName(record.getFirstName());
				yesRecord.setBene5LastName(record.getLastName());
				yesRecord.setBene5Age(record.getBeneficiaryAge().toString());
				yesRecord.setBene5Relation(record.getBeneficaryRelationship());
				yesRecord.setBene5Percent(new BigDecimal(record.getBeneficiaryPercentage().doubleValue()));
			}
		}
	}
	
	private Customer getCustomerByFirstAndLastName(String firstName, String lastName) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.eq("firstName", firstName))
			.add(Restrictions.eq("lastName", lastName))
			.add(Restrictions.eq("visible", "Y"));
		List<Customer> list = getCustomerService().findByCriteria(criteria);
		if(list != null && !list.isEmpty()) {
			if(list.size() == 1) {
				return list.get(0);
			} else {
				throw new Exception("Found Insured (Customer table) too many record for this first and lastname with visible is 'Y': " + firstName + ", lastName: " + lastName);
			}
		} else {
			return null;
		}
	}
	
	private Customer getCustomerByCitizenId(String citizenId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.eq("citizenId", citizenId))
			.add(Restrictions.eq("visible", "Y"));
		List<Customer> list = getCustomerService().findByCriteria(criteria);
		
		if(list != null && !list.isEmpty()) {
			if(list.size() == 1) {
				return list.get(0);
			} else {
				throw new Exception("Found Insured (Customer table) too many record for this citizen id and visible is 'Y': " + citizenId);
			}
		} else {
			return null;
		}
	}
	
	private List<String[]> extractMTLDataToArrays(String fileLocation) throws Exception {
		Path path = Paths.get(fileLocation);
		return Files.lines(path, Charset.forName("windows-874"))
				.map(m -> m.split(MTL_DELIM_CHAR))
				.collect(Collectors.toList());
	}
	
	private List<String> extractMTIDataToArrays(String fileLocation) throws Exception {
		Path path = Paths.get(fileLocation);
		return Files.lines(path, Charset.forName("windows-874"))
				.map(m -> new String(m))
				.filter(p -> p.substring(0, 2).equals("T3"))
				.collect(Collectors.toList());
	}
	
	public void importForMTL(String fileLocation) throws Exception {
		log.info(" ");
		log.info("Import: " + fileLocation);
		try {
			List<String[]> lines = extractMTLDataToArrays(fileLocation);
			importMTLDataList(lines);
		} catch(Exception e) {
			log.error(e.getMessage());
			throw e;
		} finally {
			
		}
		log.info("Finish");
	}
	
	public void importForMTI(String fileLocation) throws Exception {
		log.info(" ");
		log.info("Import: " + fileLocation);
		try {
			List<String> lines = extractMTIDataToArrays(fileLocation);
			importMTIDataList(lines);
		} catch(Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		log.info("Finish");
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
						&& name.endsWith(".txt");
			}
		});
		
		ImportKbankYesFile batch = new ImportKbankYesFile();
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
