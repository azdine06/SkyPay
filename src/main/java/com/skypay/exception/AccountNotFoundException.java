package com.skypay.exception;

/**
 * Exception levée lorsqu'un compte n'est pas trouvé.
 */
public class AccountNotFoundException extends FunctionalException {

    private static final String ERROR_CODE = "ACCOUNT_NOT_FOUND";
    private static final String MESSAGE_TEMPLATE = "Compte non trouvé avec l'identifiant: %s";

    public AccountNotFoundException(String accountId) {
        super(String.format(MESSAGE_TEMPLATE, accountId), ERROR_CODE);
    }

    public AccountNotFoundException(Long accountId) {
        super(String.format(MESSAGE_TEMPLATE, accountId.toString()), ERROR_CODE);
    }
}
