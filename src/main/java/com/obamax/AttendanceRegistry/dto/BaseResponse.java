package com.obamax.AttendanceRegistry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private String message;
    private boolean success;
    private T data;
}
