package com.gym.gym.repositories;

import com.gym.gym.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
        Optional<User> findByPhoneNumber(String phoneNumber);

        void deleteByPhoneNumber(String phoneNumber);
}
