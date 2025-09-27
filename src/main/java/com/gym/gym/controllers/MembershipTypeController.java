package com.gym.gym.controllers;


import com.gym.gym.entities.MembershipType;
import com.gym.gym.service.MembershipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/membership_type")
public class MembershipTypeController
{
        @Autowired
        private MembershipTypeService service;

        // GET
        @GetMapping("/name")
        public ResponseEntity<MembershipType> GetMembershipByName(@RequestParam String name) {
                var mem = service.GetTypeFromName(name);

                if(mem.isEmpty())
                        return ResponseEntity.notFound().build();

                return ResponseEntity.ok().body(mem.get());
        }
}
