package com.tkd.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tkd.empmgmt.entity.Employee;
import com.tkd.empmgmt.exception.EmployeeMgmtException;
import com.tkd.empmgmt.exception.UserCreationException;
import com.tkd.empmgmt.exception.UserDeletionException;
import com.tkd.empmgmt.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee/create")
	public Employee createEmployee(@RequestBody Employee employee) throws UserCreationException {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/employee/findfirst/{count}")
	public List<Employee> employeesBaesOnSalary() {
		return employeeService.employeesBasedOnSalary();
	}
	
	@DeleteMapping("employee/delete/{empId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "empId")Long employeeId) throws UserDeletionException {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee Deleted Successfully");
	}
	
	@GetMapping("/employee/hierarchy/{position}")
	public String getPositionHierarchy(@PathVariable(name = "position") String currentPosition) throws EmployeeMgmtException {
		return employeeService.getPositionHierarchy(currentPosition);
	}
}
