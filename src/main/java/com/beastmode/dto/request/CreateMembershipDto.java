package com.beastmode.dto.request;

public record CreateMembershipDto(String membershipName, Double price, String durationInMonths, boolean isActive) {
}
