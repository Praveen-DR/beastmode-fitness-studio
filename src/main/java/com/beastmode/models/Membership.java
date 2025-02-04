package com.beastmode.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Membership {
    @Id
    private String membershipId;

    @Column(nullable = false, unique = true)
    private String membershipName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String durationInMonths;

    @Column(nullable = false)
    private boolean isActive;

    @ManyToMany(mappedBy = "membership")
    private List<User> users;

}
