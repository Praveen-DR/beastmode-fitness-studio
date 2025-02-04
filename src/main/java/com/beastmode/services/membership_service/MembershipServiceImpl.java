package com.beastmode.services.membership_service;

import com.beastmode.exceptions.ApiRequestException;
import com.beastmode.mappers.MembershipMapper;
import com.beastmode.models.Membership;
import com.beastmode.models.User;
import com.beastmode.repositories.MembershipRepository;
import com.beastmode.services.user_service.UserService;
import com.beastmode.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService{
    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;
    private final UUIDUtil uuidUtil;

    MembershipServiceImpl(MembershipRepository membershipRepository, MembershipMapper membershipMapper, UUIDUtil uuidUtil){
        this.membershipRepository = membershipRepository;
        this.membershipMapper = membershipMapper;
        this.uuidUtil = uuidUtil;
    }

    @Override
    public String createMembership(String membershipName, Double price, String durationInMonths, boolean isActive) {
        List<Membership> activeMembership = membershipRepository.findAllByMembershipIdAndIsActive(uuidUtil.generateUuid(), isActive);
        for (Membership data: activeMembership){
            data.setActive(isActive);
            membershipRepository.save(data);
        }
        Membership membership = membershipMapper.toMembership(uuidUtil.generateUuid(), membershipName, price, durationInMonths, isActive);
        membershipRepository.save(membership);
        return "Membership Created Successfully";
    }

    @Override
    public String updateMembership(String membershipId, String membershipName, Double price, String durationInMonths, boolean isActive) {
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(()->new ApiRequestException("Membership not found"));
        membership.setMembershipId(membershipId);
        membership.setMembershipName(membershipName);
        membership.setPrice(price);
        membership.setDurationInMonths(durationInMonths);
        membership.setActive(isActive);
        return "Membership updated";
    }

    @Override
    public String deleteMembership(String membershipId) {
        if(!membershipRepository.existsById(membershipId)){
            throw new ApiRequestException("Membership ID not found");
        }
        membershipRepository.deleteById(membershipId);
        return "Membership deleted successfully";
    }




}
