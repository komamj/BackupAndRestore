package com.koma.vcalendarlibrary;

/**
 * Exception thrown when error happens in handling iCalendar components.
 */
public class VCalendarException extends Exception {
    public static final int FORMAT_EXCEPTION = 0;
    public static final int NO_ACCOUNT_EXCEPTION = 1;
    public static final int NO_EVENT_EXCEPTION = 2;
    public static final int FILE_READ_EXCEPTION = 3;
    private static final long serialVersionUID = 1L;

    public VCalendarException() {
        super();
    }

    public VCalendarException(String msg) {
        super(msg);
    }

    public VCalendarException(String msg, Throwable cause) {
        super(msg, cause);
    }
}