package com.beastmode.controllers;

import com.beastmode.dto.request.MarkAttendanceDto;
import com.beastmode.models.Attendance;
import com.beastmode.models.AttendanceType;
import com.beastmode.services.attendance_service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/api/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;

    AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    @PostMapping("/v1/markAttendance")
    ResponseEntity<String> markAttendance(@RequestBody MarkAttendanceDto data){
        String message = attendanceService.markAttendance(data.checkInTime(), data.checkOutTime(), data.attendanceType(), data.userId());
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("v1/getAttendanceById")
    ResponseEntity<Attendance> getAttendanceById(@RequestParam("AttendanceId") String attendanceId){
        return ResponseEntity.status(200).body(attendanceService.getAttendanceById(attendanceId));
    }

    @GetMapping("/v1/getAttendanceByType")
    ResponseEntity<Attendance> getAttendanceByType(@RequestParam("AttendanceType") AttendanceType attendanceType){
        return ResponseEntity.status(200).body(attendanceService.getAttendanceByType(attendanceType));
    }

    @GetMapping("/v1/getAllAttendance")
    ResponseEntity<List<Attendance>> getAllAttendance(){
        return ResponseEntity.status(200).body(attendanceService.getAllAttendance());
    }


}
