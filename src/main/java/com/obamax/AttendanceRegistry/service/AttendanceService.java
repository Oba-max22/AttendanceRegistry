package com.obamax.AttendanceRegistry.service;

import com.obamax.AttendanceRegistry.dto.AttendanceRequest;
import com.obamax.AttendanceRegistry.dto.BaseResponse;
import com.obamax.AttendanceRegistry.exceptions.BadRequestException;
import com.obamax.AttendanceRegistry.exceptions.IllegalActionException;
import com.obamax.AttendanceRegistry.model.Attendance;
import com.obamax.AttendanceRegistry.model.Department;
import com.obamax.AttendanceRegistry.model.Employee;
import com.obamax.AttendanceRegistry.model.enums.AvailabilityStatus;
import com.obamax.AttendanceRegistry.repository.AttendanceRepository;
import com.obamax.AttendanceRegistry.repository.EmployeeRepository;
import com.obamax.AttendanceRegistry.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public BaseResponse<Attendance> registerAttendance(AttendanceRequest request) {
        Attendance attendance = new Attendance();

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new BadRequestException(ConstantUtil.INVALID_EMPLOYEE));

        Attendance existingAttendance = attendanceRepository.findByEmployeeIdAndDateCreated(request.getEmployeeId(), new Date());
        if (existingAttendance != null) {
            attendance = existingAttendance;
        }

        attendance.setEmployeeId(employee.getId());

        if (request.getAvailabilityStatus() != null) {
            attendance.setAvailabilityStatus(request.getAvailabilityStatus());
        }

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return BaseResponse.<Attendance>builder()
                .message(ConstantUtil.RECORD_SAVED_MESSAGE)
                .success(true)
                .data(savedAttendance)
                .build();
    }

    private Date convertToDate(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(dateStr);
    }

    public BaseResponse<?> filterAttendance(String fromDate, String toDate) throws ParseException {
        Date fromFormattedDate = fromDate != null ? this.convertToDate(fromDate) : null;
        Date toFormattedDate = toDate != null ? this.convertToDate(toDate) : null;

        List<Attendance> attendances = attendanceRepository.findByDateRange(fromFormattedDate, toFormattedDate);

        return BaseResponse.builder()
                .message(ConstantUtil.RECORD_SAVED_MESSAGE)
                .success(true)
                .data(attendances)
                .build();
    }

    public BaseResponse<?> signInAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ConstantUtil.INVALID_ATTENDANCE));

        attendance.setAvailabilityStatus(AvailabilityStatus.PRESENT);
        attendance.setSignedIn(true);
        attendance.setSignedInTime(new Date());

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return BaseResponse.builder()
                .message(ConstantUtil.RECORD_UPDATED_MESSAGE)
                .success(true)
                .data(savedAttendance)
                .build();
    }

    public BaseResponse<?> signOutAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ConstantUtil.INVALID_ATTENDANCE));

        if (!attendance.isSignedIn()) {
            throw new IllegalActionException();
        }

        attendance.setSignedIn(false);
        attendance.setSignedOutTime(new Date());

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return BaseResponse.builder()
                .message(ConstantUtil.RECORD_UPDATED_MESSAGE)
                .success(true)
                .data(savedAttendance)
                .build();
    }
}
