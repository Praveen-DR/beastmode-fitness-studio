package com.beastmode.mappers;

import com.beastmode.models.Membership;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

@Service
public class MembershipMapper {
    public Membership toMembership(String membershipId, String membershipName, double price, Integer durationInMonths, boolean isActive) {
        return Membership.builder()
                .membershipId(membershipId)
                .membershipName(membershipName)
                .price(price)
                .durationInMonths(durationInMonths)
                .isActive(isActive)
                .build();
    }
}

