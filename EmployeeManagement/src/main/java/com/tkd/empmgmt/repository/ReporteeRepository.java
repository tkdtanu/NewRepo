package com.tkd.empmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkd.empmgmt.entity.Employee;
import com.tkd.empmgmt.entity.Reportee;

@Repository
public interface ReporteeRepository extends JpaRepository<Reportee, Long> {

	List<Employee> findEmployeeByReporteeId(Long reporteeId);
	
	List<Employee> findEmployeeByEmployee(Employee employee);
}
