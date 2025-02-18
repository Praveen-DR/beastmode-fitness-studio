package com.beastmode.services.attendance_service;

import com.beastmode.models.Attendance;
import com.beastmode.models.AttendanceType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface AttendanceService {
    String markAttendance(LocalDateTime checkInTime, LocalDateTime checkOutTime, AttendanceType attendanceType, String userId);

    Attendance getAttendanceById(String attendanceId);

    Attendance getAttendanceByType(AttendanceType attendanceType);

    List<Attendance> getAllAttendance();

}
