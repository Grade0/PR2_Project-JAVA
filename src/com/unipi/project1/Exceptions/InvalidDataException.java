package com.unipi.project1.Exceptions;

// Eccezione checked per indicare che formato del dato è invalido
public class InvalidDataException extends Exception {
    public InvalidDataException(String msg) {
        super(msg);
    }
}
