package com.tkd.empmgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkd.empmgmt.entity.Employee;
import com.tkd.empmgmt.entity.Reportee;

@Repository
public interface ReporteeRepository extends JpaRepository<Reportee, Long> {

	List<Reportee> findAllByReporteeId(Long reporteeId);
	
	List<Reportee> findAllByEmployee(Employee employee);
	
	@Transactional
	void deleteByEmployeeEmpId(Long empId);
}
