package com.adms.batch.sales.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "DDOP_MAPPING_2ND_RESULT")
@NamedNativeQueries({ @NamedNativeQuery(name = "findDdopMapping2ndResultByxReference", query = "select * from DDOP_MAPPING_2ND_RESULT where X_REFERENCE = ?", resultClass = DdopMapping2ndResult.class),
})
public class DdopMapping2ndResult extends BaseAuditDomain {

	private static final long serialVersionUID = -2366384577250740833L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "IMPORT_ID")
	private FileImport fileImport;

	@ManyToOne
	@JoinColumn(name = "X_REFERENCE", referencedColumnName = "X_REFERENCE")
	private Sales xReference;

	@Column(name = "REJECT_DATE")
	@Temporal(TemporalType.DATE)
	private Date rejectDate;

	@Column(name = "LAST_4_DIGIT_DEBIT_CARD")
	private String last4DigitDebitCard;

	@Column(name = "LAST_4_DIGIT_DEBIT_CARD_NEW")
	private String last4DigitDebitCardNew;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "COMM_STATUS")
	private String commStatus;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public FileImport getFileImport()
	{
		return fileImport;
	}

	public void setFileImport(FileImport fileImport)
	{
		this.fileImport = fileImport;
	}

	public Sales getxReference()
	{
		return xReference;
	}

	public void setxReference(Sales xReference)
	{
		this.xReference = xReference;
	}

	public Date getRejectDate()
	{
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate)
	{
		this.rejectDate = rejectDate;
	}

	public String getLast4DigitDebitCard()
	{
		return last4DigitDebitCard;
	}

	public void setLast4DigitDebitCard(String last4DigitDebitCard)
	{
		this.last4DigitDebitCard = last4DigitDebitCard;
	}

	public String getLast4DigitDebitCardNew()
	{
		return last4DigitDebitCardNew;
	}

	public void setLast4DigitDebitCardNew(String last4DigitDebitCardNew)
	{
		this.last4DigitDebitCardNew = last4DigitDebitCardNew;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getCommStatus()
	{
		return commStatus;
	}

	public void setCommStatus(String commStatus)
	{
		this.commStatus = commStatus;
	}

	@Override
	public String toString()
	{
		return "DdopMapping2ndResult [id=" + id + ", fileImport=" + fileImport + ", xReference=" + xReference + ", rejectDate=" + rejectDate + ", last4DigitDebitCard=" + last4DigitDebitCard + ", last4DigitDebitCardNew=" + last4DigitDebitCardNew + ", remark=" + remark + ", commStatus=" + commStatus + "]";
	}

}
