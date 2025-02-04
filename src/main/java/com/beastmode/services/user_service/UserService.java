package com.beastmode.services.user_service;

import com.beastmode.Role;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String createUser(String email, String name, String password, Role role);

    String updateUser(String userId, String email, String name, String password, Role role);

    String deleteUser(String userId);

    List<User> getAllUser();

    User getUserById(String userId);

    List<User> getAllByRole(Role role);
}
