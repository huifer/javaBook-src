package org.example.domain.event.exception;

public enum ErrorEnums implements Error {
	;

	private final int code;

	private final String msg;

	ErrorEnums(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String msg() {
		return msg;
	}
}
