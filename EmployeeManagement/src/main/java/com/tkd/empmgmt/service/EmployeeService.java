package com.tkd.empmgmt.service;

import java.util.List;

import com.tkd.empmgmt.entity.Employee;
import com.tkd.empmgmt.exception.EmployeeMgmtException;
import com.tkd.empmgmt.exception.UserCreationException;
import com.tkd.empmgmt.exception.UserDeletionException;

public interface EmployeeService {

	Employee createEmployee(Employee employee) throws UserCreationException;
	
	List<Employee> employeesBasedOnSalary();
	
	void deleteEmployee(Long empId) throws UserDeletionException;
	
	String getPositionHierarchy(String currentPosition) throws EmployeeMgmtException;
}
