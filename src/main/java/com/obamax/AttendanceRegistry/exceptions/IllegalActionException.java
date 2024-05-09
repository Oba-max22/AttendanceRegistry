package com.obamax.AttendanceRegistry.exceptions;

public class IllegalActionException extends RuntimeException {

    private String message;

    public IllegalActionException() {
        super();
        this.message = "Illegal action";
    }
    public IllegalActionException(String msg) {
        super(msg);
        this.message = msg;
    }
}

