package com.beastmode.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trainer {
    @Id
    private String trainerId;// Primary Key

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private int experience; // Experience in years

    @Column
    private boolean isActive = true;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "trainer")
    @JsonManagedReference
    private List<User> users;

    @OneToMany(mappedBy = "trainer")
    @JsonManagedReference
    private List<Feedback> feedbacks;
}
