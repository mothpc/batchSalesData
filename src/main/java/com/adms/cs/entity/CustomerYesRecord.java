package com.adms.cs.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name="CUSTOMER_YES_RECORD")
public class CustomerYesRecord extends BaseAuditDomain {

	private static final long serialVersionUID = 2580555342840606597L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="IMPORT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date importDate;

	@Column(name="REFERENCE_NO")
	private String referenceNo;
	
	@Column(name="POLICY_NO")
	private String policyNo;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

	@Column(name="INSURED_OCCUPATION")
	private String insuredOccupation;
	
	@Column(name="INSURED_BANK_CODE")
	private String insuredBankCode;
	
	@Column(name="INSURED_ACCOUNT_TYPE")
	private String insuredAccountType;
	
	@Column(name="INSURED_ACCOUNT_NO")
	private String insuredAccountNo;
	
	@Column(name="INSURED_ACCOUNT_EXPIRED_DATE")
	private String insuredAccountExpiredDate;
	
	@Column(name="INSURED_ADDRESS_1")
	private String insuredAddress1;
	
	@Column(name="INSURED_ADDRESS_2")
	private String insuredAddress2;
	
	@Column(name="INSURED_ADDRESS_3")
	private String insuredAddress3;
	
	@Column(name="INSURED_ADDRESS_4")
	private String insuredAddress4;
	
	@Column(name="INSURED_SUB_DISTRICT")
	private String insuredSubDistrict;
	
	@Column(name="INSURED_DISTRICT")
	private String insuredDistrict;
	
	@Column(name="INSURED_PROVINCE")
	private String insuredProvince;
	
	@Column(name="INSURED_POST_CODE")
	private String insuredPostCode;
	
	@Column(name="INSURED_COUNTRY")
	private String insuredCountry;
	
	@Column(name="INSURED_MOBILE_NO")
	private String insuredMobileNo;
	
	@Column(name="INSURED_EMAIL")
	private String insuredEmail;
	
	@Column(name="SP_TITLE")
	private String spTitle;
	
	@Column(name="SP_FIRST_NAME")
	private String spFirstName;
	
	@Column(name="SP_LAST_NAME")
	private String spLastName;
	
	@Column(name="SP_NATIONAL")
	private String spNational;
	
	@Column(name="SP_CITIZEN_ID")
	private String spCitizenId;
	
	@Column(name="SP_PASSPORT_ID")
	private String spPassportId;
	
	@Column(name="SP_GENDER")
	private String spGender;
	
	@Column(name="SP_DOB")
	@Temporal(TemporalType.DATE)
	private Date spDob;
	
	@Column(name="SP_OCCUPATION")
	private String spOccupation;
	
	@Column(name="PREMIUM", scale=10)
	private BigDecimal premium;
	
	@Column(name="BENEFIT", scale=10)
	private BigDecimal benefit;
	
	@Column(name="BILL_FREQUENCY")
	private String billFrequency;
	
	@Column(name="BENE_1_FIRST_NAME")
	private String bene1FirstName;
	
	@Column(name="BENE_1_LAST_NAME")
	private String bene1LastName;
	
	@Column(name="BENE_1_PERCENT", scale=10)
	private BigDecimal bene1Percent;

	@Column(name="BENE_2_FIRST_NAME")
	private String bene2FirstName;

	@Column(name="BENE_2_LAST_NAME")
	private String bene2LastName;

	@Column(name="BENE_2_PERCENT", scale=10)
	private BigDecimal bene2Percent;

	@Column(name="BENE_3_FIRST_NAME")
	private String bene3FirstName;

	@Column(name="BENE_3_LAST_NAME")
	private String bene3LastName;

	@Column(name="BENE_3_PERCENT", scale=10)
	private BigDecimal bene3Percent;

	@Column(name="BENE_4_FIRST_NAME")
	private String bene4FirstName;

	@Column(name="BENE_4_LAST_NAME")
	private String bene4LastName;

	@Column(name="BENE_4_PERCENT", scale=10)
	private BigDecimal bene4Percent;

	@Column(name="BENE_5_FIRST_NAME")
	private String bene5FirstName;

	@Column(name="BENE_5_LAST_NAME")
	private String bene5LastName;

	@Column(name="BENE_5_PERCENT", scale=10)
	private BigDecimal bene5Percent;
	
