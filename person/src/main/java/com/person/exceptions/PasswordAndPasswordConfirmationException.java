package com.person.exceptions;

public class PasswordAndPasswordConfirmationException extends RuntimeException {

    public PasswordAndPasswordConfirmationException() {
        super("password and password confirmation is not equals");
    }
}
