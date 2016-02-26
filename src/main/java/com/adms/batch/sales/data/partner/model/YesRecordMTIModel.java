package com.adms.batch.sales.data.partner.model;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.adms.utils.DateUtil;

public class YesRecordMTIModel {

	private String recordType;
	
	private String agencyId;
	
	private String locationCode;
	
	private String campaignNumber;
	
	private String keyCode;
	
	private String fileOwnerCode;
	
	private String accountType;
	
	private String creditCardAccNo;
	
	private String creditCardExpiryDate;
	
	private String bankAccNo;
	
	private String bankName;
	
	private String bankCode1;
	
	private String bankCode2;
	
	private String primaryInsuredTitile;
	
	private String primaryInsuredFirstName;
	
	private String primaryNameOnCard;
	
	private String primaryInsuredLastName;
	
	private Date primaryInsuredDob;
	
	private String primaryInsuredGender;
	
	private String primaryInsuredOccupationCode;
	
	private String primaryIdCard;
	
	private String primaryNameChanged;
	
	private String primaryMaritalStatus;
	
	private String primaryIncome;
	
	private String piBuildingNo;
	
	private String piBuildingName;
	
	private String piRoomFloor;
	
	private String moo;
	
	private String mooBaan;
	
	private String soi;
	
	private String streetName;
	
	private String subDistrict;
	
	private String district;
	
	private String province;
	
	private String postCode;
	
	private String areaCode;

	private String phoneIndicator;
	
	private String phoneCountryCode;
	
	private String phoneAreaCode;
	
	private String phoneExtNo;
	
	private String phoneNo;

	private String phoneIndicatorAlt;
	
	private String phoneCountryCodeAlt;
	
	private String phoneAreaCodeAlt;
	
	private String phoneExtNoAlt;
	
	private String phoneNoAlt;
	
	private String preferredPhoneNoToContact;
	
	private String spouseTitle;
	
	private String spouseFirstName;
	
	private String spouseMidName;
	
	private String spouseLastName;
	
	private Date spouseDob;
	
	private String spouseGender;
	
	private String spouseInsuredOccCode;
	
	private String spouseIdCard;
	
	private String dependentTitle1;
	
	private String dependentFirstName1;
	
	private String dependentOccCode1;
	
	private String dependentRelation1;
	
	private String dependentLastName1;
	
	private Date dependentDob1;
	
	private String dependentGender1;
	
	private String dependentIdCard1;
	
	private String dependentTitle2;
	
	private String dependentFirstName2;
	
	private String dependentOccCode2;
	
	private String dependentRelation2;
	
	private String dependentLastName2;
	
	private Date dependentDob2;
	
	private String dependentGender2;
	
	private String dependentIdCard2;
	
	private String dependentTitle3;
	
	private String dependentFirstName3;
	
	private String dependentOccCode3;
	
	private String dependentRelation3;
	
	private String dependentLastName3;
	
	private Date dependentDob3;
	
	private String dependentGender3;
	
	private String dependentIdCard3;
	
	private String dependentTitle4;
	
	private String dependentFirstName4;
	
	private String dependentOccCode4;
	
	private String dependentRelation4;
	
	private String dependentLastName4;
	
	private Date dependentDob4;
	
	private String dependentGender4;
	
	private String dependentIdCard4;
	
	private String dependentTitle5;
	
	private String dependentFirstName5;
	
	private String dependentOccCode5;
	
	private String dependentRelation5;
	
	private String dependentLastName5;
	
	private Date dependentDob5;
	
	private String dependentGender5;
	
	private String dependentIdCard5;
	
	private String numOfDependents;
	
	private String partnerCustomerUniqId;
	
	private String languagePref;
	
	private String marketCode;
	
	private String countryIdentifier;
	
	private String productLineCode;
	
	private String soldPlanCode;
	
	private String soldSequenceNum;
	
	private String soldOptionCode;
	
	private String soldOptionCategory;
	
	private String soldPremium;
	
	private String soldBenefitAmt;
	
	private String soldBillingMode;
	
	private String soldBillingFreq;
	
	private String contactFirstName;
	
	private String contactLastName;
	
	private String contactRelation;
	
	private String tmrCode;
	
	private Date callDate;
	
	private Date callStartTime;
	
	private String calloutCome;
	
	private String beneficiaryName1;
	
	private String beneficiaryRelation1;
	
	private String beneficiaryPercent1;
	
	private String beneficiaryName2;
	
	private String beneficiaryRelation2;
	
	private String beneficiaryPercent2;
	
	private String beneficiaryName3;
	
	private String beneficiaryRelation3;
	
	private String beneficiaryPercent3;
	
	private String beneficiaryName4;
	
	private String beneficiaryRelation4;
	
	private String beneficiaryPercent4;
	
	private String beneficiaryName5;
	
	private String beneficiaryRelation5;
	
	private String beneficiaryPercent5;
	
	private String policyNo;
	
	private String marketingOffer;
	
	private String telephoneSourceCode;
	
	private Date effectiveDate;
	
	private Date effectiveTime;
	
	private String rejectedFlag;
	
	private String bankRefCode;
	
	private String bankRefDate;
	
	private String piWeight;
	
	private String piHeight;
	
	private String uniqueId;
	
	private String kbankCampaignCode;
	
	private String policyNumber;
	
	private String issueDate;
	
	private String policyStatus;
	
	private String subOutcome;
	
	private String insuredOccDesc;
	
	private String spouseOccDesc;
	
	private Integer numberOfPolicy;
	
	private String summarySumInsured;
	
	private Integer spouseNumberOfPolicy;
	
	private String spouseSummarySumInsured;
	
	private String kbankOutcome;
	
	private String kbankSubOutcome;
	
	private String kbankContact;
	
	private String qcCode;
	
	private String subCode;
	
