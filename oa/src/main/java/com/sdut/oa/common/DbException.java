package com.sdut.oa.common;

public class DbException extends Exception {
	private static final long serialVersionUID = 8377681313821734901L;

	public DbException() {
		super();
	}

	public DbException(String errorMsg) {
		super(errorMsg);
	}

	public DbException(Throwable throwable) {
		super(throwable.getMessage(), throwable);
	}

	public DbException(String errorMsg, Throwable throwable) {
		super(errorMsg + ";" + throwable.getMessage(), throwable);
	}
}
