package org.example.exception;

public class InvalidRequestPayloadException extends RuntimeException{

    public InvalidRequestPayloadException(String message) {
        super(message);
    }
}
