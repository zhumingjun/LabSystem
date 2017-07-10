package com.lab.lsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="STUDENT_PAPER")
public class StudentPaperDomain {
	private String id;//id
	private String stuId;//学生id
	private String paperId;//；论文ID
	public StudentPaperDomain() {
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
	@Column(name = "STUID",unique = true, nullable = false, length = 100)
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	@Column(name = "PAPERID",unique = true, nullable = false, length = 100)
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	
}
