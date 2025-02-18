package com.beastmode.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
    @Id
    private String attendanceId; // Primary Key

    @Column(nullable = false)
    private LocalDateTime checkInTime;

    @Column
    private LocalDateTime checkOutTime;

    @Column(nullable = false)
    private AttendanceType attendanceType;  // "Member" or "Trainer"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

