package com.beastmode.dto.request;

import com.beastmode.Role;

public record CreateUserDto(String email, String name, String password, Role role ) {
}
