package com.tkd.empmgmt.service;

import java.util.List;

import com.tkd.empmgmt.entity.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);
	
	List<Employee> employeesBasedOnSalary();
	
	void deleteEmployee(Long empId);
}
