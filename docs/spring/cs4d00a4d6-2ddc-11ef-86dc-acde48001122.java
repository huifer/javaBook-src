package org.example.domain.event.exception;

public class EventException extends RuntimeException {

	private final Error error;


	protected EventException(Error error) {
		super(error.msg());
		this.error = error;
	}

}
