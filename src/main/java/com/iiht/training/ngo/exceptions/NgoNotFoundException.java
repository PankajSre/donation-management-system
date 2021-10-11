package com.iiht.training.ngo.exceptions;

public class NgoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NgoNotFoundException() {
		super();
	}

	public NgoNotFoundException(String message) {
		super(message);
	}

}
