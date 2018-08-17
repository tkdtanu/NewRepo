package com.tkd.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tkd.empmgmt.entity.Employee;
import com.tkd.empmgmt.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee/create")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@PostMapping("/employee/findfirst")
	public List<Employee> employeesBaesOnSalary() {
		return employeeService.employeesBasedOnSalary();
	}
	
	@PostMapping("employee/delete/{empId}")
	public void deleteEmployee(@PathVariable(name = "empId")Long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}
}
