package com.beastmode.mappers;

import com.beastmode.models.Role;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(String userId, String email, String name, String password, Role role) {
        return User.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .password(password)
                .role(role)
                .build();
    }
}
