package test.project4v3.exception;


import lombok.Getter;

import java.io.Serial;

@Getter
public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
    private final int status;

    public CustomException(String message, int status) {
        super(message);
        this.message = message;
        this.status = status;
    }

}
