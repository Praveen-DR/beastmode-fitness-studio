package com.beastmode.dto.request;

import com.beastmode.models.Membership;
import com.beastmode.models.Role;

public record CreateUserDto(String email, String name, String password, Role role, String phoneNo, String membershipId) {
}
