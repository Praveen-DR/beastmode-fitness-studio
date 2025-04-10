package com.beastmode.services.membership_service;

import com.beastmode.models.Membership;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MembershipService {

    String createMembership(String membershipName, double price, Integer durationInMonths, boolean isActive);

    String updateMembership(String membershipId, String membershipName, double price, Integer durationInMonths, boolean isActive);

    String deleteMembership(String membershipId);

    List<Membership> getAllMembership();

    Membership getMembershipById(String membershipId);

    Membership getMembershipByUserId(String userId);

}