	@Column(name="EFFECTIVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;
	
	@Column(name="PAYEE_NAME")
	private String payeeName;
	
	@Column(name="PAYEE_CITIZEN_ID")
	private String payeeCitizenId;
	
	@Column(name="PAYEE_BANK_CODE")
	private String payeeBankCode;
	
	@Column(name="PAYEE_BRANCH_CODE")
	private String payeeBranchCode;
	
	@Column(name="PRODUCT_CODE")
	private String productCode;
	
	@Column(name="BENEFIT_LEVEL")
	private String benefitLevel;
	
	@Column(name="COVER_CODE")
	private String coverCode;
	
	@Column(name="SEQUENCE_NO")
	private String sequenceNo;
	
	@Column(name="BENE_1_TITLE")
	private String bene1Title;

	@Column(name="BENE_1_AGE")
	private String bene1Age;

	@Column(name="BENE_1_RELATION")
	private String bene1Relation;

	@Column(name="BENE_2_TITLE")
	private String bene2Title;

	@Column(name="BENE_2_AGE")
	private String bene2Age;

	@Column(name="BENE_2_RELATION")
	private String bene2Relation;

	@Column(name="BENE_3_TITLE")
	private String bene3Title;

	@Column(name="BENE_3_AGE")
	private String bene3Age;

	@Column(name="BENE_3_RELATION")
	private String bene3Relation;

	@Column(name="BENE_4_TITLE")
	private String bene4Title;

	@Column(name="BENE_4_AGE")
	private String bene4Age;

	@Column(name="BENE_4_RELATION")
	private String bene4Relation;

	@Column(name="BENE_5_TITLE")
	private String bene5Title;

	@Column(name="BENE_5_AGE")
	private String bene5Age;

	@Column(name="BENE_5_RELATION")
	private String bene5Relation;
	
	@Column(name="OPT_OUT")
	private String optOut;
	
	@Column(name="CAMPAIGN_NAME")
	private String campaignName;
	
	@Column(name="INSURED_ANNUAL_INCOME")
	private String insuredAnnualIncome;
	
	@Column(name="INSURED_EDUCATION_LEVEL")
	private String insuredEducationLevel;
	
	@Column(name="FATCA")
	private String fatca;
	
	@Column(name="FATCA_DETAIL")
	private String fatcaDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getInsuredOccupation() {
		return insuredOccupation;
	}

	public void setInsuredOccupation(String insuredOccupation) {
		this.insuredOccupation = insuredOccupation;
	}

	public String getInsuredBankCode() {
		return insuredBankCode;
	}

	public void setInsuredBankCode(String insuredBankCode) {
		this.insuredBankCode = insuredBankCode;
	}

	public String getInsuredAccountType() {
		return insuredAccountType;
	}

	public void setInsuredAccountType(String insuredAccountType) {
		this.insuredAccountType = insuredAccountType;
	}

	public String getInsuredAccountNo() {
		return insuredAccountNo;
	}

	public void setInsuredAccountNo(String insuredAccountNo) {
		this.insuredAccountNo = insuredAccountNo;
	}

	public String getInsuredAccountExpiredDate() {
		return insuredAccountExpiredDate;
	}

	public void setInsuredAccountExpiredDate(String insuredAccountExpiredDate) {
		this.insuredAccountExpiredDate = insuredAccountExpiredDate;
	}

	public String getInsuredAddress1() {
		return insuredAddress1;
	}

	public void setInsuredAddress1(String insuredAddress1) {
		this.insuredAddress1 = insuredAddress1;
	}

	public String getInsuredAddress2() {
		return insuredAddress2;
	}

	public void setInsuredAddress2(String insuredAddress2) {
		this.insuredAddress2 = insuredAddress2;
	}

	public String getInsuredAddress3() {
		return insuredAddress3;
	}

	public void setInsuredAddress3(String insuredAddress3) {
		this.insuredAddress3 = insuredAddress3;
	}

	public String getInsuredAddress4()
	{
		return insuredAddress4;
	}

	public void setInsuredAddress4(String insuredAddress4)
	{
		this.insuredAddress4 = insuredAddress4;
	}

	public String getInsuredSubDistrict() {
		return insuredSubDistrict;
	}

	public void setInsuredSubDistrict(String insuredSubDistrict) {
		this.insuredSubDistrict = insuredSubDistrict;
	}

	public String getInsuredDistrict() {
		return insuredDistrict;
	}

	public void setInsuredDistrict(String insuredDistrict) {
		this.insuredDistrict = insuredDistrict;
	}

	public String getInsuredProvince() {
		return insuredProvince;
	}

	public void setInsuredProvince(String insuredProvince) {
		this.insuredProvince = insuredProvince;
	}

	public String getInsuredPostCode() {
		return insuredPostCode;
	}

	public void setInsuredPostCode(String insuredPostCode) {
		this.insuredPostCode = insuredPostCode;
	}

	public String getInsuredCountry() {
		return insuredCountry;
	}

	public void setInsuredCountry(String insuredCountry) {
		this.insuredCountry = insuredCountry;
	}

	public String getInsuredMobileNo() {
		return insuredMobileNo;
	}

	public void setInsuredMobileNo(String insuredMobileNo) {
		this.insuredMobileNo = insuredMobileNo;
	}

	public String getInsuredEmail() {
		return insuredEmail;
	}

	public void setInsuredEmail(String insuredEmail) {
		this.insuredEmail = insuredEmail;
	}

	public String getSpTitle() {
		return spTitle;
	}

	public void setSpTitle(String spTitle) {
		this.spTitle = spTitle;
	}

	public String getSpFirstName() {
		return spFirstName;
	}

	public void setSpFirstName(String spFirstName) {
		this.spFirstName = spFirstName;
	}

	public String getSpLastName() {
		return spLastName;
	}

	public void setSpLastName(String spLastName) {
		this.spLastName = spLastName;
	}

	public String getSpNational() {
		return spNational;
	}

	public void setSpNational(String spNational) {
		this.spNational = spNational;
	}

	public String getSpCitizenId() {
		return spCitizenId;
	}

	public void setSpCitizenId(String spCitizenId) {
		this.spCitizenId = spCitizenId;
	}

	public String getSpPassportId() {
		return spPassportId;
	}

	public void setSpPassportId(String spPassportId) {
		this.spPassportId = spPassportId;
	}

	public String getSpGender() {
		return spGender;
	}

	public void setSpGender(String spGender) {
		this.spGender = spGender;
	}

	public Date getSpDob() {
		return spDob;
	}

	public void setSpDob(Date spDob) {
		this.spDob = spDob;
	}

	public String getSpOccupation() {
		return spOccupation;
	}

	public void setSpOccupation(String spOccupation) {
		this.spOccupation = spOccupation;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public BigDecimal getBenefit() {
		return benefit;
	}

	public void setBenefit(BigDecimal benefit) {
		this.benefit = benefit;
	}

	public String getBillFrequency() {
		return billFrequency;
	}

	public void setBillFrequency(String billFrequency) {
		this.billFrequency = billFrequency;
	}

	public String getBene1FirstName() {
		return bene1FirstName;
	}

	public void setBene1FirstName(String bene1FirstName) {
		this.bene1FirstName = bene1FirstName;
	}

	public String getBene1LastName() {
		return bene1LastName;
	}

	public void setBene1LastName(String bene1LastName) {
		this.bene1LastName = bene1LastName;
	}

	public BigDecimal getBene1Percent() {
		return bene1Percent;
	}

	public void setBene1Percent(BigDecimal bene1Percent) {
		this.bene1Percent = bene1Percent;
	}

	public String getBene2FirstName() {
		return bene2FirstName;
	}

	public void setBene2FirstName(String bene2FirstName) {
		this.bene2FirstName = bene2FirstName;
	}

	public String getBene2LastName() {
		return bene2LastName;
	}

	public void setBene2LastName(String bene2LastName) {
		this.bene2LastName = bene2LastName;
	}

	public BigDecimal getBene2Percent() {
		return bene2Percent;
	}

	public void setBene2Percent(BigDecimal bene2Percent) {
		this.bene2Percent = bene2Percent;
	}

	public String getBene3FirstName() {
		return bene3FirstName;
	}

	public void setBene3FirstName(String bene3FirstName) {
		this.bene3FirstName = bene3FirstName;
	}

	public String getBene3LastName() {
		return bene3LastName;
	}

	public void setBene3LastName(String bene3LastName) {
		this.bene3LastName = bene3LastName;
	}

	public BigDecimal getBene3Percent() {
		return bene3Percent;
	}

	public void setBene3Percent(BigDecimal bene3Percent) {
		this.bene3Percent = bene3Percent;
	}

	public String getBene4FirstName() {
		return bene4FirstName;
	}

	public void setBene4FirstName(String bene4FirstName) {
		this.bene4FirstName = bene4FirstName;
	}

	public String getBene4LastName() {
		return bene4LastName;
	}

	public void setBene4LastName(String bene4LastName) {
		this.bene4LastName = bene4LastName;
	}

	public BigDecimal getBene4Percent() {
		return bene4Percent;
	}

	public void setBene4Percent(BigDecimal bene4Percent) {
		this.bene4Percent = bene4Percent;
	}

	public String getBene5FirstName()
	{
		return bene5FirstName;
	}

	public void setBene5FirstName(String bene5FirstName)
	{
		this.bene5FirstName = bene5FirstName;
	}

	public String getBene5LastName()
	{
		return bene5LastName;
	}

	public void setBene5LastName(String bene5LastName)
	{
		this.bene5LastName = bene5LastName;
	}

	public BigDecimal getBene5Percent()
	{
		return bene5Percent;
	}

	public void setBene5Percent(BigDecimal bene5Percent)
	{
		this.bene5Percent = bene5Percent;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReferenceNo()
	{
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo)
	{
		this.referenceNo = referenceNo;
	}

	public String getPayeeName()
	{
		return payeeName;
	}

	public void setPayeeName(String payeeName)
	{
		this.payeeName = payeeName;
	}

	public String getPayeeCitizenId()
	{
		return payeeCitizenId;
	}

	public void setPayeeCitizenId(String payeeCitizenId)
	{
		this.payeeCitizenId = payeeCitizenId;
	}

	public String getPayeeBankCode()
	{
		return payeeBankCode;
	}

	public void setPayeeBankCode(String payeeBankCode)
	{
		this.payeeBankCode = payeeBankCode;
	}

	public String getPayeeBranchCode()
	{
		return payeeBranchCode;
	}

	public void setPayeeBranchCode(String payeeBranchCode)
	{
		this.payeeBranchCode = payeeBranchCode;
	}

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getBenefitLevel()
	{
		return benefitLevel;
	}

	public void setBenefitLevel(String benefitLevel)
	{
		this.benefitLevel = benefitLevel;
	}

	public String getCoverCode()
	{
		return coverCode;
	}

	public void setCoverCode(String coverCode)
	{
		this.coverCode = coverCode;
	}

	public String getSequenceNo()
	{
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo)
	{
		this.sequenceNo = sequenceNo;
	}

	public String getBene1Title()
	{
		return bene1Title;
	}

	public void setBene1Title(String bene1Title)
	{
		this.bene1Title = bene1Title;
	}

	public String getBene1Age()
	{
		return bene1Age;
	}

	public void setBene1Age(String bene1Age)
	{
		this.bene1Age = bene1Age;
	}

	public String getBene1Relation()
	{
		return bene1Relation;
	}

	public void setBene1Relation(String bene1Relation)
	{
		this.bene1Relation = bene1Relation;
	}

	public String getBene2Title()
	{
		return bene2Title;
	}

	public void setBene2Title(String bene2Title)
	{
		this.bene2Title = bene2Title;
	}

	public String getBene2Age()
	{
		return bene2Age;
	}

	public void setBene2Age(String bene2Age)
	{
		this.bene2Age = bene2Age;
	}

	public String getBene2Relation()
	{
		return bene2Relation;
	}

	public void setBene2Relation(String bene2Relation)
	{
		this.bene2Relation = bene2Relation;
	}

	public String getBene3Title()
	{
		return bene3Title;
	}

	public void setBene3Title(String bene3Title)
	{
		this.bene3Title = bene3Title;
	}

	public String getBene3Age()
	{
		return bene3Age;
	}

	public void setBene3Age(String bene3Age)
	{
		this.bene3Age = bene3Age;
	}

	public String getBene3Relation()
	{
		return bene3Relation;
	}

	public void setBene3Relation(String bene3Relation)
	{
		this.bene3Relation = bene3Relation;
	}

	public String getBene4Title()
	{
		return bene4Title;
	}

	public void setBene4Title(String bene4Title)
	{
		this.bene4Title = bene4Title;
	}

	public String getBene4Age()
	{
		return bene4Age;
	}

	public void setBene4Age(String bene4Age)
	{
		this.bene4Age = bene4Age;
	}

	public String getBene4Relation()
	{
		return bene4Relation;
	}

	public void setBene4Relation(String bene4Relation)
	{
		this.bene4Relation = bene4Relation;
	}

	public String getBene5Title()
	{
		return bene5Title;
	}

	public void setBene5Title(String bene5Title)
	{
		this.bene5Title = bene5Title;
	}

	public String getBene5Age()
	{
		return bene5Age;
	}

	public void setBene5Age(String bene5Age)
	{
		this.bene5Age = bene5Age;
	}

	public String getBene5Relation()
	{
		return bene5Relation;
	}

	public void setBene5Relation(String bene5Relation)
	{
		this.bene5Relation = bene5Relation;
	}

	public String getOptOut()
	{
		return optOut;
	}

	public void setOptOut(String optOut)
	{
		this.optOut = optOut;
	}

	public String getInsuredAnnualIncome()
	{
		return insuredAnnualIncome;
	}

	public void setInsuredAnnualIncome(String insuredAnnualIncome)
	{
		this.insuredAnnualIncome = insuredAnnualIncome;
	}

	public String getInsuredEducationLevel()
	{
		return insuredEducationLevel;
	}

	public void setInsuredEducationLevel(String insuredEducationLevel)
	{
		this.insuredEducationLevel = insuredEducationLevel;
	}

	public String getFatca()
	{
		return fatca;
	}

	public void setFatca(String fatca)
	{
		this.fatca = fatca;
	}

	public String getFatcaDetail()
	{
		return fatcaDetail;
	}

	public void setFatcaDetail(String fatcaDetail)
	{
		this.fatcaDetail = fatcaDetail;
	}

	public String getPolicyNo()
	{
		return policyNo;
	}

	public void setPolicyNo(String policyNo)
	{
		this.policyNo = policyNo;
	}

	public String getCampaignName()
	{
		return campaignName;
	}

	public void setCampaignName(String campaignName)
	{
		this.campaignName = campaignName;
	}

	@Override
	public String toString()
	{
		return "CustomerYesRecord [id=" + id + ", importDate=" + importDate + ", referenceNo=" + referenceNo + ", policyNo=" + policyNo + ", customer=" + customer + ", insuredOccupation=" + insuredOccupation + ", insuredBankCode=" + insuredBankCode + ", insuredAccountType=" + insuredAccountType
				+ ", insuredAccountNo=" + insuredAccountNo + ", insuredAccountExpiredDate=" + insuredAccountExpiredDate + ", insuredAddress1=" + insuredAddress1 + ", insuredAddress2=" + insuredAddress2 + ", insuredAddress3=" + insuredAddress3 + ", insuredAddress4=" + insuredAddress4
				+ ", insuredSubDistrict=" + insuredSubDistrict + ", insuredDistrict=" + insuredDistrict + ", insuredProvince=" + insuredProvince + ", insuredPostCode=" + insuredPostCode + ", insuredCountry=" + insuredCountry + ", insuredMobileNo=" + insuredMobileNo + ", insuredEmail=" + insuredEmail
				+ ", spTitle=" + spTitle + ", spFirstName=" + spFirstName + ", spLastName=" + spLastName + ", spNational=" + spNational + ", spCitizenId=" + spCitizenId + ", spPassportId=" + spPassportId + ", spGender=" + spGender + ", spDob=" + spDob + ", spOccupation=" + spOccupation
				+ ", premium=" + premium + ", benefit=" + benefit + ", billFrequency=" + billFrequency + ", bene1FirstName=" + bene1FirstName + ", bene1LastName=" + bene1LastName + ", bene1Percent=" + bene1Percent + ", bene2FirstName=" + bene2FirstName + ", bene2LastName=" + bene2LastName
				+ ", bene2Percent=" + bene2Percent + ", bene3FirstName=" + bene3FirstName + ", bene3LastName=" + bene3LastName + ", bene3Percent=" + bene3Percent + ", bene4FirstName=" + bene4FirstName + ", bene4LastName=" + bene4LastName + ", bene4Percent=" + bene4Percent + ", bene5FirstName="
				+ bene5FirstName + ", bene5LastName=" + bene5LastName + ", bene5Percent=" + bene5Percent + ", effectiveDate=" + effectiveDate + ", payeeName=" + payeeName + ", payeeCitizenId=" + payeeCitizenId + ", payeeBankCode=" + payeeBankCode + ", payeeBranchCode=" + payeeBranchCode
				+ ", productCode=" + productCode + ", benefitLevel=" + benefitLevel + ", coverCode=" + coverCode + ", sequenceNo=" + sequenceNo + ", bene1Title=" + bene1Title + ", bene1Age=" + bene1Age + ", bene1Relation=" + bene1Relation + ", bene2Title=" + bene2Title + ", bene2Age=" + bene2Age
				+ ", bene2Relation=" + bene2Relation + ", bene3Title=" + bene3Title + ", bene3Age=" + bene3Age + ", bene3Relation=" + bene3Relation + ", bene4Title=" + bene4Title + ", bene4Age=" + bene4Age + ", bene4Relation=" + bene4Relation + ", bene5Title=" + bene5Title + ", bene5Age=" + bene5Age
				+ ", bene5Relation=" + bene5Relation + ", optOut=" + optOut + ", campaignName=" + campaignName + ", insuredAnnualIncome=" + insuredAnnualIncome + ", insuredEducationLevel=" + insuredEducationLevel + ", fatca=" + fatca + ", fatcaDetail=" + fatcaDetail + "]";
	}

}
