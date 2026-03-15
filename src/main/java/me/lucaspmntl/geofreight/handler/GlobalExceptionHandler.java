package me.lucaspmntl.geofreight.handler;

import me.lucaspmntl.geofreight.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NonAmapaAddresException.class)
    public ResponseEntity<String> handleResourceNotFound(NonAmapaAddresException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ExternalIntegrationException.class)
    public ResponseEntity<String> handleExternalIntegration(ExternalIntegrationException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(AmapaToAmapaException.class)
    public ResponseEntity<String> handleAmapaToAmapaException(AmapaToAmapaException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

}
