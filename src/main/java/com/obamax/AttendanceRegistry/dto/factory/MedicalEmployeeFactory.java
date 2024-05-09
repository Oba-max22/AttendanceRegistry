package com.obamax.AttendanceRegistry.dto.factory;

import com.obamax.AttendanceRegistry.dto.EmployeeRequest;
import com.obamax.AttendanceRegistry.model.Department;
import com.obamax.AttendanceRegistry.model.Employee;
import com.obamax.AttendanceRegistry.model.enums.EmployeeType;

public class MedicalEmployeeFactory implements EmployeeFactory {

    @Override
    public Employee createEmployee(EmployeeRequest request) {
        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .address(request.getAddress())
                .employeeType(EmployeeType.MEDICAL)
                .departmentId(request.getDepartmentId())
                .build();
    }
}
