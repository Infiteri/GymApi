package com.gym.gym.controllers;

import com.gym.gym.entities.Membership;
import com.gym.gym.entities.MembershipState;
import com.gym.gym.entities.MembershipType;
import com.gym.gym.service.MembershipService;
import com.gym.gym.service.UserService;
import com.gym.gym.utils.TimeCheckerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/membership")
public class MembershipController
{
        private static final Logger LOG = Logger.getLogger(MembershipController.class.getName());

        @Autowired
        private MembershipService service;
        @Autowired
        private UserService userService;

        // GET
        @GetMapping("/id")
        public ResponseEntity<Membership> GetMembershipById(@RequestParam int id)
        {
                var mem = service.GetMembershipById(id);
                if (mem.isEmpty())
                        return ResponseEntity.notFound().build();
                else
                        return ResponseEntity.ok().body(mem.get());
        }

        @GetMapping("/phoneNumber")
        public ResponseEntity<Membership> GetMembershipByUserPhoneNumber(@RequestParam String phoneNumber)
        {
                var mem = service.GetMembershipByUserPhoneNumber(phoneNumber);
                if (mem.isEmpty())
                        return ResponseEntity.notFound().build();
                else
                        return ResponseEntity.ok().body(mem.get());
        }


        // POST
        @PostMapping("/create")
        public ResponseEntity<Membership> CreateMembership(@RequestBody Membership membership)
        {
                var mem = service.CreateMembership(membership.getUser().getId(), membership);

                if (mem == null) return ResponseEntity.notFound().build();
                return ResponseEntity.ok().body(mem);
        }

        @PostMapping("/enter")
        public ResponseEntity<Membership> Enter(@RequestParam String phoneNumber)
        {
                var user = userService.GetUserByPhoneNumber(phoneNumber);

                if (user.isEmpty()) return ResponseEntity.notFound().build();

                var membership = service.GetMembershipByUserId(user.get().getId());

                if (membership.isEmpty()) return ResponseEntity.notFound().build();

                if (membership.get().getState().equals(MembershipState.GetInGym()))
                {
                        // todo: Add some json to describe the issue so can display in whatever front end its using
                        LOG.severe("Membership is already in gym");
                        // todo: Reanable
                        return ResponseEntity.badRequest().build();
                }


                // todo: Check time to make the membership is payed
                if (!TimeCheckerUtils.CheckIfMembershipIsPayed(membership.get()))
                {
                        LOG.severe("Membership is not payed");
                        return ResponseEntity.badRequest().build();
                }

                // todo: Check time to make sure membership type allows it

                if (!TimeCheckerUtils.CheckTimeOnMembershipType(membership.get().getType()))
                {
                        LOG.severe("Time doesn't allow");
                        return ResponseEntity.badRequest().build();
                }

                var stateMembership = service.UpdateMembershipState(membership.get().getId(), MembershipState.GetState(true));

                return ResponseEntity.ok().body(stateMembership);
        }

        @PostMapping("/exit")
        public ResponseEntity<Membership> Exit(@RequestParam String phoneNumber) {
                var user = userService.GetUserByPhoneNumber(phoneNumber);
                if (user.isEmpty()) return ResponseEntity.notFound().build();
                var membership = service.GetMembershipByUserId(user.get().getId());
                if (membership.isEmpty()) return ResponseEntity.notFound().build();

                if (membership.get().getState().equals(MembershipState.GetNotInGym()))
                {
                        LOG.severe("Membership isn't already in gym");
                        return  ResponseEntity.badRequest().build();
                }

                var stateMembership = service.UpdateMembershipState(membership.get().getId(), MembershipState.GetState(false));

                return ResponseEntity.ok().body(stateMembership);
        }



        // PUT
        @PutMapping("/update/{id}")
        public ResponseEntity<Membership> UpdateMembershipById(@PathVariable int id, @RequestBody Membership membership)
        {
                LOG.info("Updating membership by id: " + id);
                var mem = service.UpdateMembershipById(id, membership);
                if (mem == null) return ResponseEntity.notFound().build();
                return ResponseEntity.ok().body(mem);
        }

        // PATCH
        @PatchMapping("/pay/{id}")
        public ResponseEntity<Membership> UpdateMembershipLastPayedById(@PathVariable int id, @RequestBody Membership membership)
        {
                LOG.info("Updating membership payment by id: " + id);

                if (membership.getLastPayed() == null) membership.setLastPayed(LocalDateTime.now());

                var mem = service.UpdateMembershipLastPayedById(id, membership.getLastPayed());
                if (mem == null) return ResponseEntity.notFound().build();
                return ResponseEntity.ok().body(mem);
        }

        // DELETE
        @DeleteMapping
        public ResponseEntity<Membership> DeleteMembershipById(@RequestParam int id)
        {
                LOG.info("Deleting membership by id: " + id);
                service.DeleteMembershipById(id);
                return ResponseEntity.ok().build();
        }
}