	public YesRecordMTIModel(String line) throws Exception {
		this.recordType = line.substring(0, 2).trim();
		this.agencyId = line.substring(2, 4).trim();
		this.locationCode = line.substring(4, 6).trim();
		
		this.campaignNumber = line.substring(7, 19).trim();
		this.keyCode = line.substring(19, 24).trim();
		this.fileOwnerCode = line.substring(24, 29).trim();
		
		this.accountType = line.substring(46, 47).trim();
		this.creditCardAccNo = line.substring(47, 63).trim();
		this.creditCardExpiryDate = line.substring(63, 67).trim();
		
		this.bankAccNo = line.substring(67, 83).trim();
		this.bankName = line.substring(84, 88).trim();
		this.bankCode1 = line.substring(88, 93).trim();
		this.bankCode2 = line.substring(93, 98).trim();
		
		this.primaryInsuredTitile = line.substring(98, 119).trim();
		this.primaryInsuredFirstName = line.substring(119, 159).trim();
		this.primaryNameOnCard = line.substring(159, 199).trim();
		this.primaryInsuredLastName = line.substring(199, 239).trim();
		this.primaryInsuredDob = getDateWithPattern("ddMMyyyy", line.substring(239, 247).trim());
		this.primaryInsuredGender = line.substring(247, 248).trim();
		this.primaryInsuredOccupationCode = line.substring(248, 251).trim();
		this.primaryIdCard = line.substring(251, 264).trim();
		this.primaryNameChanged = line.substring(264, 267).trim();
		this.primaryMaritalStatus = line.substring(267, 268).trim();
		this.primaryIncome = line.substring(268, 270).trim();
		
		this.piBuildingNo = line.substring(277, 302).trim();
		this.piBuildingName = line.substring(302, 402).trim();
		this.piRoomFloor = line.substring(402, 405).trim();
		
		this.moo = line.substring(405, 415).trim();
		this.mooBaan = line.substring(415, 445).trim();
		this.soi = line.substring(445, 495).trim();
		this.streetName = line.substring(495, 595).trim();
		this.subDistrict = line.substring(595, 645).trim();
		this.district = line.substring(645, 695).trim();
		this.province = line.substring(695, 720).trim();
		this.postCode = line.substring(720, 728).trim();
		
		this.phoneIndicator = line.substring(738, 739).trim();
		this.phoneCountryCode = line.substring(739, 742).trim();
		this.phoneAreaCode = line.substring(742, 745).trim();
		this.phoneExtNo = line.substring(745, 750).trim();
		this.phoneNo = line.substring(750, 758).trim();
		
		this.phoneIndicatorAlt = line.substring(758, 759).trim();
		this.phoneCountryCodeAlt = line.substring(759, 762).trim();
		this.phoneAreaCodeAlt = line.substring(762, 765).trim();
		this.phoneExtNoAlt = line.substring(765, 770).trim();
		this.phoneNoAlt = line.substring(770, 779).trim();
		
		this.preferredPhoneNoToContact = line.substring(778, 779).trim();
		
		this.spouseTitle = line.substring(779, 800).trim();
		this.spouseFirstName = line.substring(800, 840).trim();
		this.spouseMidName = line.substring(840, 880).trim();
		this.spouseLastName = line.substring(880, 920).trim();
		this.spouseDob = getDateWithPattern("ddMMyyyy", line.substring(920, 928).trim());
		this.spouseGender = line.substring(928, 929).trim();
		this.spouseInsuredOccCode = line.substring(929, 932).trim();
		this.spouseIdCard = line.substring(932, 945).trim();
		
		this.dependentTitle1 = line.substring(945, 966).trim();
		this.dependentFirstName1 = line.substring(966, 1006).trim();
		this.dependentOccCode1 = line.substring(1006, 1009).trim();
		this.dependentRelation1 = line.substring(1009, 1010).trim();
		this.dependentLastName1 = line.substring(1046, 1086).trim();
		this.dependentDob1 = getDateWithPattern("ddMMyyyy", line.substring(1086, 1094).trim());
		this.dependentGender1 = line.substring(1094, 1095).trim();
		this.dependentIdCard1 = line.substring(1095, 1108).trim();
		
		this.numOfDependents = line.substring(1760, 1762).trim();
		
		this.countryIdentifier = line.substring(1784, 1787).trim();
		this.productLineCode = line.substring(1787, 1788).trim();
		
		this.soldPlanCode = line.substring(1788, 1794).trim();
		this.soldSequenceNum = line.substring(1794, 1797).trim();
		this.soldOptionCode = line.substring(1797, 1799).trim();
		this.soldOptionCategory = line.substring(1799, 1800).trim();
		this.soldPremium = line.substring(1800, 1814).trim();
		this.soldBenefitAmt = line.substring(1814, 1828).trim();
		this.soldBillingMode = line.substring(1828, 1829).trim();
		this.soldBillingFreq = line.substring(1829, 1830).trim();
		
		this.contactFirstName = line.substring(1830, 1870).trim();
		this.contactLastName = line.substring(1870, 1910).trim();
		this.contactRelation = line.substring(1910, 1911).trim();
		
		this.tmrCode = line.substring(1911, 1915).trim();
		this.callDate = getDateWithPattern("ddMMyyyy", line.substring(1915, 1923));
		this.callStartTime = getDateWithPattern("HHmmss", line.substring(1923, 1929));
		this.calloutCome = line.substring(1929, 1931).trim();
		
		this.beneficiaryName1 = line.substring(1931, 2031).trim();
		this.beneficiaryRelation1 = line.substring(2031, 2032).trim();
		this.beneficiaryPercent1 = line.substring(2032, 2035).trim();
		
		this.beneficiaryName2 = line.substring(2035, 2135).trim();
		this.beneficiaryRelation2 = line.substring(2135, 2136).trim();
		this.beneficiaryPercent2 = line.substring(2136, 2139).trim();
		
		this.beneficiaryName3 = line.substring(2139, 2239).trim();
		this.beneficiaryRelation3 = line.substring(2239, 2240).trim();
		this.beneficiaryPercent3 = line.substring(2240, 2243).trim();
		
		this.beneficiaryName4 = line.substring(2243, 2343).trim();
		this.beneficiaryRelation4 = line.substring(2343, 2344).trim();
		this.beneficiaryPercent4 = line.substring(2344, 2347).trim();
		
		this.beneficiaryName5 = line.substring(2347, 2447).trim();
		this.beneficiaryRelation5 = line.substring(2447, 2448).trim();
		this.beneficiaryPercent5 = line.substring(2448, 2451).trim();
		
		this.policyNo = line.substring(2596, 2616).trim();
		this.effectiveDate = getDateWithPattern("ddMMyyyyHHmmss", line.substring(2618, 2626) + line.substring(2626, 2632));
		
		this.bankRefCode = line.substring(2634, 2644).trim();
		this.bankRefDate = line.substring(2644, 2652).trim();
		
		this.piWeight = line.substring(2692, 2695).trim();
		this.piHeight = line.substring(2695, 2698).trim();
		
		this.uniqueId = line.substring(3712, 3728).trim();
		this.kbankCampaignCode = line.substring(3732, 3742).trim();
		this.policyNumber = line.substring(3742, 3762).trim();
		
		this.issueDate = line.substring(3762, 3770).trim();
		this.policyStatus = line.substring(3770, 3772).trim();
	}

