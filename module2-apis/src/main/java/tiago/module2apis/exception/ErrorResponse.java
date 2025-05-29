package tiago.module2apis.exception;

import java.time.LocalDateTime;

public record ErrorResponse(String message, int code, LocalDateTime timestamp) {
}
