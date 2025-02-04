package com.beastmode.dto.request;

public record UpdateMembershipDto(String membershipId, String membershipName, Double price, String durationInMonths, boolean isActive) {
}
