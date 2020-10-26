package com.unipi.project1.Exceptions;

// Eccezione checked per indicare che il dato che si sta cercando di inserire esiste già
public class DuplicateDataException extends Exception {
    public DuplicateDataException(String msg) {
        super(msg);
    }
}
