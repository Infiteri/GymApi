package com.gym.gym.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
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
        private LocalDate birthday;

        @Column(name="phone_number")
        private String phoneNumber;

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

        public LocalDate getBirthday()
        {
                return birthday;
        }

        public void setBirthday(LocalDate birthday)
        {
                this.birthday = birthday;
        }

        public String getPhoneNumber()
        {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber)
        {
                this.phoneNumber = phoneNumber;
        }
}
