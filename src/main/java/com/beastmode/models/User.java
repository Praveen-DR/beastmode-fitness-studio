package com.beastmode.models;

import com.beastmode.models.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Payment> payments;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "membership_id")
    private Membership memberships;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH)
    @JsonManagedReference
    private List<Feedback> feedbacks;

}
