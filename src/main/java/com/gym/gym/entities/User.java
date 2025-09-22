package com.gym.gym.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class User
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name="name")
        private String name;

        @Column(name="birthday")
        private LocalDateTime birthday;

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

        public LocalDateTime getBirthday()
        {
                return birthday;
        }

        public void setBirthday(LocalDateTime birthday)
        {
                this.birthday = birthday;
        }
}
