package com.beastmode.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Feedback {

    @Id
    private String feedbackId;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    @Min(1) @Max(5)
    private Integer rating; // 1-5 scale

    @Column(nullable = false)
    private FeedbackType feedbackType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "trainer_id")
    private Trainer trainer; // Optional if feedback is trainer-specific

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt;
}