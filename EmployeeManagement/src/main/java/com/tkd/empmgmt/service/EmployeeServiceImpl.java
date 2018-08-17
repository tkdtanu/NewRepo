package com.tkd.empmgmt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tkd.empmgmt.entity.Employee;
import com.tkd.empmgmt.entity.Reportee;
import com.tkd.empmgmt.repository.EmployeeRepository;
import com.tkd.empmgmt.repository.ReporteeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ReporteeRepository reporteeRepository;
	
	@Override
	public Employee createEmployee(Employee employee) {
		employee.setActive(true);
		if(employee.getRole() == null || employee.getRole().getRoleId() == null) {
			throw new RuntimeException("Role Can't be Blank 1-CEO, 2->VP, 3->Director,4->Manager,5-SDE");
		}
		Set<Reportee> reportee = Optional.ofNullable(employee.getReportee()).orElse(new HashSet<>());
/*		if(reportee.stream().filter(a -> a.getId() == 5).count()>0) {
			throw new RuntimeException("You can't report to SDE");
		}*/
		reportee.stream().forEach(a -> a.setEmployee(employee));
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> employeesBasedOnSalary() {
		return employeeRepository.findTop10AllOrderBySalary(new Sort(Sort.Direction.DESC, "salary"));
	}

	@Override
	public void deleteEmployee(Long empId) {
		Optional<Employee> result = employeeRepository.findById(empId);
		if(result.get() ==null) {
			throw new RuntimeException("Employee Not Found");
		}
		Employee empToDelete = result.get();
		if(empToDelete.getRole().getRoleType().equals("Manager")) {
			List<Employee> empToChangeReporting = reporteeRepository.findEmployeeByReporteeId(empToDelete.getEmpId());
		}
		employeeRepository.delete(empToDelete);
	}
	

}
