package com.obamax.AttendanceRegistry.service;

import com.obamax.AttendanceRegistry.dto.BaseResponse;
import com.obamax.AttendanceRegistry.model.Department;
import com.obamax.AttendanceRegistry.repository.DepartmentRepository;
import com.obamax.AttendanceRegistry.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public BaseResponse<?> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return BaseResponse.builder()
                .message(ConstantUtil.FETCH_SUCCESS_MESSAGE)
                .success(true)
                .data(departments)
                .build();
    }
}
