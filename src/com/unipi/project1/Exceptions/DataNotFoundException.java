package com.unipi.project1.Exceptions;

// Eccezione checked per indicare il fallimento nella ricerca di un elemento
public class DataNotFoundException extends Exception {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
