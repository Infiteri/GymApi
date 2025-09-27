package com.gym.gym.controllers;

import com.gym.gym.entities.User;
import com.gym.gym.service.MembershipService;
import com.gym.gym.service.UserService;
import org.apache.coyote.Response;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/user")
public class UserController
{
        private static final Logger LOG = Logger.getLogger(UserController.class.getName());

        @Autowired
        private UserService service;


        @Autowired
        private MembershipService membershipService;

        // GET
        @GetMapping("/id")
        public ResponseEntity<User> GetUserById(@RequestParam int userId)
        {
                LOG.info("Getting user by id: " + userId);
                var user = service.GetUserById(userId);
                if (user.isEmpty())
                        return ResponseEntity.notFound().build();
                else
                        return ResponseEntity.ok().body(user.get());
        }

        @GetMapping("/all")
        public ResponseEntity<List<User>> GetAllUsers()
        {
                // note: This is VERY BAD DON'T CALL JUST FOR FUNNY API
                return ResponseEntity.ok().body(service.GetAllUsers());
        }

        @GetMapping("/phoneNumber")
        public ResponseEntity<User> GetUserByPhoneNumber(@RequestParam String phoneNumber)
        {
                LOG.info("Getting user by phone number: " + phoneNumber);
                var user = service.GetUserByPhoneNumber(phoneNumber);
                if (user.isEmpty())
                        return ResponseEntity.notFound().build();
                else
                        return ResponseEntity.ok().body(user.get());
        }

        // POST
        @PostMapping("/create")
        public ResponseEntity<User> CreateUser(@RequestBody User user)
        {
                LOG.info("Creating user");

                service.CreateUser(user.getName(), user.getBirthday(), user.getPhoneNumber());
                return ResponseEntity.ok().build();
        }

        // PUT

        // DELETE
        @DeleteMapping("/id")
        public ResponseEntity<User> DeleteUserById(@RequestParam int userId)
        {
                LOG.info("Deleting user by id: " + userId);

                var membership = membershipService.GetMembershipByUserId(userId);
                if (!membership.isEmpty())
                        membershipService.DeleteMembershipById(membership.get().getId());

                service.DeleteUserById(userId);
                return ResponseEntity.ok().build();
        }

        @DeleteMapping("/phoneNumber")
        public ResponseEntity<User> DeleteUserByPhoneNumber(@RequestParam String phoneNumber)
        {
                var user = service.GetUserByPhoneNumber(phoneNumber);
                if(user.isEmpty()) {
                        return ResponseEntity.notFound().build();
                }

                DeleteUserById(user.get().getId());
                return ResponseEntity.ok().build();
        }
}
