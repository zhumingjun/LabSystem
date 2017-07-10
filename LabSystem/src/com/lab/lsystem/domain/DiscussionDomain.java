package com.lab.lsystem.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="DISCUSSION")
public class DiscussionDomain {
	private String id;//id
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date discussionDate;//研讨时间
	private String location;//研讨地点
	private String content;//研讨内容
	private String participant;//参与人员
	public DiscussionDomain() {
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
	@Column(name = "DISCUSSIONDATE",unique = true, nullable = true, length = 100)
	public Date getDiscussionDate() {
		return discussionDate;
	}
	public void setDiscussionDate(Date discussionDate) {
		this.discussionDate = discussionDate;
	}
	@Column(name = "LOCATION",unique = true, nullable = true, length = 100)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "CONTENT",unique = true, nullable = true, length = 500)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "PARTICIPANT",unique = true, nullable = true, length = 300)
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	
	
}
