package com.skypay.exception;

/**
 * Exception levée lorsqu'une opération sur un compte est invalide.
 */
public class InvalidOperationException extends FunctionalException {

    private static final String ERROR_CODE = "INVALID_OPERATION";

    public InvalidOperationException(String message) {
        super(message, ERROR_CODE);
    }

    /**
     * Exception pour un compte bloqué.
     */
    public static InvalidOperationException accountBlocked() {
        return new InvalidOperationException("Opération impossible: le compte est bloqué.");
    }

    /**
     * Exception pour un compte fermé.
     */
    public static InvalidOperationException accountClosed() {
        return new InvalidOperationException("Opération impossible: le compte est fermé.");
    }

    /**
     * Exception pour une limite de transaction dépassée.
     */
    public static InvalidOperationException transactionLimitExceeded(int limit, int requested) {
        return new InvalidOperationException(
            String.format("Limite de transaction dépassée. Maximum: %d€, demandé: %d€", limit, requested)
        );
    }
}
