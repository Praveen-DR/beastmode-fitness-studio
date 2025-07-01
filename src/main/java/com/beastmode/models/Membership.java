package com.beastmode.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Integer durationInMonths;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "memberships")
    @JsonManagedReference
    private List<User> users;

    @JsonManagedReference
    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<Payment> payments;



}

