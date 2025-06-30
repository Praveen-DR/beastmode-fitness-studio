package com.beastmode.mappers;

import com.beastmode.models.Attendance;
import com.beastmode.models.AttendanceType;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttendanceMapper {
    public Attendance toAttendance(String attendanceId, LocalDateTime checkInTime, LocalDateTime checkOutTime, AttendanceType attendanceType, User user) {
        return Attendance.builder()
                .attendanceId(attendanceId)
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .attendanceType(attendanceType)
                .user(user)
                .build();
    }
}



