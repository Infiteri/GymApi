package com.gym.gym.utils;

import com.gym.gym.entities.Membership;
import com.gym.gym.entities.MembershipType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeCheckerUtils
{
        private static boolean CheckIsTimeInZone(LocalTime time, LocalTime leftBound, LocalTime rightBound) {
                // Normal case: leftBound <= rightBound (e.g., 07:00 to 23:00)
                if (!leftBound.isAfter(rightBound)) {
                        return !time.isBefore(leftBound) && !time.isAfter(rightBound);
                }
                // Overnight case: leftBound > rightBound (e.g., 22:00 to 06:00)
                else {
                        return !time.isBefore(leftBound) || !time.isAfter(rightBound);
                }
        }

        public static boolean CheckTimeOnMembershipType(MembershipType membershipType)
        {
                var leftTime = membershipType.getStartTime();
                var rightTime = membershipType.getEndTime();

                return CheckIsTimeInZone(LocalTime.now(), leftTime, rightTime);
        }

        public static boolean CheckIfMembershipIsPayed(Membership membership)
        {
                // todo: Add right number of months based on membership type
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime validUntil = membership.getLastPayed().plusMonths(1);

                return now.isBefore(validUntil);
        }
}
