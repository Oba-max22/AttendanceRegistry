package com.obamax.AttendanceRegistry.dto;

import com.obamax.AttendanceRegistry.model.enums.EmployeeType;
import com.obamax.AttendanceRegistry.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeUpdateRequest {

    @NotNull
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
    private Long departmentId;
    private EmployeeType employeeType;
}
