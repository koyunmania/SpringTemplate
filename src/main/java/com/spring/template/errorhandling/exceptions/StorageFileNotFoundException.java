package com.spring.template.errorhandling.exceptions;

public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = -524425622382212689L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
