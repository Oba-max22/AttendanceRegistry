package com.obamax.AttendanceRegistry.repository;

import com.obamax.AttendanceRegistry.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(value = "SELECT * FROM attendance WHERE date_created BETWEEN ?1 and ?2", nativeQuery = true)
    List<Attendance> findByDateRange(Date startDate, Date endDate);

    Attendance findByEmployeeIdAndDateCreated(Long employeeId, Date date);
}