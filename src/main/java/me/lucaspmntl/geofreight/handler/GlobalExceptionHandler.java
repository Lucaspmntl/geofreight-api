package me.lucaspmntl.geofreight.handler;

import me.lucaspmntl.geofreight.dto.ExceptionDTO;
import me.lucaspmntl.geofreight.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NonAmapaAddresException.class)
    public ResponseEntity<ExceptionDTO> handleResourceNotFound(NonAmapaAddresException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionDTO);
    }

    @ExceptionHandler(ExternalIntegrationException.class)
    public ResponseEntity<ExceptionDTO> handleExternalIntegration(ExternalIntegrationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionDTO);
    }

    @ExceptionHandler(AmapaToAmapaException.class)
    public ResponseEntity<ExceptionDTO> handleAmapaToAmapaException(AmapaToAmapaException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionDTO);
    }

}
