package com.obamax.AttendanceRegistry.exceptions;

public class BadRequestException  extends RuntimeException {

    private String message;

    public BadRequestException() {
        super();
        this.message = "Bad request";
    }
    public BadRequestException(String msg) {
        super(msg);
        this.message = msg;
    }
}
