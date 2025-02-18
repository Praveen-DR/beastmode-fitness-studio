package com.beastmode.dto.request;

public record UpdateMembershipDto(String membershipId, String membershipName, double price, Integer durationInMonths, boolean isActive) {
}
