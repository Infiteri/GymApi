package com.gym.gym.repositories;

import com.gym.gym.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
}
