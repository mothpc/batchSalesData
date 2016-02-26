package com.adms.batch.sales.data.partner.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.adms.utils.DateUtil;

public class YesRecordMTLModel implements Serializable {
	
	private static final long serialVersionUID = 1205658517176332298L;

	private String sequenceNo;
	
	private Integer recordType;
	
	private String title;
	
	private String firstName;
	
	private String lastName;
	
	private String sex;
	
	private Date dob;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	private String address4;
	
	private String homeTel;
	
	private String officeTel;
	
	private String postCode;
	
	private String idCard;
	
	private String accountNo;
	
	private String expiryDate;
	
	private String accountType;
	
	private Date salesOrderDate;
	
	private Date policyEffectiveDate;
	
	private String productCode;
	
	private String benefitLevel;
	
	private String coverCode;
	
	private String paymentFrequency;
	
	private String campaignCode;
	
	private String tsrCode;
	
	private String xReferenceNo;
	
	private String mobileNo;
	
	private String faxNo;
	
	private String email;
	
	private String optOut;
	
	private String policyNo;
	
	private String transactionEffectiveDate;
	
	private String responderId;
	
	private String maritalStatus;
	
	private String preferedCommunicationsChannel;
	
	private Double beneficiaryPercentage;
	
	private Integer beneficiaryAge;
	
	private String beneficaryRelationship;
	
	private String spouseIndiator;
	
	private Double annualIncome;
	
	private String applicationType;
	
	private String healthQuestion;
	
	private String dependantsNoOfDependants;
	
	private String employmentStatus;
	
	private String educationLevel;
	
	private String occupationalCategory;
	
	private String numberOfPeriods;
	
	private String payeeName;
	
	private String payeeIdCardNo;
	
	private String bankCode;
	
	private String branchCode;
	
	private String keyCode;
	
	private String contactReferCode1;
	
	private String contactReferCode2;
	
	private Double policyPremium;
	
	private Date idCardExpire;
	
	private String fatcaQ1;
	
	private String fatcaQ2;
	
	private String fatcaQ3;
	
	private String fatcaQ4;
	
	private String fatcaQ5;
	
	private String fatcaQ6;
	
	private String fatcaQ1Detail;
	
	private String fatcaQ2Detail;
	
	private String fatcaQ3Detail;
	
	public YesRecordMTLModel(String[] data) throws Exception
	{
		this.sequenceNo = data[0];
		this.recordType = Integer.valueOf(data[1]);
		this.title = data[2];
		this.firstName = data[3];
		this.lastName = data[4];
		this.sex = data[5];
		this.dob = StringUtils.isBlank(data[6]) ? null : DateUtil.convStringToDate("dd/MM/yyyy", data[6]);
		this.address1 = data[7];
		this.address2 = data[8];
		this.address3 = data[9];
		this.address4 = data[10];
		this.homeTel = StringUtils.isNotBlank(data[11]) ? data[11].replaceAll("-", "") : null;
		this.officeTel = StringUtils.isNotBlank(data[12]) ? data[12].replaceAll("-", "") : null;
		this.postCode = data[13];
		this.idCard = data[14];
		this.accountNo = data[15];
		this.expiryDate = data[16];
		this.accountType = data[17];
		this.salesOrderDate = StringUtils.isBlank(data[18]) ? null : DateUtil.convStringToDate("dd/MM/yyyy", data[18]);
		this.policyEffectiveDate = StringUtils.isBlank(data[19]) ? null : DateUtil.convStringToDate("dd/MM/yyyy", data[19]);
		this.productCode = data[20];
		this.benefitLevel = data[21];
		this.coverCode = data[22];
		this.paymentFrequency = data[23];
		this.campaignCode = data[24];
		this.tsrCode = data[25];
		this.xReferenceNo = data[26];
		this.mobileNo = StringUtils.isNotBlank(data[27]) ? data[27].replaceAll("-", "") : null;
		this.faxNo = StringUtils.isNotBlank(data[28]) ? data[28].replaceAll("-", "") : null;
		this.email = data[29];
		this.optOut = data[30];
		this.policyNo = data[31];
		this.transactionEffectiveDate = data[32];
		this.responderId = data[33];
		this.maritalStatus = data[34];
		this.preferedCommunicationsChannel = data[35];
		this.beneficiaryPercentage = StringUtils.isBlank(data[36]) ? null : Double.valueOf(data[36]);
		this.beneficiaryAge = StringUtils.isBlank(data[37]) ? null : Integer.valueOf(data[37]);
		this.beneficaryRelationship = data[38];
		this.spouseIndiator = data[39];
		this.annualIncome = StringUtils.isBlank(data[40]) ? null : Double.valueOf(data[40]);
		this.applicationType = data[41];
		this.healthQuestion = data[42];
		this.dependantsNoOfDependants = data[43];
		this.employmentStatus = data[44];
		this.educationLevel = data[45];
		this.occupationalCategory = data[46];
		this.numberOfPeriods = data[47];
		this.payeeName = data[48];
		this.payeeIdCardNo = data[49];
		this.bankCode = data[50];
		this.branchCode = data[51];
		this.keyCode = data[52];
		this.contactReferCode1 = data[53];
		this.contactReferCode2 = data[54];
		this.policyPremium = StringUtils.isBlank(data[55]) ? null : Double.valueOf(data[55]);
		this.idCardExpire = StringUtils.isBlank(data[56]) ? null : DateUtil.convStringToDate("dd/MM/yyyy", data[56]);
		this.fatcaQ1 = data[57];
		this.fatcaQ2 = data[58];
		this.fatcaQ3 = data[59];
		this.fatcaQ4 = data[60];
		this.fatcaQ5 = data[61];
		this.fatcaQ6 = data[62];
		this.fatcaQ1Detail = data[63];
		this.fatcaQ2Detail = data[64];
		this.fatcaQ3Detail = data[65];
	}

