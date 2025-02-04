package com.beastmode.services.membership_service;

import org.springframework.stereotype.Service;

@Service
public interface MembershipService {

    String createMembership(String membershipName, Double price, String durationInMonths, boolean isActive);

    String updateMembership(String membershipId, String membershipName, Double price, String durationInMonths, boolean isActive);

    String deleteMembership(String membershipId);

}
