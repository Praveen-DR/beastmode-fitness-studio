package com.beastmode.dto.request;

public record CreateMembershipDto(String membershipName, Double price, Integer durationInMonths, boolean isActive) {
}
