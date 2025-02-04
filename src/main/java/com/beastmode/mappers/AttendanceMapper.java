package com.beastmode.mappers;

import com.beastmode.models.Attendance;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

@Service
public class AttendanceMapper {
    public Attendance toAttendance(String attendanceId, User userId, String date) {
        return Attendance.builder()
                .attendanceId(attendanceId)
                .user(userId)
                .date(date)
                .build();
    }
}
