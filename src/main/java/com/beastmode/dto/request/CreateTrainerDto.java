package com.beastmode.dto.request;

public record CreateTrainerDto(String firstName, String lastName, String email, String phoneNumber, String specialization, int experience, boolean isActive, String password) {
}
