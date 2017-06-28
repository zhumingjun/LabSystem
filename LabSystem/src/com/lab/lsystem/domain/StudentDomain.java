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
@Table(name="STUDENT")
public class StudentDomain {
	private String id; //id
	private String name;//学生姓名
	private String stuCode;//学生学号
	private int sex;//学生性别
	private String bankCard;//学生账号
	private String idNumber;//身份证号
	private int grade;//年级
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date birthday;//生日
	private String phoneNumber;//手机号
	private String email;//邮箱
	private String homeAddress;//家庭住址
	private String entryYear;//入学年份
	public StudentDomain() {
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
	@Column(name = "NAME",unique = true, nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "STUCODE",unique = true, nullable = false, length = 100)
	public String getStuCode() {
		return stuCode;
	}
	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}
	@Column(name = "SEX",unique = true, nullable = false, length =11)
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Column(name = "BANKCARD",unique = true, nullable = false, length = 100)
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	@Column(name = "IDNUMBER",unique = true, nullable = false, length = 100)
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name = "GRADE",unique = true, nullable = false, length = 11)
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Column(name = "BIRTHDAY",unique = true, nullable = false, length = 100)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column(name = "PHONENUMBER",unique = true, nullable = false, length = 100)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "EMAIL",unique = true, nullable = false, length = 100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "HOMEADDRESS",unique = true, nullable = false, length = 255)
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	@Column(name = "ENTRYYEAR",unique = true, nullable = false, length = 100)
	public String getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}
	
}
