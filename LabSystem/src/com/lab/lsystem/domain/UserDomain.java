package com.lab.lsystem.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.lab.lsystem.domain.RoleDomain;

/**
 * 用户登录控制
 * @author zhu
 *
 */
@Entity
@Table(name="USER")
public class UserDomain {
	private String id; //id
	
	private String username; 
	
	private String password; 
	
	private RoleDomain role;	//角色
	
	public UserDomain() {
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
	@Column(name = "USERNAME",unique = true, nullable = false, length = 100)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ROLEID")
	public RoleDomain getRole() {
		return role;
	}

	public void setRole(RoleDomain role) {
		this.role = role;
	}
	

}
