package com.spring.template.errorhandling.exceptions;

public class StorageException extends RuntimeException{

	private static final long serialVersionUID = 2043789006076733704L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }	
}
