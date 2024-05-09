package com.obamax.AttendanceRegistry.controller;

import com.obamax.AttendanceRegistry.dto.AttendanceRequest;
import com.obamax.AttendanceRegistry.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("")
    public ResponseEntity<?> registerAttendance(@Valid @RequestBody AttendanceRequest requestDTO) {
        return ResponseEntity.ok().body(attendanceService.registerAttendance(requestDTO));
    }

    @PatchMapping("/{id}/sign-in")
    public ResponseEntity<?> signInAttendance(@PathVariable Long id) {
        return ResponseEntity.ok().body(attendanceService.signInAttendance(id));
    }

    @PatchMapping("/{id}/sign-out")
    public ResponseEntity<?> signOutAttendance(@PathVariable Long id) {
        return ResponseEntity.ok().body(attendanceService.signOutAttendance(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> fetchAttendance(@RequestParam("fromDate") String fromDate, String toDate) throws ParseException {
        return ResponseEntity.ok().body(attendanceService.filterAttendance(fromDate, toDate));
    }
}
