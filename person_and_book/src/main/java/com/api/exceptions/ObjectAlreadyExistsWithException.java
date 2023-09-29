package com.api.exceptions;

public class ObjectAlreadyExistsWithException extends RuntimeException {

    public ObjectAlreadyExistsWithException(String objectName, String attributeName) {
        super(String.format("%s already exists with attribute %s", objectName, attributeName));
    }
}
