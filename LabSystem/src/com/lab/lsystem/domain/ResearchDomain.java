package com.lab.lsystem.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="RESEARCH")
public class ResearchDomain {
	private String id;//id
	private TeacherDomain principal;//负责人
	private String phone;//负责人联系方式
	private String projectNumber;//项目流水编号
	private String email;//负责人邮箱
	private Integer projectLevel;//项目级别
	private Integer state;//项目状态
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date projectDate;//立项日期
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date planDate;//计划完成日期
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date startDate;//开始日期
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date endDate;//结项日期
	private String allFund;//立项总经费
	private String giveFund;//拨款经费
	private String finaAccount;//财务账号
	private String projectSource;//项目来源
	public ResearchDomain() {
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
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "TEACHERID")
	public TeacherDomain getPrincipal() {
		return principal;
	}
	public void setPrincipal(TeacherDomain principal) {
		this.principal = principal;
	}
	@Column(name = "PHONE",unique = true, nullable = true, length = 100)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "PROJECTNUMBER",unique = true, nullable = true, length = 200)
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	@Column(name = "EMAIL",unique = true, nullable = true, length = 100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "PROJECTLEVEL",unique = true, nullable = true, length = 11)
	public Integer getProjectLevel() {
		return projectLevel;
	}
	public void setProjectLevel(Integer projectLevel) {
		this.projectLevel = projectLevel;
	}
	@Column(name = "STATE",unique = true, nullable = true, length = 11)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "PROJECTDATE",unique = true, nullable = true, length = 200)
	public Date getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}
	@Column(name = "PLANDATE",unique = true, nullable = true, length = 200)
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	@Column(name = "STARTDATE",unique = true, nullable = true, length = 200)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "ENDDATE",unique = true, nullable = true, length = 200)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name = "ALLFUND",unique = true, nullable = true, length = 200)
	public String getAllFund() {
		return allFund;
	}
	public void setAllFund(String allFund) {
		this.allFund = allFund;
	}
	@Column(name = "GIVEFUND",unique = true, nullable = true, length = 200)
	public String getGiveFund() {
		return giveFund;
	}
	public void setGiveFund(String giveFund) {
		this.giveFund = giveFund;
	}
	@Column(name = "FINACCOUNT",unique = true, nullable = true, length = 200)
	public String getFinaAccount() {
		return finaAccount;
	}
	public void setFinaAccount(String finaAccount) {
		this.finaAccount = finaAccount;
	}
	@Column(name = "PROJECTSOURCE",unique = true, nullable = true, length = 200)
	public String getProjectSource() {
		return projectSource;
	}
	public void setProjectSource(String projectSource) {
		this.projectSource = projectSource;
	}
	
	
}