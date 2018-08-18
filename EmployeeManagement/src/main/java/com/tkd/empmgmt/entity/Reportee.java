package com.tkd.empmgmt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="REPORTEE")
public class Reportee {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="EMP_ID")
	@JsonIgnore
	Employee employee;
	
	private Long reporteeId;

	public Reportee(Long id, Employee employee, Long reporteeId) {
		super();
		this.id = id;
		this.employee = employee;
		this.reporteeId = reporteeId;
	}

	public Reportee() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getReporteeId() {
		return reporteeId;
	}

	public void setReporteeId(Long reporteeId) {
		this.reporteeId = reporteeId;
	}

	@Override
	public String toString() {
		return "Reportee [id=" + id + ", reporteeId=" + reporteeId + "]";
	}
	
	
}
