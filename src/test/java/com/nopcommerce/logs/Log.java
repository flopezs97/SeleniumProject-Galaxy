package com.nopcommerce.logs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log {
    private static final Logger Log = (Logger) LogManager.getLogger(Log.class);

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.info(message);
    }

    public static void error(String message) {
        Log.info(message);
    }

    public static void fatal(String message) {
        Log.info(message);
    }

    public static void debug(String message) {
        Log.info(message);
    }
    
}

