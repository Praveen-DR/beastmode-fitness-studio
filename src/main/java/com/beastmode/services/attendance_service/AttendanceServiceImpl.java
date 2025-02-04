package com.beastmode.services.attendance_service;

import com.beastmode.repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    private final AttendanceRepository attendanceRepository;

    AttendanceServiceImpl(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }




}
