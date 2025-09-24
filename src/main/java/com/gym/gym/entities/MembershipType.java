package com.gym.gym.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name="membership_types")
public class MembershipType
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name="name")
        private String name;

        @Column(name="start_time")
        private LocalTime startTime;

        @Column(name="end_time")
        private LocalTime endTime;

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

        public LocalTime getStartTime()
        {
                return startTime;
        }

        public void setStartTime(LocalTime startTime)
        {
                this.startTime = startTime;
        }

        public LocalTime getEndTime()
        {
                return endTime;
        }

        public void setEndTime(LocalTime endTime)
        {
                this.endTime = endTime;
        }
}
