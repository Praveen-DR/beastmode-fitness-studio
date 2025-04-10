package com.beastmode.repositories;

import com.beastmode.models.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, String> {

    List<Membership> findAllByMembershipIdAndIsActive(String membershipId, boolean isActive);

    Optional<Membership> findByUser_UserId(String userId);


}
