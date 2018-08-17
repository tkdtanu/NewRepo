package com.tkd.empmgmt.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity(name="ROLE")
public class Role {

	@Id
	@GeneratedValue
	@Column(name="ROLE_ID")
	private Long roleId;
	
	@Column(name="ROLE",unique=true)
	private String roleType;
	
	@OneToMany(mappedBy="role")
	@JsonIgnore
	private Set<Employee> employee;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	public Role() {
		super();
	}

	public Role(Long roleId, String roleType, Set<Employee> employee) {
		super();
		this.roleId = roleId;
		this.roleType = roleType;
		this.employee = employee;
	}

	
	
}
