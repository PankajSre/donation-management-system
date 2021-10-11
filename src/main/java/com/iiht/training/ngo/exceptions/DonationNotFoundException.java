package com.iiht.training.ngo.exceptions;

public class DonationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DonationNotFoundException() {
		super();
	}

	public DonationNotFoundException(String message) {
		super(message);
	}

}
