package com.gym.gym.repositories;

import com.gym.gym.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Integer>
{
        void deleteByUserId(int userId);
        Optional<Membership> findByUserId(int userId);
}
