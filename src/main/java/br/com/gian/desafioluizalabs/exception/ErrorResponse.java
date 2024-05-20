package br.com.gian.desafioluizalabs.exception;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
}
