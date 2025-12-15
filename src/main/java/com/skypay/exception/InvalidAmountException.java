package com.skypay.exception;

/**
 * Exception levée lorsqu'un montant invalide est fourni (zéro ou négatif).
 */
public class InvalidAmountException extends FunctionalException {

    private static final String ERROR_CODE = "INVALID_AMOUNT";

    public InvalidAmountException(int amount) {
        super(buildMessage(amount), ERROR_CODE);
    }

    private static String buildMessage(int amount) {
        if (amount == 0) {
            return "Le montant ne peut pas être zéro.";
        } else if (amount < 0) {
            return String.format("Le montant ne peut pas être négatif: %d€", amount);
        }
        return "Montant invalide: " + amount;
    }
}
