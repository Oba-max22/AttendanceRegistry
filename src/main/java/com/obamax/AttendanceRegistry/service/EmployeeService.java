package com.obamax.AttendanceRegistry.service;

import com.obamax.AttendanceRegistry.dto.BaseResponse;
import com.obamax.AttendanceRegistry.dto.EmployeeRequest;
import com.obamax.AttendanceRegistry.dto.EmployeeUpdateRequest;
import com.obamax.AttendanceRegistry.dto.factory.EmployeeFactory;
import com.obamax.AttendanceRegistry.dto.factory.MedicalEmployeeFactory;
import com.obamax.AttendanceRegistry.dto.factory.NonMedicalEmployeeFactory;
import com.obamax.AttendanceRegistry.exceptions.BadRequestException;
import com.obamax.AttendanceRegistry.exceptions.NotFoundException;
import com.obamax.AttendanceRegistry.model.Department;
import com.obamax.AttendanceRegistry.model.Employee;
import com.obamax.AttendanceRegistry.repository.DepartmentRepository;
import com.obamax.AttendanceRegistry.repository.EmployeeRepository;
import com.obamax.AttendanceRegistry.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public BaseResponse<Employee> createMedicalEmployee(EmployeeRequest employeeRequestDTO) {

        Department department = departmentRepository.findById(employeeRequestDTO.getDepartmentId())
                .orElseThrow(() -> new BadRequestException(ConstantUtil.INVALID_DEPARTMENT));

        EmployeeFactory medicalFactory = new MedicalEmployeeFactory();
        Employee employee = medicalFactory.createEmployee(employeeRequestDTO);

        Employee savedEmployee = employeeRepository.save(employee);
        return BaseResponse.<Employee>builder()
                .message(ConstantUtil.RECORD_SAVED_MESSAGE)
                .success(true)
                .data(savedEmployee)
                .build();
    }

    public BaseResponse<Employee> createNonMedicalEmployee(EmployeeRequest employeeRequestDTO) {

        Department department = departmentRepository.findById(employeeRequestDTO.getDepartmentId())
                .orElseThrow(() -> new BadRequestException(ConstantUtil.INVALID_DEPARTMENT));

        EmployeeFactory medicalFactory = new NonMedicalEmployeeFactory();
        Employee employee = medicalFactory.createEmployee(employeeRequestDTO);

        Employee savedEmployee = employeeRepository.save(employee);
        return BaseResponse.<Employee>builder()
                .message(ConstantUtil.RECORD_SAVED_MESSAGE)
                .success(true)
                .data(savedEmployee)
                .build();
    }

    public BaseResponse<?> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return BaseResponse.builder()
                .message(ConstantUtil.FETCH_SUCCESS_MESSAGE)
                .success(true)
                .data(employees)
                .build();
    }

    public BaseResponse<?> getAllEmployeesByDepartment(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        return BaseResponse.builder()
                .message(ConstantUtil.FETCH_SUCCESS_MESSAGE)
                .success(true)
                .data(employees)
                .build();
    }

    public BaseResponse<Employee> updateEmployee(EmployeeUpdateRequest requestDTO) {

        Employee employee = employeeRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new NotFoundException(ConstantUtil.EMPLOYEE_NOT_FOUND));

        if (requestDTO.getEmployeeType() != null) {
            employee.setEmployeeType(requestDTO.getEmployeeType());
        }

        Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new BadRequestException(ConstantUtil.INVALID_DEPARTMENT));

        if (department != null) {
            employee.setDepartmentId(requestDTO.getDepartmentId());
        }

        if (requestDTO.getGender() != null) {
            employee.setGender(requestDTO.getGender());
        }

        if (requestDTO.getAddress() != null && !requestDTO.getAddress().isEmpty()) {
            employee.setAddress(requestDTO.getAddress());
        }

        if (requestDTO.getFirstName() != null && !requestDTO.getFirstName().isEmpty()) {
            employee.setFirstName(requestDTO.getFirstName());
        }

        if (requestDTO.getLastName() != null && !requestDTO.getLastName().isEmpty()) {
            employee.setLastName(requestDTO.getLastName());
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return BaseResponse.<Employee>builder()
                .message(ConstantUtil.RECORD_UPDATED_MESSAGE)
                .success(true)
                .data(savedEmployee)
                .build();
    }
}
