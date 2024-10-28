package test.project4v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<Object> handleCustomException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<Object> handleGeneralException(java.lang.Exception ex) {
        return new ResponseEntity<>("An error occurred" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
