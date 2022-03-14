package com.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe tratamento de erros
 * 
 * @author dkalbiak
 *
 */
@JsonInclude(Include.NON_NULL) // vai incluir apenas propriedades que não estiverem nula na represesntação
@Getter
@Setter
public class ExceptionErros {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campo;

	@Getter
	@AllArgsConstructor
	public static class Campo {
		private String nomeCampo;
		private String msg;
	}

}