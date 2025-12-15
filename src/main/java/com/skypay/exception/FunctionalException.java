package com.skypay.exception;

/**
 * Exception de base pour toutes les erreurs fonctionnelles du projet.
 * Étend RuntimeException pour ne pas forcer la gestion explicite.
 */
public class FunctionalException extends RuntimeException {

    private final String errorCode;

    public FunctionalException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public FunctionalException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
