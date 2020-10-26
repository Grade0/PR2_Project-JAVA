package com.unipi.project1.Exceptions;

// Eccezione checked per indicare che la password che si e inserita e errata
public class UnauthorizedLoginException extends Exception {
    public UnauthorizedLoginException(String msg) {
        super(msg);
    }
}
