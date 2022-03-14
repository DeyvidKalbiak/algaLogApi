package com.algalog.api.domain.exception;

/**
 * Tratamento exceptions das entidades
 * 
 * @author dkalbiak
 *
 */
public class EntidadeNaoEncontradaException extends NovaException {

	private static final long serialVersionUID = -2323581066021414023L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}

}
