package com.beastmode.repositories;

import com.beastmode.Role;
import com.beastmode.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    List<User> findAllByRole(Role role);

}