	private Date getDateWithPattern(String pattern, String dateStr) throws ParseException {
		return StringUtils.isBlank(dateStr) ? null : DateUtil.convStringToDate(pattern, dateStr.trim());
	}
	
	public String getRecordType()
	{
		return recordType;
	}

	public void setRecordType(String recordType)
	{
		this.recordType = recordType;
	}

	public String getAgencyId()
	{
		return agencyId;
	}

	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	public String getLocationCode()
	{
		return locationCode;
	}

	public void setLocationCode(String locationCode)
	{
		this.locationCode = locationCode;
	}

	public String getCampaignNumber()
	{
		return campaignNumber;
	}

	public void setCampaignNumber(String campaignNumber)
	{
		this.campaignNumber = campaignNumber;
	}

	public String getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(String keyCode)
	{
		this.keyCode = keyCode;
	}

	public String getFileOwnerCode()
	{
		return fileOwnerCode;
	}

	public void setFileOwnerCode(String fileOwnerCode)
	{
		this.fileOwnerCode = fileOwnerCode;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getCreditCardAccNo()
	{
		return creditCardAccNo;
	}

	public void setCreditCardAccNo(String creditCardAccNo)
	{
		this.creditCardAccNo = creditCardAccNo;
	}

	public String getCreditCardExpiryDate()
	{
		return creditCardExpiryDate;
	}

	public void setCreditCardExpiryDate(String creditCardExpiryDate)
	{
		this.creditCardExpiryDate = creditCardExpiryDate;
	}

	public String getBankAccNo()
	{
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo)
	{
		this.bankAccNo = bankAccNo;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public String getBankCode1()
	{
		return bankCode1;
	}

	public void setBankCode1(String bankCode1)
	{
		this.bankCode1 = bankCode1;
	}

	public String getBankCode2()
	{
		return bankCode2;
	}

	public void setBankCode2(String bankCode2)
	{
		this.bankCode2 = bankCode2;
	}

	public String getPrimaryInsuredTitile()
	{
		return primaryInsuredTitile;
	}

	public void setPrimaryInsuredTitile(String primaryInsuredTitile)
	{
		this.primaryInsuredTitile = primaryInsuredTitile;
	}

	public String getPrimaryInsuredFirstName()
	{
		return primaryInsuredFirstName;
	}

	public void setPrimaryInsuredFirstName(String primaryInsuredFirstName)
	{
		this.primaryInsuredFirstName = primaryInsuredFirstName;
	}

	public String getPrimaryNameOnCard()
	{
		return primaryNameOnCard;
	}

	public void setPrimaryNameOnCard(String primaryNameOnCard)
	{
		this.primaryNameOnCard = primaryNameOnCard;
	}

	public String getPrimaryInsuredLastName()
	{
		return primaryInsuredLastName;
	}

	public void setPrimaryInsuredLastName(String primaryInsuredLastName)
	{
		this.primaryInsuredLastName = primaryInsuredLastName;
	}

	public Date getPrimaryInsuredDob()
	{
		return primaryInsuredDob;
	}

	public void setPrimaryInsuredDob(Date primaryInsuredDob)
	{
		this.primaryInsuredDob = primaryInsuredDob;
	}

	public String getPrimaryInsuredGender()
	{
		return primaryInsuredGender;
	}

	public void setPrimaryInsuredGender(String primaryInsuredGender)
	{
		this.primaryInsuredGender = primaryInsuredGender;
	}

	public String getPrimaryInsuredOccupationCode()
	{
		return primaryInsuredOccupationCode;
	}

	public void setPrimaryInsuredOccupationCode(String primaryInsuredOccupationCode)
	{
		this.primaryInsuredOccupationCode = primaryInsuredOccupationCode;
	}

	public String getPrimaryIdCard()
	{
		return primaryIdCard;
	}

	public void setPrimaryIdCard(String primaryIdCard)
	{
		this.primaryIdCard = primaryIdCard;
	}

	public String getPrimaryNameChanged()
	{
		return primaryNameChanged;
	}

	public void setPrimaryNameChanged(String primaryNameChanged)
	{
		this.primaryNameChanged = primaryNameChanged;
	}

	public String getPrimaryMaritalStatus()
	{
		return primaryMaritalStatus;
	}

	public void setPrimaryMaritalStatus(String primaryMaritalStatus)
	{
		this.primaryMaritalStatus = primaryMaritalStatus;
	}

	public String getPrimaryIncome()
	{
		return primaryIncome;
	}

	public void setPrimaryIncome(String primaryIncome)
	{
		this.primaryIncome = primaryIncome;
	}

	public String getPiBuildingNo()
	{
		return piBuildingNo;
	}

	public void setPiBuildingNo(String piBuildingNo)
	{
		this.piBuildingNo = piBuildingNo;
	}

	public String getPiBuildingName()
	{
		return piBuildingName;
	}

	public void setPiBuildingName(String piBuildingName)
	{
		this.piBuildingName = piBuildingName;
	}

	public String getPiRoomFloor()
	{
		return piRoomFloor;
	}

	public void setPiRoomFloor(String piRoomFloor)
	{
		this.piRoomFloor = piRoomFloor;
	}

	public String getMoo()
	{
		return moo;
	}

	public void setMoo(String moo)
	{
		this.moo = moo;
	}

	public String getMooBaan()
	{
		return mooBaan;
	}

	public void setMooBaan(String mooBaan)
	{
		this.mooBaan = mooBaan;
	}

	public String getSoi()
	{
		return soi;
	}

	public void setSoi(String soi)
	{
		this.soi = soi;
	}

	public String getStreetName()
	{
		return streetName;
	}

	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	public String getSubDistrict()
	{
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict)
	{
		this.subDistrict = subDistrict;
	}

	public String getDistrict()
	{
		return district;
	}

	public void setDistrict(String district)
	{
		this.district = district;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getPostCode()
	{
		return postCode;
	}

	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getPhoneIndicator()
	{
		return phoneIndicator;
	}

	public void setPhoneIndicator(String phoneIndicator)
	{
		this.phoneIndicator = phoneIndicator;
	}

	public String getPhoneCountryCode()
	{
		return phoneCountryCode;
	}

	public void setPhoneCountryCode(String phoneCountryCode)
	{
		this.phoneCountryCode = phoneCountryCode;
	}

	public String getPhoneAreaCode()
	{
		return phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode)
	{
		this.phoneAreaCode = phoneAreaCode;
	}

	public String getPhoneExtNo()
	{
		return phoneExtNo;
	}

	public void setPhoneExtNo(String phoneExtNo)
	{
		this.phoneExtNo = phoneExtNo;
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	public String getPhoneIndicatorAlt()
	{
		return phoneIndicatorAlt;
	}

	public void setPhoneIndicatorAlt(String phoneIndicatorAlt)
	{
		this.phoneIndicatorAlt = phoneIndicatorAlt;
	}

	public String getPhoneCountryCodeAlt()
	{
		return phoneCountryCodeAlt;
	}

	public void setPhoneCountryCodeAlt(String phoneCountryCodeAlt)
	{
		this.phoneCountryCodeAlt = phoneCountryCodeAlt;
	}

	public String getPhoneAreaCodeAlt()
	{
		return phoneAreaCodeAlt;
	}

	public void setPhoneAreaCodeAlt(String phoneAreaCodeAlt)
	{
		this.phoneAreaCodeAlt = phoneAreaCodeAlt;
	}

	public String getPhoneExtNoAlt()
	{
		return phoneExtNoAlt;
	}

	public void setPhoneExtNoAlt(String phoneExtNoAlt)
	{
		this.phoneExtNoAlt = phoneExtNoAlt;
	}

	public String getPhoneNoAlt()
	{
		return phoneNoAlt;
	}

	public void setPhoneNoAlt(String phoneNoAlt)
	{
		this.phoneNoAlt = phoneNoAlt;
	}

	public String getPreferredPhoneNoToContact()
	{
		return preferredPhoneNoToContact;
	}

	public void setPreferredPhoneNoToContact(String preferredPhoneNoToContact)
	{
		this.preferredPhoneNoToContact = preferredPhoneNoToContact;
	}

	public String getSpouseTitle()
	{
		return spouseTitle;
	}

	public void setSpouseTitle(String spouseTitle)
	{
		this.spouseTitle = spouseTitle;
	}

	public String getSpouseFirstName()
	{
		return spouseFirstName;
	}

	public void setSpouseFirstName(String spouseFirstName)
	{
		this.spouseFirstName = spouseFirstName;
	}

	public String getSpouseMidName()
	{
		return spouseMidName;
	}

	public void setSpouseMidName(String spouseMidName)
	{
		this.spouseMidName = spouseMidName;
	}

	public String getSpouseLastName()
	{
		return spouseLastName;
	}

	public void setSpouseLastName(String spouseLastName)
	{
		this.spouseLastName = spouseLastName;
	}

	public Date getSpouseDob()
	{
		return spouseDob;
	}

	public void setSpouseDob(Date spouseDob)
	{
		this.spouseDob = spouseDob;
	}

	public String getSpouseGender()
	{
		return spouseGender;
	}

	public void setSpouseGender(String spouseGender)
	{
		this.spouseGender = spouseGender;
	}

	public String getSpouseInsuredOccCode()
	{
		return spouseInsuredOccCode;
	}

	public void setSpouseInsuredOccCode(String spouseInsuredOccCode)
	{
		this.spouseInsuredOccCode = spouseInsuredOccCode;
	}

	public String getSpouseIdCard()
	{
		return spouseIdCard;
	}

	public void setSpouseIdCard(String spouseIdCard)
	{
		this.spouseIdCard = spouseIdCard;
	}

	public String getDependentTitle1()
	{
		return dependentTitle1;
	}

	public void setDependentTitle1(String dependentTitle1)
	{
		this.dependentTitle1 = dependentTitle1;
	}

	public String getDependentFirstName1()
	{
		return dependentFirstName1;
	}

	public void setDependentFirstName1(String dependentFirstName1)
	{
		this.dependentFirstName1 = dependentFirstName1;
	}

	public String getDependentOccCode1()
	{
		return dependentOccCode1;
	}

	public void setDependentOccCode1(String dependentOccCode1)
	{
		this.dependentOccCode1 = dependentOccCode1;
	}

	public String getDependentRelation1()
	{
		return dependentRelation1;
	}

	public void setDependentRelation1(String dependentRelation1)
	{
		this.dependentRelation1 = dependentRelation1;
	}

	public String getDependentLastName1()
	{
		return dependentLastName1;
	}

	public void setDependentLastName1(String dependentLastName1)
	{
		this.dependentLastName1 = dependentLastName1;
	}

	public Date getDependentDob1()
	{
		return dependentDob1;
	}

	public void setDependentDob1(Date dependentDob1)
	{
		this.dependentDob1 = dependentDob1;
	}

	public String getDependentGender1()
	{
		return dependentGender1;
	}

	public void setDependentGender1(String dependentGender1)
	{
		this.dependentGender1 = dependentGender1;
	}

	public String getDependentIdCard1()
	{
		return dependentIdCard1;
	}

	public void setDependentIdCard1(String dependentIdCard1)
	{
		this.dependentIdCard1 = dependentIdCard1;
	}

	public String getDependentTitle2()
	{
		return dependentTitle2;
	}

	public void setDependentTitle2(String dependentTitle2)
	{
		this.dependentTitle2 = dependentTitle2;
	}

	public String getDependentFirstName2()
	{
		return dependentFirstName2;
	}

	public void setDependentFirstName2(String dependentFirstName2)
	{
		this.dependentFirstName2 = dependentFirstName2;
	}

	public String getDependentOccCode2()
	{
		return dependentOccCode2;
	}

	public void setDependentOccCode2(String dependentOccCode2)
	{
		this.dependentOccCode2 = dependentOccCode2;
	}

	public String getDependentRelation2()
	{
		return dependentRelation2;
	}

	public void setDependentRelation2(String dependentRelation2)
	{
		this.dependentRelation2 = dependentRelation2;
	}

	public String getDependentLastName2()
	{
		return dependentLastName2;
	}

	public void setDependentLastName2(String dependentLastName2)
	{
		this.dependentLastName2 = dependentLastName2;
	}

	public Date getDependentDob2()
	{
		return dependentDob2;
	}

	public void setDependentDob2(Date dependentDob2)
	{
		this.dependentDob2 = dependentDob2;
	}

	public String getDependentGender2()
	{
		return dependentGender2;
	}

	public void setDependentGender2(String dependentGender2)
	{
		this.dependentGender2 = dependentGender2;
	}

	public String getDependentIdCard2()
	{
		return dependentIdCard2;
	}

	public void setDependentIdCard2(String dependentIdCard2)
	{
		this.dependentIdCard2 = dependentIdCard2;
	}

	public String getDependentTitle3()
	{
		return dependentTitle3;
	}

	public void setDependentTitle3(String dependentTitle3)
	{
		this.dependentTitle3 = dependentTitle3;
	}

	public String getDependentFirstName3()
	{
		return dependentFirstName3;
	}

	public void setDependentFirstName3(String dependentFirstName3)
	{
		this.dependentFirstName3 = dependentFirstName3;
	}

	public String getDependentOccCode3()
	{
		return dependentOccCode3;
	}

	public void setDependentOccCode3(String dependentOccCode3)
	{
		this.dependentOccCode3 = dependentOccCode3;
	}

	public String getDependentRelation3()
	{
		return dependentRelation3;
	}

	public void setDependentRelation3(String dependentRelation3)
	{
		this.dependentRelation3 = dependentRelation3;
	}

	public String getDependentLastName3()
	{
		return dependentLastName3;
	}

	public void setDependentLastName3(String dependentLastName3)
	{
		this.dependentLastName3 = dependentLastName3;
	}

	public Date getDependentDob3()
	{
		return dependentDob3;
	}

	public void setDependentDob3(Date dependentDob3)
	{
		this.dependentDob3 = dependentDob3;
	}

	public String getDependentGender3()
	{
		return dependentGender3;
	}

	public void setDependentGender3(String dependentGender3)
	{
		this.dependentGender3 = dependentGender3;
	}

	public String getDependentIdCard3()
	{
		return dependentIdCard3;
	}

	public void setDependentIdCard3(String dependentIdCard3)
	{
		this.dependentIdCard3 = dependentIdCard3;
	}

	public String getDependentTitle4()
	{
		return dependentTitle4;
	}

	public void setDependentTitle4(String dependentTitle4)
	{
		this.dependentTitle4 = dependentTitle4;
	}

	public String getDependentFirstName4()
	{
		return dependentFirstName4;
	}

	public void setDependentFirstName4(String dependentFirstName4)
	{
		this.dependentFirstName4 = dependentFirstName4;
	}

	public String getDependentOccCode4()
	{
		return dependentOccCode4;
	}

	public void setDependentOccCode4(String dependentOccCode4)
	{
		this.dependentOccCode4 = dependentOccCode4;
	}

	public String getDependentRelation4()
	{
		return dependentRelation4;
	}

	public void setDependentRelation4(String dependentRelation4)
	{
		this.dependentRelation4 = dependentRelation4;
	}

	public String getDependentLastName4()
	{
		return dependentLastName4;
	}

	public void setDependentLastName4(String dependentLastName4)
	{
		this.dependentLastName4 = dependentLastName4;
	}

	public Date getDependentDob4()
	{
		return dependentDob4;
	}

	public void setDependentDob4(Date dependentDob4)
	{
		this.dependentDob4 = dependentDob4;
	}

	public String getDependentGender4()
	{
		return dependentGender4;
	}

	public void setDependentGender4(String dependentGender4)
	{
		this.dependentGender4 = dependentGender4;
	}

	public String getDependentIdCard4()
	{
		return dependentIdCard4;
	}

	public void setDependentIdCard4(String dependentIdCard4)
	{
		this.dependentIdCard4 = dependentIdCard4;
	}

	public String getDependentTitle5()
	{
		return dependentTitle5;
	}

	public void setDependentTitle5(String dependentTitle5)
	{
		this.dependentTitle5 = dependentTitle5;
	}

	public String getDependentFirstName5()
	{
		return dependentFirstName5;
	}

	public void setDependentFirstName5(String dependentFirstName5)
	{
		this.dependentFirstName5 = dependentFirstName5;
	}

	public String getDependentOccCode5()
	{
		return dependentOccCode5;
	}

	public void setDependentOccCode5(String dependentOccCode5)
	{
		this.dependentOccCode5 = dependentOccCode5;
	}

	public String getDependentRelation5()
	{
		return dependentRelation5;
	}

	public void setDependentRelation5(String dependentRelation5)
	{
		this.dependentRelation5 = dependentRelation5;
	}

	public String getDependentLastName5()
	{
		return dependentLastName5;
	}

	public void setDependentLastName5(String dependentLastName5)
	{
		this.dependentLastName5 = dependentLastName5;
	}

	public Date getDependentDob5()
	{
		return dependentDob5;
	}

	public void setDependentDob5(Date dependentDob5)
	{
		this.dependentDob5 = dependentDob5;
	}

	public String getDependentGender5()
	{
		return dependentGender5;
	}

	public void setDependentGender5(String dependentGender5)
	{
		this.dependentGender5 = dependentGender5;
	}

	public String getDependentIdCard5()
	{
		return dependentIdCard5;
	}

	public void setDependentIdCard5(String dependentIdCard5)
	{
		this.dependentIdCard5 = dependentIdCard5;
	}

	public String getNumOfDependents()
	{
		return numOfDependents;
	}

	public void setNumOfDependents(String numOfDependents)
	{
		this.numOfDependents = numOfDependents;
	}

	public String getPartnerCustomerUniqId()
	{
		return partnerCustomerUniqId;
	}

	public void setPartnerCustomerUniqId(String partnerCustomerUniqId)
	{
		this.partnerCustomerUniqId = partnerCustomerUniqId;
	}

	public String getLanguagePref()
	{
		return languagePref;
	}

	public void setLanguagePref(String languagePref)
	{
		this.languagePref = languagePref;
	}

	public String getMarketCode()
	{
		return marketCode;
	}

	public void setMarketCode(String marketCode)
	{
		this.marketCode = marketCode;
	}

	public String getCountryIdentifier()
	{
		return countryIdentifier;
	}

	public void setCountryIdentifier(String countryIdentifier)
	{
		this.countryIdentifier = countryIdentifier;
	}

	public String getProductLineCode()
	{
		return productLineCode;
	}

	public void setProductLineCode(String productLineCode)
	{
		this.productLineCode = productLineCode;
	}

	public String getSoldPlanCode()
	{
		return soldPlanCode;
	}

	public void setSoldPlanCode(String soldPlanCode)
	{
		this.soldPlanCode = soldPlanCode;
	}

	public String getSoldSequenceNum()
	{
		return soldSequenceNum;
	}

	public void setSoldSequenceNum(String soldSequenceNum)
	{
		this.soldSequenceNum = soldSequenceNum;
	}

	public String getSoldOptionCode()
	{
		return soldOptionCode;
	}

	public void setSoldOptionCode(String soldOptionCode)
	{
		this.soldOptionCode = soldOptionCode;
	}

	public String getSoldOptionCategory()
	{
		return soldOptionCategory;
	}

	public void setSoldOptionCategory(String soldOptionCategory)
	{
		this.soldOptionCategory = soldOptionCategory;
	}

	public String getSoldPremium()
	{
		return soldPremium;
	}

	public void setSoldPremium(String soldPremium)
	{
		this.soldPremium = soldPremium;
	}

	public String getSoldBenefitAmt()
	{
		return soldBenefitAmt;
	}

	public void setSoldBenefitAmt(String soldBenefitAmt)
	{
		this.soldBenefitAmt = soldBenefitAmt;
	}

	public String getSoldBillingMode()
	{
		return soldBillingMode;
	}

	public void setSoldBillingMode(String soldBillingMode)
	{
		this.soldBillingMode = soldBillingMode;
	}

	public String getSoldBillingFreq()
	{
		return soldBillingFreq;
	}

	public void setSoldBillingFreq(String soldBillingFreq)
	{
		this.soldBillingFreq = soldBillingFreq;
	}

	public String getContactFirstName()
	{
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName)
	{
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName()
	{
		return contactLastName;
	}

	public void setContactLastName(String contactLastName)
	{
		this.contactLastName = contactLastName;
	}

	public String getContactRelation()
	{
		return contactRelation;
	}

	public void setContactRelation(String contactRelation)
	{
		this.contactRelation = contactRelation;
	}

	public String getTmrCode()
	{
		return tmrCode;
	}

	public void setTmrCode(String tmrCode)
	{
		this.tmrCode = tmrCode;
	}

	public Date getCallDate()
	{
		return callDate;
	}

	public void setCallDate(Date callDate)
	{
		this.callDate = callDate;
	}

	public Date getCallStartTime()
	{
		return callStartTime;
	}

	public void setCallStartTime(Date callStartTime)
	{
		this.callStartTime = callStartTime;
	}

	public String getCalloutCome()
	{
		return calloutCome;
	}

	public void setCalloutCome(String calloutCome)
	{
		this.calloutCome = calloutCome;
	}

	public String getBeneficiaryName1()
	{
		return beneficiaryName1;
	}

	public void setBeneficiaryName1(String beneficiaryName1)
	{
		this.beneficiaryName1 = beneficiaryName1;
	}

	public String getBeneficiaryRelation1()
	{
		return beneficiaryRelation1;
	}

	public void setBeneficiaryRelation1(String beneficiaryRelation1)
	{
		this.beneficiaryRelation1 = beneficiaryRelation1;
	}

	public String getBeneficiaryPercent1()
	{
		return beneficiaryPercent1;
	}

	public void setBeneficiaryPercent1(String beneficiaryPercent1)
	{
		this.beneficiaryPercent1 = beneficiaryPercent1;
	}

	public String getBeneficiaryName2()
	{
		return beneficiaryName2;
	}

	public void setBeneficiaryName2(String beneficiaryName2)
	{
		this.beneficiaryName2 = beneficiaryName2;
	}

	public String getBeneficiaryRelation2()
	{
		return beneficiaryRelation2;
	}

	public void setBeneficiaryRelation2(String beneficiaryRelation2)
	{
		this.beneficiaryRelation2 = beneficiaryRelation2;
	}

	public String getBeneficiaryPercent2()
	{
		return beneficiaryPercent2;
	}

	public void setBeneficiaryPercent2(String beneficiaryPercent2)
	{
		this.beneficiaryPercent2 = beneficiaryPercent2;
	}

	public String getBeneficiaryName3()
	{
		return beneficiaryName3;
	}

	public void setBeneficiaryName3(String beneficiaryName3)
	{
		this.beneficiaryName3 = beneficiaryName3;
	}

	public String getBeneficiaryRelation3()
	{
		return beneficiaryRelation3;
	}

	public void setBeneficiaryRelation3(String beneficiaryRelation3)
	{
		this.beneficiaryRelation3 = beneficiaryRelation3;
	}

	public String getBeneficiaryPercent3()
	{
		return beneficiaryPercent3;
	}

	public void setBeneficiaryPercent3(String beneficiaryPercent3)
	{
		this.beneficiaryPercent3 = beneficiaryPercent3;
	}

	public String getBeneficiaryName4()
	{
		return beneficiaryName4;
	}

	public void setBeneficiaryName4(String beneficiaryName4)
	{
		this.beneficiaryName4 = beneficiaryName4;
	}

	public String getBeneficiaryRelation4()
	{
		return beneficiaryRelation4;
	}

	public void setBeneficiaryRelation4(String beneficiaryRelation4)
	{
		this.beneficiaryRelation4 = beneficiaryRelation4;
	}

	public String getBeneficiaryPercent4()
	{
		return beneficiaryPercent4;
	}

	public void setBeneficiaryPercent4(String beneficiaryPercent4)
	{
		this.beneficiaryPercent4 = beneficiaryPercent4;
	}

	public String getBeneficiaryName5()
	{
		return beneficiaryName5;
	}

	public void setBeneficiaryName5(String beneficiaryName5)
	{
		this.beneficiaryName5 = beneficiaryName5;
	}

	public String getBeneficiaryRelation5()
	{
		return beneficiaryRelation5;
	}

	public void setBeneficiaryRelation5(String beneficiaryRelation5)
	{
		this.beneficiaryRelation5 = beneficiaryRelation5;
	}

	public String getBeneficiaryPercent5()
	{
		return beneficiaryPercent5;
	}

	public void setBeneficiaryPercent5(String beneficiaryPercent5)
	{
		this.beneficiaryPercent5 = beneficiaryPercent5;
	}

	public String getPolicyNo()
	{
		return policyNo;
	}

	public void setPolicyNo(String policyNo)
	{
		this.policyNo = policyNo;
	}

	public String getMarketingOffer()
	{
		return marketingOffer;
	}

	public void setMarketingOffer(String marketingOffer)
	{
		this.marketingOffer = marketingOffer;
	}

	public String getTelephoneSourceCode()
	{
		return telephoneSourceCode;
	}

	public void setTelephoneSourceCode(String telephoneSourceCode)
	{
		this.telephoneSourceCode = telephoneSourceCode;
	}

	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public Date getEffectiveTime()
	{
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime)
	{
		this.effectiveTime = effectiveTime;
	}

	public String getRejectedFlag()
	{
		return rejectedFlag;
	}

	public void setRejectedFlag(String rejectedFlag)
	{
		this.rejectedFlag = rejectedFlag;
	}

	public String getBankRefCode()
	{
		return bankRefCode;
	}

	public void setBankRefCode(String bankRefCode)
	{
		this.bankRefCode = bankRefCode;
	}

	public String getBankRefDate()
	{
		return bankRefDate;
	}

	public void setBankRefDate(String bankDefDate)
	{
		this.bankRefDate = bankDefDate;
	}

	public String getPiWeight()
	{
		return piWeight;
	}

	public void setPiWeight(String piWeight)
	{
		this.piWeight = piWeight;
	}

	public String getPiHeight()
	{
		return piHeight;
	}

	public void setPiHeight(String piHeight)
	{
		this.piHeight = piHeight;
	}

	public String getUniqueId()
	{
		return uniqueId;
	}

	public void setUniqueId(String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	public String getKbankCampaignCode()
	{
		return kbankCampaignCode;
	}

	public void setKbankCampaignCode(String kbankCampaignCode)
	{
		this.kbankCampaignCode = kbankCampaignCode;
	}

	public String getPolicyNumber()
	{
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber)
	{
		this.policyNumber = policyNumber;
	}

	public String getIssueDate()
	{
		return issueDate;
	}

	public void setIssueDate(String issueDate)
	{
		this.issueDate = issueDate;
	}

	public String getPolicyStatus()
	{
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus)
	{
		this.policyStatus = policyStatus;
	}

	public String getSubOutcome()
	{
		return subOutcome;
	}

	public void setSubOutcome(String subOutcome)
	{
		this.subOutcome = subOutcome;
	}

	public String getInsuredOccDesc()
	{
		return insuredOccDesc;
	}

	public void setInsuredOccDesc(String insuredOccDesc)
	{
		this.insuredOccDesc = insuredOccDesc;
	}

	public String getSpouseOccDesc()
	{
		return spouseOccDesc;
	}

	public void setSpouseOccDesc(String spouseOccDesc)
	{
		this.spouseOccDesc = spouseOccDesc;
	}

	public Integer getNumberOfPolicy()
	{
		return numberOfPolicy;
	}

	public void setNumberOfPolicy(Integer numberOfPolicy)
	{
		this.numberOfPolicy = numberOfPolicy;
	}

	public String getSummarySumInsured()
	{
		return summarySumInsured;
	}

	public void setSummarySumInsured(String summarySumInsured)
	{
		this.summarySumInsured = summarySumInsured;
	}

	public Integer getSpouseNumberOfPolicy()
	{
		return spouseNumberOfPolicy;
	}

	public void setSpouseNumberOfPolicy(Integer spouseNumberOfPolicy)
	{
		this.spouseNumberOfPolicy = spouseNumberOfPolicy;
	}

	public String getSpouseSummarySumInsured()
	{
		return spouseSummarySumInsured;
	}

	public void setSpouseSummarySumInsured(String spouseSummarySumInsured)
	{
		this.spouseSummarySumInsured = spouseSummarySumInsured;
	}

	public String getKbankOutcome()
	{
		return kbankOutcome;
	}

	public void setKbankOutcome(String kbankOutcome)
	{
		this.kbankOutcome = kbankOutcome;
	}

	public String getKbankSubOutcome()
	{
		return kbankSubOutcome;
	}

	public void setKbankSubOutcome(String kbankSubOutcome)
	{
		this.kbankSubOutcome = kbankSubOutcome;
	}

	public String getKbankContact()
	{
		return kbankContact;
	}

	public void setKbankContact(String kbankContact)
	{
		this.kbankContact = kbankContact;
	}

	public String getQcCode()
	{
		return qcCode;
	}

	public void setQcCode(String qcCode)
	{
		this.qcCode = qcCode;
	}

	public String getSubCode()
	{
		return subCode;
	}

	public void setSubCode(String subCode)
	{
		this.subCode = subCode;
	}

	@Override
	public String toString()
	{
		return "YesRecordMTIModel [recordType=" + recordType + ", agencyId=" + agencyId + ", locationCode=" + locationCode + ", campaignNumber=" + campaignNumber + ", keyCode=" + keyCode + ", fileOwnerCode=" + fileOwnerCode + ", accountType=" + accountType + ", creditCardAccNo=" + creditCardAccNo
				+ ", creditCardExpiryDate=" + creditCardExpiryDate + ", bankAccNo=" + bankAccNo + ", bankName=" + bankName + ", bankCode1=" + bankCode1 + ", bankCode2=" + bankCode2 + ", primaryInsuredTitile=" + primaryInsuredTitile + ", primaryInsuredFirstName=" + primaryInsuredFirstName
				+ ", primaryNameOnCard=" + primaryNameOnCard + ", primaryInsuredLastName=" + primaryInsuredLastName + ", primaryInsuredDob=" + primaryInsuredDob + ", primaryInsuredGender=" + primaryInsuredGender + ", primaryInsuredOccupationCode=" + primaryInsuredOccupationCode + ", primaryIdCard="
				+ primaryIdCard + ", primaryNameChanged=" + primaryNameChanged + ", primaryMaritalStatus=" + primaryMaritalStatus + ", primaryIncome=" + primaryIncome + ", piBuildingNo=" + piBuildingNo + ", piBuildingName=" + piBuildingName + ", piRoomFloor=" + piRoomFloor + ", moo=" + moo
				+ ", mooBaan=" + mooBaan + ", soi=" + soi + ", streetName=" + streetName + ", subDistrict=" + subDistrict + ", district=" + district + ", province=" + province + ", postCode=" + postCode + ", areaCode=" + areaCode + ", phoneIndicator=" + phoneIndicator + ", phoneCountryCode="
				+ phoneCountryCode + ", phoneAreaCode=" + phoneAreaCode + ", phoneExtNo=" + phoneExtNo + ", phoneNo=" + phoneNo + ", phoneIndicatorAlt=" + phoneIndicatorAlt + ", phoneCountryCodeAlt=" + phoneCountryCodeAlt + ", phoneAreaCodeAlt=" + phoneAreaCodeAlt + ", phoneExtNoAlt="
				+ phoneExtNoAlt + ", phoneNoAlt=" + phoneNoAlt + ", preferredPhoneNoToContact=" + preferredPhoneNoToContact + ", spouseTitle=" + spouseTitle + ", spouseFirstName=" + spouseFirstName + ", spouseMidName=" + spouseMidName + ", spouseLastName=" + spouseLastName + ", spouseDob="
				+ spouseDob + ", spouseGender=" + spouseGender + ", spouseInsuredOccCode=" + spouseInsuredOccCode + ", spouseIdCard=" + spouseIdCard + ", dependentTitle1=" + dependentTitle1 + ", dependentFirstName1=" + dependentFirstName1 + ", dependentOccCode1=" + dependentOccCode1
				+ ", dependentRelation1=" + dependentRelation1 + ", dependentLastName1=" + dependentLastName1 + ", dependentDob1=" + dependentDob1 + ", dependentGender1=" + dependentGender1 + ", dependentIdCard1=" + dependentIdCard1 + ", dependentTitle2=" + dependentTitle2 + ", dependentFirstName2="
				+ dependentFirstName2 + ", dependentOccCode2=" + dependentOccCode2 + ", dependentRelation2=" + dependentRelation2 + ", dependentLastName2=" + dependentLastName2 + ", dependentDob2=" + dependentDob2 + ", dependentGender2=" + dependentGender2 + ", dependentIdCard2=" + dependentIdCard2
				+ ", dependentTitle3=" + dependentTitle3 + ", dependentFirstName3=" + dependentFirstName3 + ", dependentOccCode3=" + dependentOccCode3 + ", dependentRelation3=" + dependentRelation3 + ", dependentLastName3=" + dependentLastName3 + ", dependentDob3=" + dependentDob3
				+ ", dependentGender3=" + dependentGender3 + ", dependentIdCard3=" + dependentIdCard3 + ", dependentTitle4=" + dependentTitle4 + ", dependentFirstName4=" + dependentFirstName4 + ", dependentOccCode4=" + dependentOccCode4 + ", dependentRelation4=" + dependentRelation4
				+ ", dependentLastName4=" + dependentLastName4 + ", dependentDob4=" + dependentDob4 + ", dependentGender4=" + dependentGender4 + ", dependentIdCard4=" + dependentIdCard4 + ", dependentTitle5=" + dependentTitle5 + ", dependentFirstName5=" + dependentFirstName5 + ", dependentOccCode5="
				+ dependentOccCode5 + ", dependentRelation5=" + dependentRelation5 + ", dependentLastName5=" + dependentLastName5 + ", dependentDob5=" + dependentDob5 + ", dependentGender5=" + dependentGender5 + ", dependentIdCard5=" + dependentIdCard5 + ", numOfDependents=" + numOfDependents
				+ ", partnerCustomerUniqId=" + partnerCustomerUniqId + ", languagePref=" + languagePref + ", marketCode=" + marketCode + ", countryIdentifier=" + countryIdentifier + ", productLineCode=" + productLineCode + ", soldPlanCode=" + soldPlanCode + ", soldSequenceNum=" + soldSequenceNum
				+ ", soldOptionCode=" + soldOptionCode + ", soldOptionCategory=" + soldOptionCategory + ", soldPremium=" + soldPremium + ", soldBenefitAmt=" + soldBenefitAmt + ", soldBillingMode=" + soldBillingMode + ", soldBillingFreq=" + soldBillingFreq + ", contactFirstName=" + contactFirstName
				+ ", contactLastName=" + contactLastName + ", contactRelation=" + contactRelation + ", tmrCode=" + tmrCode + ", callDate=" + callDate + ", callStartTime=" + callStartTime + ", calloutCome=" + calloutCome + ", beneficiaryName1=" + beneficiaryName1 + ", beneficiaryRelation1="
				+ beneficiaryRelation1 + ", beneficiaryPercent1=" + beneficiaryPercent1 + ", beneficiaryName2=" + beneficiaryName2 + ", beneficiaryRelation2=" + beneficiaryRelation2 + ", beneficiaryPercent2=" + beneficiaryPercent2 + ", beneficiaryName3=" + beneficiaryName3
				+ ", beneficiaryRelation3=" + beneficiaryRelation3 + ", beneficiaryPercent3=" + beneficiaryPercent3 + ", beneficiaryName4=" + beneficiaryName4 + ", beneficiaryRelation4=" + beneficiaryRelation4 + ", beneficiaryPercent4=" + beneficiaryPercent4 + ", beneficiaryName5="
				+ beneficiaryName5 + ", beneficiaryRelation5=" + beneficiaryRelation5 + ", beneficiaryPercent5=" + beneficiaryPercent5 + ", policyNo=" + policyNo + ", marketingOffer=" + marketingOffer + ", telephoneSourceCode=" + telephoneSourceCode + ", effectiveDate=" + effectiveDate
				+ ", effectiveTime=" + effectiveTime + ", rejectedFlag=" + rejectedFlag + ", bankRefCode=" + bankRefCode + ", bankRefDate=" + bankRefDate + ", piWeight=" + piWeight + ", piHeight=" + piHeight + ", uniqueId=" + uniqueId + ", kbankCampaignCode=" + kbankCampaignCode + ", policyNumber="
				+ policyNumber + ", issueDate=" + issueDate + ", policyStatus=" + policyStatus + ", subOutcome=" + subOutcome + ", insuredOccDesc=" + insuredOccDesc + ", spouseOccDesc=" + spouseOccDesc + ", numberOfPolicy=" + numberOfPolicy + ", summarySumInsured=" + summarySumInsured
				+ ", spouseNumberOfPolicy=" + spouseNumberOfPolicy + ", spouseSummarySumInsured=" + spouseSummarySumInsured + ", kbankOutcome=" + kbankOutcome + ", kbankSubOutcome=" + kbankSubOutcome + ", kbankContact=" + kbankContact + ", qcCode=" + qcCode + ", subCode=" + subCode + "]";
	}
	
}
