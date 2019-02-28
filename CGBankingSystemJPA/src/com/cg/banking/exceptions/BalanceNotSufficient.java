package com.cg.banking.exceptions;

public class BalanceNotSufficient extends Exception{
	public BalanceNotSufficient() {
	}

	public BalanceNotSufficient(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BalanceNotSufficient(String message, Throwable cause) {
		super(message, cause);
	}

	public BalanceNotSufficient(String message) {
		super(message);
	}

	public BalanceNotSufficient(Throwable cause) {
		super(cause);
	}
}
