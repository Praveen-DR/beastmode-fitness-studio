package com.beastmode.dto.request;

public record UpdateTrainerDto(String trainerId, String firstName, String lastName, String email, String phoneNumber, String specialization, int experience, boolean isActive) {
}
