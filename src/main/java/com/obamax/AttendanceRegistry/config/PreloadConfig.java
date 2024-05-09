package com.obamax.AttendanceRegistry.config;

import com.obamax.AttendanceRegistry.model.Department;
import com.obamax.AttendanceRegistry.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PreloadConfig implements CommandLineRunner {

    public static final String ENGINEERING = "ENGINEERING";
    public static final String OPERATIONS = "OPERATIONS";
    public static final String MANAGEMENT = "MANAGEMENT";

    private final DepartmentRepository departmentRepository;

    private void populateDepartmentTable() {
        log.info(">> Populating Department Table");
        List<Department> departments = new ArrayList<>();

        if (!departmentRepository.existsByName(ENGINEERING))
            departments.add(Department.builder().name(ENGINEERING).build());
        if (!departmentRepository.existsByName(OPERATIONS))
            departments.add(Department.builder().name(OPERATIONS).build());
        if (!departmentRepository.existsByName(MANAGEMENT))
            departments.add(Department.builder().name(MANAGEMENT).build());

        this.departmentRepository.saveAll(departments);
    }

    @Override
    public void run(String... args) {
        this.populateDepartmentTable();
    }
}
