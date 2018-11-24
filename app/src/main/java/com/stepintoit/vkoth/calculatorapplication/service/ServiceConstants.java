package com.stepintoit.vkoth.calculatorapplication.service;

public class ServiceConstants {

    public interface ACTION {
        public static String MAIN_ACTION = "com.stepintoit.vkoth.calculatorapplication.service.action.main";
        public static String PREV_ACTION = "com.stepintoit.vkoth.calculatorapplication.service.action.prev";
        public static String PLAY_ACTION = "com.stepintoit.vkoth.calculatorapplication.service.action.play";
        public static String NEXT_ACTION = "com.stepintoit.vkoth.calculatorapplication.service.action.next";
        public static String STARTFOREGROUND_ACTION = "com.stepintoit.vkoth.calculatorapplication.service.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.stepintoit.vkoth.calculatorapplication.service.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}
