package org.example.project4.WebPacket.Domain.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class Exception extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;
    private final int status;

    public Exception(String message, int status) {
        super(message);
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public int getStatus() {
        return status;
    }
}
