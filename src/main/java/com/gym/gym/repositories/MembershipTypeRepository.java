package com.gym.gym.repositories;

import com.gym.gym.entities.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipTypeRepository extends JpaRepository<MembershipType, Integer>
{
        Optional<MembershipType> findByName(String name);
}
