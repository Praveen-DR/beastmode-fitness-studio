package com.beastmode.dto.request;

import com.beastmode.models.AttendanceType;
import com.beastmode.models.User;

import java.time.LocalDateTime;

public record MarkAttendanceDto(LocalDateTime checkInTime, LocalDateTime checkOutTime, AttendanceType attendanceType, String userId) {
}
