package com.obamax.AttendanceRegistry.repository;

import com.obamax.AttendanceRegistry.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String engineering);
}