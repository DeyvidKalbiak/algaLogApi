package com.algalog.api.domain.exception;

/**
 * Tratamentos gerais excpetions
 * 
 * @author dkalbiak
 *
 */
public class NovaException extends RuntimeException {

	public NovaException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// public static final String EMAIL_EXISTENG = "E-mail j\u00E1 cadastrado, entre
	// com um novo e-mail!";

}
