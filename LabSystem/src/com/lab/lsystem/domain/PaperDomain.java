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
	private String tilte;//论文题目
	private String firstAuthor;//第一作者
	private Integer firstIdentity;//第一作者身份
	private String secondAuthor;//第二作者
	private Integer secondIdentify;//第二作者身份
	private String corrspondAuthor;//通讯作者
	private String otherAuthors;//其他作者
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date publishDate;//出版时间
	private String journalTitle;//期刊名称
	private String projectSource;//项目来源
	private String journalLevel;//刊物级别
	private String discipline;//一级学科
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
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
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
	@Column(name = "CORRSPONDAUTHOR",unique = true, nullable = true, length = 200)
	public String getCorrspondAuthor() {
		return corrspondAuthor;
	}
	public void setCorrspondAuthor(String corrspondAuthor) {
		this.corrspondAuthor = corrspondAuthor;
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
	public Integer getSecondIdentify() {
		return secondIdentify;
	}
	public void setSecondIdentify(Integer secondIdentify) {
		this.secondIdentify = secondIdentify;
	}

	
}
