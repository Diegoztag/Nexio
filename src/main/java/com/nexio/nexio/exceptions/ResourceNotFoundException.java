package com.nexio.nexio.exceptions;

// Para datos no encontrados
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
