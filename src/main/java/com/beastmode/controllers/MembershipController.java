package com.beastmode.controllers;

import com.beastmode.dto.request.CreateMembershipDto;
import com.beastmode.dto.request.UpdateMembershipDto;
import com.beastmode.services.membership_service.MembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/api/user")
public class MembershipController {
    private final MembershipService membershipService;

    MembershipController(MembershipService membershipService){
        this.membershipService = membershipService;
    }

    @PostMapping("/v1/createMembership")
    ResponseEntity<String> createMembership(@RequestBody CreateMembershipDto data){
        String message = membershipService.createMembership(data.membershipName(), data.price(), data.durationInMonths(), data.isActive());
        return ResponseEntity.status(201).body(message);
    }

    @PostMapping("/v1/updateMembership")
    ResponseEntity<String> updateMembership(@RequestBody UpdateMembershipDto data){
        String message = membershipService.updateMembership(data.membershipId(), data.membershipName(), data.price(), data.durationInMonths(), data.isActive());
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("/v1/deleteMembership")
    ResponseEntity<String> deleteMembership(@)






}
