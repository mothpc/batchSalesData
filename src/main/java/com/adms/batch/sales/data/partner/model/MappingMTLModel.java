package com.adms.batch.sales.data.partner.model;

public class MappingMTLModel {

	private String dataType;
	
	private String cisNo;
	
	private String cpacCustNo;
	
	private String cardNo;
	
	private String cardExpired;
	
	private String accountNo;
	
	private String title;
	
	private String name;
	
	private String surname;
	
	private String idNumber;
	
	private String refNo;
	
	private String campaignCode;
	
	private String status;
	
	public MappingMTLModel(String[] line) {
		this.dataType = line[0];
		this.cisNo = line[1];
		this.cpacCustNo = line[2];
		this.cardNo = line[3];
		this.cardExpired = line[4];
		this.accountNo = line[5];
		this.title = line[6];
		this.name = line[7];
		this.surname = line[8];
		this.idNumber = line[9];
		this.refNo = line[10];
		this.campaignCode = line[11];
		this.status = line[12];
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getCisNo()
	{
		return cisNo;
	}

	public void setCisNo(String cisNo)
	{
		this.cisNo = cisNo;
	}

	public String getCpacCustNo()
	{
		return cpacCustNo;
	}

	public void setCpacCustNo(String cpacCustNo)
	{
		this.cpacCustNo = cpacCustNo;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getCardExpired()
	{
		return cardExpired;
	}

	public void setCardExpired(String cardExpired)
	{
		this.cardExpired = cardExpired;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getIdNumber()
	{
		return idNumber;
	}

	public void setIdNumber(String idNumber)
	{
		this.idNumber = idNumber;
	}

	public String getRefNo()
	{
		return refNo;
	}

	public void setRefNo(String refNo)
	{
		this.refNo = refNo;
	}

	public String getCampaignCode()
	{
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode)
	{
		this.campaignCode = campaignCode;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "MtlMappingModel [dataType=" + dataType + ", cisNo=" + cisNo + ", cpacCustNo=" + cpacCustNo + ", cardNo=" + cardNo + ", cardExpired=" + cardExpired + ", accountNo=" + accountNo + ", title=" + title + ", name=" + name + ", surname=" + surname + ", idNumber=" + idNumber + ", refNo="
				+ refNo + ", campaignCode=" + campaignCode + ", status=" + status + "]";
	}
	
}
