package me.lucaspmntl.geofreight.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record ExceptionDTO(String message, int status, LocalDateTime timestamp) {
}
