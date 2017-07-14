package com.lab.lsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="PAPER_AUTHOR")
public class PaperAuthorDomain {
	private String id;//id
	private String paperId;//论文ID
	private Integer authorOrder;//作者顺序
	private String contributionRate;//贡献率
	private String authorCode;//作者编号
	private String authorName;//作者姓名
	public PaperAuthorDomain() {
		super();
	}
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "ID", unique = true, nullable = true, precision = 10, scale = 0)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "PAPERID",unique = true, nullable = true, length = 100)
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	@Column(name = "AUTHORORDER",unique = true, nullable = true, length = 11)
	public Integer getAuthorOrder() {
		return authorOrder;
	}
	public void setAuthorOrder(Integer authorOrder) {
		this.authorOrder = authorOrder;
	}
	@Column(name = "CONTRIBUTIONRATE",unique = true, nullable = true, length = 11)
	public String getContributionRate() {
		return contributionRate;
	}
	public void setContributionRate(String contributionRate) {
		this.contributionRate = contributionRate;
	}
	@Column(name = "AUTHORCODE",unique = true, nullable = true, length = 100)
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	@Column(name = "AUTHORNAME",unique = true, nullable = true, length = 100)
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
