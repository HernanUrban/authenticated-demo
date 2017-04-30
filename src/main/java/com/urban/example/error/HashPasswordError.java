package com.urban.example.error;

/**
 * Created by hurban
 */
public class HashPasswordError extends RuntimeException{
    public HashPasswordError() {
    }

    public HashPasswordError(String message) {
        super(message);
    }

    public HashPasswordError(String message, Throwable cause) {
        super(message, cause);
    }

    public HashPasswordError(Throwable cause) {
        super(cause);
    }

    public HashPasswordError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
