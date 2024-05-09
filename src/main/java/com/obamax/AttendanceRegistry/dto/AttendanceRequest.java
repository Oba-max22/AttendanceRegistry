package com.obamax.AttendanceRegistry.dto;

import com.obamax.AttendanceRegistry.model.enums.AvailabilityStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AttendanceRequest {

    @NotNull
    private Long employeeId;

    private AvailabilityStatus availabilityStatus;
}
