package com.lab.lsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="PROJECT_PERSON")
public class ProjectPersonDomain {
	private String id;//id
	private String projectId;//项目Id
	private String personCode;//参与人员号
	private String personName;//参与人员姓名
	private Integer personOrder;//参与人员顺序
	public ProjectPersonDomain() {
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
	@Column(name = "PROJECTID",unique = true, nullable = true, length = 100)
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	@Column(name = "PERSONCODE",unique = true, nullable = true, length = 100)
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	@Column(name = "PERSONNAME",unique = true, nullable = true, length = 100)
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	@Column(name = "PERSONORDER",unique = true, nullable = true, length = 11)
	public Integer getPersonOrder() {
		return personOrder;
	}
	public void setPersonOrder(Integer personOrder) {
		this.personOrder = personOrder;
	}
	
	
}
