package com.gym.gym.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="memberships")
public class Membership
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name="type")
        private MembershipTypes type;

        @Column(name="last_payed")
        private LocalDateTime lastPayed;

        @Column(name="created_at")
        private LocalDateTime createdAt;

        @OneToOne
        @JoinColumn(name="user_id")
        private User user;

        public Integer getId()
        {
                return id;
        }

        public MembershipTypes getType()
        {
                return type;
        }

        public void setType(MembershipTypes type)
        {
                this.type = type;
        }

        public LocalDateTime getLastPayed()
        {
                return lastPayed;
        }

        public void setLastPayed(LocalDateTime lastPayed)
        {
                this.lastPayed = lastPayed;
        }

        public LocalDateTime getCreatedAt()
        {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt)
        {
                this.createdAt = createdAt;
        }

        public User getUser()
        {
                return user;
        }

        public void setUser(User user)
        {
                this.user = user;
        }
}
