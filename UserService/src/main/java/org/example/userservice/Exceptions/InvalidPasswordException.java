package org.example.userservice.Exceptions;

public class InvalidPasswordException extends Exception {
    public  InvalidPasswordException(String message)
    {
        super(message);
    }
}