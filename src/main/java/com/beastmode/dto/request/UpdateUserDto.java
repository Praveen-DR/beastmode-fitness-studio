package com.beastmode.dto.request;

import com.beastmode.Role;

public record UpdateUserDto(String userId, String email, String name, String password, Role role){
}
