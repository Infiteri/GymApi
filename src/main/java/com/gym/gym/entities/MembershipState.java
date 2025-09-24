package com.gym.gym.entities;

public class MembershipState
{
        public static String GetInGym() {return "IN_GYM";};
        public static String GetNotInGym() {return "NOT_IN_GYM";};

        public static String GetState(boolean isInGym) {return isInGym ? GetInGym() : GetNotInGym();}
}
