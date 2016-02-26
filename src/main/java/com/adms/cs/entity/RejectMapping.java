package com.adms.cs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name="REJECT_MAPPING")
public class RejectMapping extends BaseAuditDomain {

	private static final long serialVersionUID = -6502155742744034194L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="IMPORT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date importDate;
	
	@Column(name="REFERENCE_NO")
	private String referenceNo;
	
	@Column(name="CARD_NO")
	private String cardNo;
	
	@Column(name="ACCOUNT_TYPE")
	private String accountType;
	
	@Column(name="ACCOUNT_NO")
	private String accountNo;
	
	@Column(name="INSURER")
	private String insurer;
	
	@Column(name="SOURCE")
	private String source;
	
	@Column(name="REJECT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rejectDate;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getImportDate()
	{
		return importDate;
	}

	public void setImportDate(Date importDate)
	{
		this.importDate = importDate;
	}

	public String getReferenceNo()
	{
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo)
	{
		this.referenceNo = referenceNo;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getInsurer()
	{
		return insurer;
	}

	public void setInsurer(String insurer)
	{
		this.insurer = insurer;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public Date getRejectDate()
	{
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate)
	{
		this.rejectDate = rejectDate;
	}
	
}
