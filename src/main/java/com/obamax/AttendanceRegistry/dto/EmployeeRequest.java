package com.obamax.AttendanceRegistry.dto;

import com.obamax.AttendanceRegistry.model.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;

    @NotNull
    private Gender gender;

    @NotNull
    private Long departmentId;
}
