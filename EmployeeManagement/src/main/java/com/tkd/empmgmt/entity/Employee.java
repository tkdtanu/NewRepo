package com.tkd.empmgmt.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="EMPLOYEE")
public class Employee {
	@Id
	@Column(name="EMP_ID")
	private Long empId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SALARY")
	private Long salary;
	
	@Column(name="RATING")
	private Integer rating;
	
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private Role role;
	
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
	private Set<Reportee> reportee;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Reportee> getReportee() {
		return reportee;
	}

	public void setReportee(Set<Reportee> reportee) {
		this.reportee = reportee;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", salary=" + salary + ", rating=" + rating + ", role="
				+ role + ", reportee=" + reportee + "]";
	}

	public Employee() {
		super();
	}

	public Employee(Long empId, String name, Long salary, Integer rating, Boolean active, Role role,
			Set<Reportee> reportee) {
		super();
		this.empId = empId;
		this.name = name;
		this.salary = salary;
		this.rating = rating;
		this.active = active;
		this.role = role;
		this.reportee = reportee;
	}

	
}
