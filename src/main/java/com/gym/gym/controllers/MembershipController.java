package com.gym.gym.controllers;

import com.gym.gym.entities.Membership;
import com.gym.gym.service.MembershipService;
import com.gym.gym.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/membership")
public class MembershipController
{
        private static final Logger LOG = Logger.getLogger(MembershipController.class.getName());

        @Autowired
        private MembershipService service;

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

                if(membership.getLastPayed() == null) membership.setLastPayed(LocalDateTime.now());

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
