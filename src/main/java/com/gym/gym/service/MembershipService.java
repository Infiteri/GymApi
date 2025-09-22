package com.gym.gym.service;

import com.gym.gym.entities.Membership;
import com.gym.gym.entities.User;
import com.gym.gym.repositories.MembershipRepository;
import com.gym.gym.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.logging.Logger;

@Service
public class MembershipService
{
        private static final Logger LOG = Logger.getLogger(MembershipService.class.getName());

        @Autowired
        private MembershipRepository repo;

        @Autowired
        private UserRepository userRepository;

        public Optional<Membership> GetMembershipById(int id)
        {
                LOG.info("Getting membership by id: " + id);
                return repo.findById(id);
        }

        public Optional<Membership> GetMembershipByUserPhoneNumber(String phoneNumber)
        {
                var user = userRepository.findByPhoneNumber(phoneNumber);
                if (user.isEmpty()) return Optional.empty();

                var mem = repo.findByUserId(user.get().getId());
                if (mem.isEmpty()) return Optional.empty();
                return mem;
        }

        public Membership CreateMembership(int userId, Membership membership)
        {
                Optional<User> user = userRepository.findById(userId);

                if (user.isEmpty()) return null;

                Membership mem = new Membership();
                mem.setType(membership.getType());
                mem.setLastPayed(LocalDateTime.now());
                mem.setCreatedAt(LocalDateTime.now());
                mem.setUser(user.get());
                return repo.save(mem);
        }

        public void DeleteMembershipById(int id)
        {
                LOG.info("Deleting membership by id: " + id);
                repo.deleteById(id);
        }

        public Optional<Membership> GetMembershipByUserId(int id)
        {
                LOG.info("Getting membership by user id: " + id);
                return repo.findByUserId(id);
        }

        public Membership UpdateMembershipById(int id, Membership newMembership)
        {
                LOG.info("Updating membership by id: " + id);
                var mem = repo.findById(id);
                if (mem.isEmpty()) return null;

                Membership membership = mem.get();
                membership.setType(newMembership.getType());
                membership.setLastPayed(newMembership.getLastPayed());
                membership.setCreatedAt(newMembership.getCreatedAt());
                return repo.save(membership);
        }

        public Membership UpdateMembershipLastPayedById(int id, LocalDateTime newLastPayed)
        {
                LOG.info("Updating membership payment by id: " + id);
                var mem = repo.findById(id);
                if (mem.isEmpty()) return null;

                Membership membership = mem.get();
                membership.setLastPayed(newLastPayed);
                return repo.save(membership);

        }
}
