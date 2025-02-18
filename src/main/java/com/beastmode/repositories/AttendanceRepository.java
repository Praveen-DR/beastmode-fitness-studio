package com.beastmode.repositories;

import com.beastmode.models.Attendance;
import com.beastmode.models.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    Optional<Attendance> findByAttendanceType(AttendanceType attendanceType);
}
