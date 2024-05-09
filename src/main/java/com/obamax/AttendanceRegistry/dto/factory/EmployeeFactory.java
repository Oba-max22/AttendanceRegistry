package com.obamax.AttendanceRegistry.dto.factory;

import com.obamax.AttendanceRegistry.dto.EmployeeRequest;
import com.obamax.AttendanceRegistry.model.Department;
import com.obamax.AttendanceRegistry.model.Employee;

public interface EmployeeFactory {
    Employee createEmployee(EmployeeRequest request);
}
