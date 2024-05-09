package com.obamax.AttendanceRegistry.controller;

import com.obamax.AttendanceRegistry.dto.EmployeeRequest;
import com.obamax.AttendanceRegistry.dto.EmployeeUpdateRequest;
import com.obamax.AttendanceRegistry.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/medical")
    public ResponseEntity<?> createMedicalEmployee(@Valid @RequestBody EmployeeRequest employeeRequestDTO) {
        return ResponseEntity.ok().body(employeeService.createMedicalEmployee(employeeRequestDTO));
    }

    @PostMapping("/non-medical")
    public ResponseEntity<?> createNonMedicalEmployee(@Valid @RequestBody EmployeeRequest employeeRequestDTO) {
        return ResponseEntity.ok().body(employeeService.createNonMedicalEmployee(employeeRequestDTO));
    }

    @GetMapping("")
    public ResponseEntity<?> fetchEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<?> fetchEmployeesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok().body(employeeService.getAllEmployeesByDepartment(departmentId));
    }

    @PatchMapping("")
    public ResponseEntity<?> modifyEmployee(@RequestBody EmployeeUpdateRequest requestDTO) {
        return ResponseEntity.ok().body(employeeService.updateEmployee(requestDTO));
    }
}
