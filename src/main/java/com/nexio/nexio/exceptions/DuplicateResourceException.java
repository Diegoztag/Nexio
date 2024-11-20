package com.nexio.nexio.exceptions;

// Para recursos duplicados
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