	public String getSequenceNo()
	{
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo)
	{
		this.sequenceNo = sequenceNo;
	}

	public Integer getRecordType()
	{
		return recordType;
	}

	public void setRecordType(Integer recordType)
	{
		this.recordType = recordType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getAddress3()
	{
		return address3;
	}

	public void setAddress3(String address3)
	{
		this.address3 = address3;
	}

	public String getAddress4()
	{
		return address4;
	}

	public void setAddress4(String address4)
	{
		this.address4 = address4;
	}

	public String getHomeTel()
	{
		return homeTel;
	}

	public void setHomeTel(String homeTel)
	{
		this.homeTel = homeTel;
	}

	public String getOfficeTel()
	{
		return officeTel;
	}

	public void setOfficeTel(String officeTel)
	{
		this.officeTel = officeTel;
	}

	public String getPostCode()
	{
		return postCode;
	}

	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	public String getIdCard()
	{
		return idCard;
	}

	public void setIdCard(String idCard)
	{
		this.idCard = idCard;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public Date getSalesOrderDate()
	{
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate)
	{
		this.salesOrderDate = salesOrderDate;
	}

	public Date getPolicyEffectiveDate()
	{
		return policyEffectiveDate;
	}

	public void setPolicyEffectiveDate(Date policyEffectiveDate)
	{
		this.policyEffectiveDate = policyEffectiveDate;
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

	public String getPaymentFrequency()
	{
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency)
	{
		this.paymentFrequency = paymentFrequency;
	}

	public String getCampaignCode()
	{
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode)
	{
		this.campaignCode = campaignCode;
	}

	public String getTsrCode()
	{
		return tsrCode;
	}

	public void setTsrCode(String tsrCode)
	{
		this.tsrCode = tsrCode;
	}

	public String getxReferenceNo()
	{
		return xReferenceNo;
	}

	public void setxReferenceNo(String xReferenceNo)
	{
		this.xReferenceNo = xReferenceNo;
	}

	public String getMobileNo()
	{
		return mobileNo;
	}

	public void setMobileNo(String mobileNo)
	{
		this.mobileNo = mobileNo;
	}

	public String getFaxNo()
	{
		return faxNo;
	}

	public void setFaxNo(String faxNo)
	{
		this.faxNo = faxNo;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getOptOut()
	{
		return optOut;
	}

	public void setOptOut(String optOut)
	{
		this.optOut = optOut;
	}

	public String getPolicyNo()
	{
		return policyNo;
	}

	public void setPolicyNo(String policyNo)
	{
		this.policyNo = policyNo;
	}

	public String getTransactionEffectiveDate()
	{
		return transactionEffectiveDate;
	}

	public void setTransactionEffectiveDate(String transactionEffectiveDate)
	{
		this.transactionEffectiveDate = transactionEffectiveDate;
	}

	public String getResponderId()
	{
		return responderId;
	}

	public void setResponderId(String responderId)
	{
		this.responderId = responderId;
	}

	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	public String getPreferedCommunicationsChannel()
	{
		return preferedCommunicationsChannel;
	}

	public void setPreferedCommunicationsChannel(String preferedCommunicationsChannel)
	{
		this.preferedCommunicationsChannel = preferedCommunicationsChannel;
	}

	public Double getBeneficiaryPercentage()
	{
		return beneficiaryPercentage;
	}

	public void setBeneficiaryPercentage(Double beneficiaryPercentage)
	{
		this.beneficiaryPercentage = beneficiaryPercentage;
	}

	public Integer getBeneficiaryAge()
	{
		return beneficiaryAge;
	}

	public void setBeneficiaryAge(Integer beneficiaryAge)
	{
		this.beneficiaryAge = beneficiaryAge;
	}

	public String getBeneficaryRelationship()
	{
		return beneficaryRelationship;
	}

	public void setBeneficaryRelationship(String beneficaryRelationship)
	{
		this.beneficaryRelationship = beneficaryRelationship;
	}

	public String getSpouseIndiator()
	{
		return spouseIndiator;
	}

	public void setSpouseIndiator(String spouseIndiator)
	{
		this.spouseIndiator = spouseIndiator;
	}

	public Double getAnnualIncome()
	{
		return annualIncome;
	}

	public void setAnnualIncome(Double annualIncome)
	{
		this.annualIncome = annualIncome;
	}

	public String getApplicationType()
	{
		return applicationType;
	}

	public void setApplicationType(String applicationType)
	{
		this.applicationType = applicationType;
	}

	public String getHealthQuestion()
	{
		return healthQuestion;
	}

	public void setHealthQuestion(String healthQuestion)
	{
		this.healthQuestion = healthQuestion;
	}

	public String getDependantsNoOfDependants()
	{
		return dependantsNoOfDependants;
	}

	public void setDependantsNoOfDependants(String dependantsNoOfDependants)
	{
		this.dependantsNoOfDependants = dependantsNoOfDependants;
	}

	public String getEmploymentStatus()
	{
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus)
	{
		this.employmentStatus = employmentStatus;
	}

	public String getEducationLevel()
	{
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel)
	{
		this.educationLevel = educationLevel;
	}

	public String getOccupationalCategory()
	{
		return occupationalCategory;
	}

	public void setOccupationalCategory(String occupationalCategory)
	{
		this.occupationalCategory = occupationalCategory;
	}

	public String getNumberOfPeriods()
	{
		return numberOfPeriods;
	}

	public void setNumberOfPeriods(String numberOfPeriods)
	{
		this.numberOfPeriods = numberOfPeriods;
	}

	public String getPayeeName()
	{
		return payeeName;
	}

	public void setPayeeName(String payeeName)
	{
		this.payeeName = payeeName;
	}

	public String getPayeeIdCardNo()
	{
		return payeeIdCardNo;
	}

	public void setPayeeIdCardNo(String payeeIdCardNo)
	{
		this.payeeIdCardNo = payeeIdCardNo;
	}

	public String getBankCode()
	{
		return bankCode;
	}

	public void setBankCode(String bankCode)
	{
		this.bankCode = bankCode;
	}

	public String getBranchCode()
	{
		return branchCode;
	}

	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}

	public String getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(String keyCode)
	{
		this.keyCode = keyCode;
	}

	public String getContactReferCode1()
	{
		return contactReferCode1;
	}

	public void setContactReferCode1(String contactReferCode1)
	{
		this.contactReferCode1 = contactReferCode1;
	}

	public String getContactReferCode2()
	{
		return contactReferCode2;
	}

	public void setContactReferCode2(String contactReferCode2)
	{
		this.contactReferCode2 = contactReferCode2;
	}

	public Double getPolicyPremium()
	{
		return policyPremium;
	}

	public void setPolicyPremium(Double policyPremium)
	{
		this.policyPremium = policyPremium;
	}

	public Date getIdCardExpire()
	{
		return idCardExpire;
	}

	public void setIdCardExpire(Date idCardExpire)
	{
		this.idCardExpire = idCardExpire;
	}

	public String getFatcaQ1()
	{
		return fatcaQ1;
	}

	public void setFatcaQ1(String fatcaQ1)
	{
		this.fatcaQ1 = fatcaQ1;
	}

	public String getFatcaQ2()
	{
		return fatcaQ2;
	}

	public void setFatcaQ2(String fatcaQ2)
	{
		this.fatcaQ2 = fatcaQ2;
	}

	public String getFatcaQ3()
	{
		return fatcaQ3;
	}

	public void setFatcaQ3(String fatcaQ3)
	{
		this.fatcaQ3 = fatcaQ3;
	}

	public String getFatcaQ4()
	{
		return fatcaQ4;
	}

	public void setFatcaQ4(String fatcaQ4)
	{
		this.fatcaQ4 = fatcaQ4;
	}

	public String getFatcaQ5()
	{
		return fatcaQ5;
	}

	public void setFatcaQ5(String fatcaQ5)
	{
		this.fatcaQ5 = fatcaQ5;
	}

	public String getFatcaQ6()
	{
		return fatcaQ6;
	}

	public void setFatcaQ6(String fatcaQ6)
	{
		this.fatcaQ6 = fatcaQ6;
	}

	public String getFatcaQ1Detail()
	{
		return fatcaQ1Detail;
	}

	public void setFatcaQ1Detail(String fatcaQ1Detail)
	{
		this.fatcaQ1Detail = fatcaQ1Detail;
	}

	public String getFatcaQ2Detail()
	{
		return fatcaQ2Detail;
	}

	public void setFatcaQ2Detail(String fatcaQ2Detail)
	{
		this.fatcaQ2Detail = fatcaQ2Detail;
	}

	public String getFatcaQ3Detail()
	{
		return fatcaQ3Detail;
	}

	public void setFatcaQ3Detail(String fatcaQ3Detail)
	{
		this.fatcaQ3Detail = fatcaQ3Detail;
	}

	@Override
	public String toString()
	{
		return "YesRecordMTLModel [sequenceNo=" + sequenceNo + ", recordType=" + recordType + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", sex=" + sex + ", dob=" + dob + ", address1=" + address1 + ", address2=" + address2 + ", address3=" + address3 + ", address4="
				+ address4 + ", homeTel=" + homeTel + ", officeTel=" + officeTel + ", postCode=" + postCode + ", idCard=" + idCard + ", accountNo=" + accountNo + ", expiryDate=" + expiryDate + ", accountType=" + accountType + ", salesOrderDate=" + salesOrderDate + ", policyEffectiveDate="
				+ policyEffectiveDate + ", productCode=" + productCode + ", benefitLevel=" + benefitLevel + ", coverCode=" + coverCode + ", paymentFrequency=" + paymentFrequency + ", campaignCode=" + campaignCode + ", tsrCode=" + tsrCode + ", xReferenceNo=" + xReferenceNo + ", mobileNo=" + mobileNo
				+ ", faxNo=" + faxNo + ", email=" + email + ", optOut=" + optOut + ", policyNo=" + policyNo + ", transactionEffectiveDate=" + transactionEffectiveDate + ", responderId=" + responderId + ", maritalStatus=" + maritalStatus + ", preferedCommunicationsChannel="
				+ preferedCommunicationsChannel + ", beneficiaryPercentage=" + beneficiaryPercentage + ", beneficiaryAge=" + beneficiaryAge + ", beneficaryRelationship=" + beneficaryRelationship + ", spouseIndiator=" + spouseIndiator + ", annualIncome=" + annualIncome + ", applicationType="
				+ applicationType + ", healthQuestion=" + healthQuestion + ", dependantsNoOfDependants=" + dependantsNoOfDependants + ", employmentStatus=" + employmentStatus + ", educationLevel=" + educationLevel + ", occupationalCategory=" + occupationalCategory + ", numberOfPeriods="
				+ numberOfPeriods + ", payeeName=" + payeeName + ", payeeIdCardNo=" + payeeIdCardNo + ", bankCode=" + bankCode + ", branchCode=" + branchCode + ", keyCode=" + keyCode + ", contactReferCode1=" + contactReferCode1 + ", contactReferCode2=" + contactReferCode2 + ", policyPremium="
				+ policyPremium + ", idCardExpire=" + idCardExpire + ", fatcaQ1=" + fatcaQ1 + ", fatcaQ2=" + fatcaQ2 + ", fatcaQ3=" + fatcaQ3 + ", fatcaQ4=" + fatcaQ4 + ", fatcaQ5=" + fatcaQ5 + ", fatcaQ6=" + fatcaQ6 + ", fatcaQ1Detail=" + fatcaQ1Detail + ", fatcaQ2Detail=" + fatcaQ2Detail
				+ ", fatcaQ3Detail=" + fatcaQ3Detail + "]";
	}
	
}
