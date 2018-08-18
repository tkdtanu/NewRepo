package com.tkd.empmgmt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.LongStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tkd.empmgmt.constant.ExceptionMessageConstant;
import com.tkd.empmgmt.entity.Employee;
import com.tkd.empmgmt.entity.Reportee;
import com.tkd.empmgmt.entity.Role;
import com.tkd.empmgmt.enums.RoleTypeEnum;
import com.tkd.empmgmt.exception.EmployeeMgmtException;
import com.tkd.empmgmt.exception.UserCreationException;
import com.tkd.empmgmt.exception.UserDeletionException;
import com.tkd.empmgmt.repository.EmployeeRepository;
import com.tkd.empmgmt.repository.ReporteeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ReporteeRepository reporteeRepository;

	@Override
	@Transactional
	public Employee createEmployee(Employee employee) throws UserCreationException {
		employee.setActive(true);
		/*
		 * Validation For Role
		 */
		Role role = employee.getRole();
		if (role == null || role.getRoleId() == null) {
			throw new UserCreationException(ExceptionMessageConstant.USER_ROLE_BLANK_EXCEPTION);
		}
		if (!RoleTypeEnum.getRoleById(role.getRoleId()).isPresent()) {
			throw new UserCreationException(ExceptionMessageConstant.INVALID_USER_ROLE_EXCEPTION);
		}
		Set<Reportee> reportees = Optional.ofNullable(employee.getReportee()).orElse(new HashSet<>());
		/*
		 * Reportee Is Mandatory Other than CEO
		 */
		checkForValidReportee(employee, reportees);
		for (Reportee reportee : reportees) {
			Optional<Employee> employeeWithGivenId = employeeRepository.findById(reportee.getReporteeId());
			/*
			 * Employee Id Check For a given Reportee Id
			 */
			if (!employeeWithGivenId.isPresent()) {
				throw new UserCreationException(
						ExceptionMessageConstant.REPORTEE_NOT_FOUND_EXCEPTION + reportee.getReporteeId());
			} else if (employeeWithGivenId.get().getRole().getRoleId() == RoleTypeEnum.SDE.getRoleId()) {
				/*
				 * No Employee can report to SDE
				 */
				throw new UserCreationException(ExceptionMessageConstant.INVALID_REPORTEE_EXCEPTION);

			}

		}

		/*
		 * Set employee id to reportee table
		 */
		reportees.stream().forEach(a -> a.setEmployee(employee));

		Employee newEmployee = null;
		try {
			newEmployee = employeeRepository.save(employee);
		} catch (Exception e) {
			throw new UserCreationException();
		}
		return newEmployee;
	}

	private void checkForValidReportee(Employee employee, Set<Reportee> reportees) throws UserCreationException {
		if (reportees.stream().filter(a -> a.getReporteeId() == null).count() > 0) {
			throw new UserCreationException(ExceptionMessageConstant.BLANK_REPORTEE_EXCEPTION);
		}
		if (employee.getRole().getRoleId() == RoleTypeEnum.CEO.getRoleId() && !reportees.isEmpty()) {
			throw new UserCreationException(ExceptionMessageConstant.INVALID_REPORTEE_ARGUMENT_FOR_CEO_EXCEPTION);
		}
	}

	@Override
	public List<Employee> employeesBasedOnSalary() {
		return employeeRepository.findTop10ByOrderBySalaryDesc();
	}

	@Override
	@Transactional
	public void deleteEmployee(Long empId) throws UserDeletionException {
		Optional<Employee> result = employeeRepository.findById(empId);
		if (!result.isPresent()) {
			throw new UserDeletionException(ExceptionMessageConstant.EMPLOYEE_NOT_FOUND_EXCEPTION);
		}
		Employee empToDelete = result.get();
		List<Reportee> reporteesToChange = new ArrayList<>();
		List<Employee> empToChangeReporting = new ArrayList<>();
		List<Reportee> reportee = reporteeRepository.findAllByEmployee(empToDelete);
		if (empToDelete.getRole().getRoleType().equals(RoleTypeEnum.MANAGER.getRole())) {
			reporteesToChange = reporteeRepository.findAllByReporteeId(empToDelete.getEmpId());
			reporteesToChange.stream().forEach(a -> empToChangeReporting.add(a.getEmployee()));

		}
		empToChangeReporting.stream()
				.forEach(a -> a.getReportee().stream().filter(b -> b.getReporteeId() == empToDelete.getEmpId())
						.forEach(c -> c.setReporteeId(reportee.get(0).getReporteeId())));
		try {
			if (!empToChangeReporting.isEmpty()) {
				employeeRepository.saveAll(empToChangeReporting);
			}
			if (!reportee.isEmpty()) {
				reporteeRepository.deleteAll(reportee);
			}
			employeeRepository.delete(empToDelete);
		} catch (Exception e) {
			throw new UserDeletionException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@Override
	public String getPositionHierarchy(String currentPosition) throws EmployeeMgmtException {
		Optional<RoleTypeEnum> currentRole = RoleTypeEnum.getRoleByType(currentPosition);
		if (!currentRole.isPresent()) {
			throw new EmployeeMgmtException(ExceptionMessageConstant.INVALID_POSITION_EXCEPTION);
		} else {
			StringBuilder builder = new StringBuilder();
			Long currentRoleId = currentRole.get().getRoleId() + 1;
			LongStream.range(1, currentRoleId).map(i -> currentRoleId - i)
					.forEach(a -> builder.append(RoleTypeEnum.getRoleById(a).get().getRole() + (a !=1 ? "-->" : "")));
			return builder.toString();
		}
	}

}
