package com.beastmode.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;

    @OneToMany(mappedBy = "trainer")
    private List<User> users;

}
