package com.blacknebula.mousify.server.utils;


import com.esotericsoftware.minlog.Log;

import java.util.Optional;


public class Logger {

    final static private String NAME = "com.blacknebula.mousify";

    public static void debug(Type logType, String message, Object... args) {
        log(Level.DEBUG, logType, Optional.<Throwable>empty(), message, args);
    }

    public static void warn(Type logType, String message, Object... args) {
        log(Level.WARN, logType, Optional.<Throwable>empty(), message, args);
    }

    public static void info(Type logType, String message, Object... args) {
        log(Level.INFO, logType, Optional.<Throwable>empty(), message, args);
    }

    public static void error(Type logType, Throwable ex) {
        log(Level.ERROR, logType, Optional.of(ex), "");
    }

    public static void error(Type logType, String message, Object... args) {
        log(Level.ERROR, logType, Optional.<Throwable>empty(), message, args);
    }

    public static void error(Type logType, Throwable ex, String message, Object... args) {
        log(Level.ERROR, logType, Optional.of(ex), message, args);
    }

    private static void log(Level level, Type logType, Optional<Throwable> ex, String message, Object... args) {
        try {
            final String formattedMessage = formatMessage(String.format("%s ", logType.toString()) + message, args);
            switch (level) {
                case DEBUG:
                    if (Log.DEBUG)
                        Log.debug(NAME, formattedMessage);
                    break;
                case INFO:
                    if (Log.INFO)
                        Log.info(NAME, formattedMessage);
                    break;
                case WARN:
                    if (Log.WARN)
                        Log.warn(NAME, formattedMessage);
                    break;
                case ERROR:
                    if (ex.isPresent())
                        Log.error(NAME, formattedMessage, ex.get());
                    else
                        Log.error(NAME, formattedMessage);
                    break;
            }
        } catch (Exception e) {
            Log.error(NAME, "LOG exception while logging data", e);
        }
    }

    private static String formatMessage(String message, Object... args) {
        try {
            return String.format(message, args);
        } catch (Exception ex) {
            Log.error(NAME, "LOG error on formatting the message=" + message, ex);
            return "";
        }
    }

    public enum Type {
        MOUSIFY, KRYONET;

        @Override
        public String toString() {
            return String.format("[%s]", super.toString());    //To change body of overridden methods use File | Settings | File Templates.
        }

    }

    private enum Level {
        DEBUG, INFO, WARN, ERROR
    }
}