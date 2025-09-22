package com.gym.gym.controllers;

import com.gym.gym.entities.User;
import com.gym.gym.service.MembershipService;
import com.gym.gym.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        @GetMapping
        public ResponseEntity<User> GetUserById(@RequestParam int userId)
        {
                LOG.info("Getting user by id: " + userId);
                var user = service.GetUserById(userId);
                if (user.isEmpty())
                        return ResponseEntity.notFound().build();
                else
                        return ResponseEntity.ok().body(user.get());
        }

        // POST

        // PUT

        // DELETE
        @DeleteMapping
        public ResponseEntity<User> DeleteUserById(@RequestParam int userId)
        {
                LOG.info("Deleting user by id: " + userId);

                var membership = membershipService.GetMembershipByUserId(userId);
                if (!membership.isEmpty())
                        membershipService.DeleteMembershipById(membership.get().getId());

                service.DeleteUserById(userId);
                return ResponseEntity.ok().build();
        }
}
