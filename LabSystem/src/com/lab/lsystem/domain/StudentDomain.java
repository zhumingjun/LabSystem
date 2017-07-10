package com.lab.lsystem.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Integer grade;//年级
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date birthday;//生日
	private String phoneNumber;//手机号
	private String email;//邮箱
	private String homeAddress;//家庭住址
	private String entryYear;//入学年份
	private String contactPerson;//联系人
	private String familyContact;//家庭联系方式
	private String headImg;//头像
	private Integer isGraduate;//是否毕业
	private TeacherDomain tutorDomain;//导师；
	private TeacherDomain restutorDomain;//责任导师
	private Set<PaperDomain> papers=new HashSet<PaperDomain>();
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
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
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
	@Column(name = "CONTACTPERSON",unique = true, nullable = false, length = 100)
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	@Column(name = "FAMILYCONTACT",unique = true, nullable = false, length = 100)
	public String getFamilyContact() {
		return familyContact;
	}
	public void setFamilyContact(String familyContact) {
		this.familyContact = familyContact;
	}
	@Column(name = "HEADIMG",unique = true, nullable = false, length = 200)
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	@Column(name = "ISGRADUATE",unique = true, nullable = false, length =11)
	public Integer getIsGraduate() {
		return isGraduate;
	}
	public void setIsGraduate(Integer isGraduate) {
		this.isGraduate = isGraduate;
	}
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "TUTORID")
	public TeacherDomain getTutorDomain() {
		return tutorDomain;
	}
	public void setTutorDomain(TeacherDomain tutorDomain) {
		this.tutorDomain = tutorDomain;
	}
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "RESTUTOR")
	public TeacherDomain getRestutorDomain() {
		return restutorDomain;
	}
	public void setRestutorDomain(TeacherDomain restutorDomain) {
		this.restutorDomain = restutorDomain;
	}
	@ManyToMany(cascade=CascadeType.REFRESH,mappedBy="studentAuthors")
	public Set<PaperDomain> getPapers() {
		return papers;
	}
	public void setPapers(Set<PaperDomain> papers) {
		this.papers = papers;
	}
	
}
