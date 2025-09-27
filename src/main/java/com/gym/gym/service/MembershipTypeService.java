package com.gym.gym.service;

import com.gym.gym.entities.MembershipType;
import com.gym.gym.repositories.MembershipRepository;
import com.gym.gym.repositories.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MembershipTypeService
{
        private static final Logger LOG = Logger.getLogger(MembershipTypeService.class.getName());

        @Autowired
        private MembershipTypeRepository repo;

        public  Optional<MembershipType> GetTypeFromName(String name)
        {
                return repo.findByName(name);
        }
}
