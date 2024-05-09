package com.obamax.AttendanceRegistry.controller;


import com.obamax.AttendanceRegistry.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<?> fetchDepartments() {
        return ResponseEntity.ok().body(departmentService.getAllDepartments());
    }
}
