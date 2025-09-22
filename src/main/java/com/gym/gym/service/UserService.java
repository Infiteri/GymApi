package com.gym.gym.service;

import com.gym.gym.entities.User;
import com.gym.gym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService
{
        private static final Logger LOG = Logger.getLogger(UserService.class.getName());

        @Autowired
        private UserRepository repo;

        public Optional<User> GetUserById(int userId)
        {
                LOG.info("Getting user by id: " + userId);
                return repo.findById(userId);
        }

        public void DeleteUserById(int userId)
        {
                LOG.info("Deleting user by id: " + userId);
                repo.deleteById(userId);
        }

}
