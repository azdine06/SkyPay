package com.skypay.exception;

/**
 * Exception levée lorsque le solde du compte est insuffisant pour un retrait.
 */
public class InsufficientBalanceException extends FunctionalException {

    private static final String ERROR_CODE = "INSUFFICIENT_BALANCE";
    private static final String MESSAGE_TEMPLATE = "Solde insuffisant. Solde actuel: %d€, montant demandé: %d€";

    public InsufficientBalanceException(int currentBalance, int requestedAmount) {
        super(String.format(MESSAGE_TEMPLATE, currentBalance, requestedAmount), ERROR_CODE);
    }
}
