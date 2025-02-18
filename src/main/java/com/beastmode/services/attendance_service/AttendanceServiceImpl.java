package com.beastmode.services.attendance_service;

import com.beastmode.exceptions.ApiRequestException;
import com.beastmode.mappers.AttendanceMapper;
import com.beastmode.models.Attendance;
import com.beastmode.models.AttendanceType;
import com.beastmode.models.User;
import com.beastmode.repositories.AttendanceRepository;
import com.beastmode.services.user_service.UserService;
import com.beastmode.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final UUIDUtil uuidUtil;
    private final UserService userService;


    AttendanceServiceImpl(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper, UUIDUtil uuidUtil, UserService userService){
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
        this.uuidUtil = uuidUtil;
        this.userService = userService;
    }


    @Override
    public String markAttendance(LocalDateTime checkInTime, LocalDateTime checkOutTime, AttendanceType attendanceType, String userId) {
        User user = userService.getUserById(userId);
        Attendance attendance = attendanceMapper.toAttendance(uuidUtil.generateUuid(), checkInTime, checkOutTime, attendanceType, user);
        attendanceRepository.save(attendance);
        return "Attendance Marked Successfully";
    }

    @Override
    public Attendance getAttendanceById(String attendanceId) {
        return attendanceRepository.findById(attendanceId).orElseThrow(()->new ApiRequestException("Attendance ID not found"));
    }

    @Override
    public Attendance getAttendanceByType(AttendanceType attendanceType) {
        return attendanceRepository.findByAttendanceType(attendanceType).orElseThrow(()->new ApiRequestException("Attendance Type Not found"));
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
