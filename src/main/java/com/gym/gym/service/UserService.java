package com.gym.gym.service;

import com.gym.gym.entities.User;
import com.gym.gym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
                return repo.findById(userId);
        }

        public Optional<User> GetUserByPhoneNumber(String phoneNumber)
        {
                return repo.findByPhoneNumber(phoneNumber);
        }

        public List<User> GetAllUsers()
        {
                return repo.findAll();
        }

        public void DeleteUserById(int userId)
        {
                LOG.info("Deleting user by id: " + userId);
                repo.deleteById(userId);
        }

        public User CreateUser(String name, LocalDate birthday, String phoneNumber)
        {
                LOG.info("Creating user");
                User user = new User();
                user.setName(name);
                user.setBirthday(birthday);
                user.setPhoneNumber(phoneNumber);
                return repo.save(user);
        }

}
