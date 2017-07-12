package com.lab.lsystem.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PAPER")
public class PaperDomain {
	private String id;//id
	private Integer type;//论文类型
	private String title;//论文题目
	private String firstAuthor;//第一作者Id
	private Integer firstIdentity;//第一作者身份
	private String secondAuthor;//第二作者Id
	private Integer secondIdentity;//第二作者身份
	private String correspondAuthor;//通讯作者
	private String otherAuthors;//其他作者
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date publishDate;//出版时间
	private String journalTitle;//期刊名称
	private String projectSource;//项目来源
	private String journalLevel;//刊物级别
	private String discipline;//一级学科
	private String firstName;//第一作者名字
	private String secondName;//第二作者名字
	private String correspondIdentity;//通讯作者身份
	public PaperDomain() {
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
	@Column(name = "TYPE",unique = true, nullable = true, length = 11)
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "TITLE",unique = true, nullable = true, length = 200)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "FIRSTAUTHOR",unique = true, nullable = true, length = 200)
	public String getFirstAuthor() {
		return firstAuthor;
	}
	public void setFirstAuthor(String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}
	@Column(name = "SECONDAUTHOR",unique = true, nullable = true, length = 200)
	public String getSecondAuthor() {
		return secondAuthor;
	}
	public void setSecondAuthor(String secondAuthor) {
		this.secondAuthor = secondAuthor;
	}
	@Column(name = "OTHERAUTHORS",unique = true, nullable = true, length = 200)
	public String getOtherAuthors() {
		return otherAuthors;
	}
	public void setOtherAuthors(String otherAuthors) {
		this.otherAuthors = otherAuthors;
	}
	@Column(name = "PUBLISHDATE",unique = true, nullable = true, length = 200)
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	@Column(name = "JOURNALTITLE",unique = true, nullable = true, length = 200)
	public String getJournalTitle() {
		return journalTitle;
	}
	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	@Column(name = "PROJECTSOURCE",unique = true, nullable = true, length = 200)
	public String getProjectSource() {
		return projectSource;
	}
	public void setProjectSource(String projectSource) {
		this.projectSource = projectSource;
	}
	@Column(name = "JOURNALLEVEL",unique = true, nullable = true, length = 200)
	public String getJournalLevel() {
		return journalLevel;
	}
	public void setJournalLevel(String journalLevel) {
		this.journalLevel = journalLevel;
	}
	@Column(name = "DISCIPLINE",unique = true, nullable = true, length = 200)
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	@Column(name = "FIRSTIDENTITY",unique = true, nullable = true, length = 11)
	public Integer getFirstIdentity() {
		return firstIdentity;
	}
	public void setFirstIdentity(Integer firstIdentity) {
		this.firstIdentity = firstIdentity;
	}
	@Column(name = "SECONDIDENTITY",unique = true, nullable = true, length = 11)
	public Integer getSecondIdentity() {
		return secondIdentity;
	}
	public void setSecondIdentity(Integer secondIdentity) {
		this.secondIdentity = secondIdentity;
	}
	@Column(name = "CORRESPONDAUTHOR",unique = true, nullable = true, length = 100)
	public String getCorrespondAuthor() {
		return correspondAuthor;
	}
	public void setCorrespondAuthor(String correspondAuthor) {
		this.correspondAuthor = correspondAuthor;
	}
	@Column(name = "FIRTSTNAME",unique = true, nullable = true, length = 100)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "SECONDNAME",unique = true, nullable = true, length = 100)
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	@Column(name = "CORRESPONDIDENTITY",unique = true, nullable = true, length = 11)
	public String getCorrespondIdentity() {
		return correspondIdentity;
	}
	public void setCorrespondIdentity(String correspondIdentity) {
		this.correspondIdentity = correspondIdentity;
	}

	
}
