package com.gym.gym.entities;

import jakarta.persistence.*;

@Entity
@Table(name="membership_types")
public class MembershipTypes
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name="name")
        private String name;

        public Integer getId()
        {
                return id;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }
}
