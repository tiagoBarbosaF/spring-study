package tiago.modulefundamentalschallenge.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(String message, int code, LocalDateTime timestamp) {
}
