package com.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algalog.api.domain.exception.EntidadeNaoEncontradaException;
import com.algalog.api.domain.exception.NovaException;

import lombok.AllArgsConstructor;

/**
 * Trata todas exceptions da aplicação
 * 
 * @author dkalbiak
 *
 */
@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ExceptionErros.Campo> problemas = new ArrayList<ExceptionErros.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			problemas.add(new ExceptionErros.Campo(nome, msg));
		}

		ExceptionErros erros = new ExceptionErros();
		erros.setStatus(status.value());
		erros.setDataHora(OffsetDateTime.now());
		erros.setTitulo("Um ou mais campos do Payload est\u00E3o incorretos, valide seus campos!");
		erros.setCampo(problemas);

		return handleExceptionInternal(ex, erros, headers, status, request);
	}

	/**
	 * Metodo responsavel pelo tratamento das exceptions da classe
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NovaException.class)
	public ResponseEntity<Object> handleNegocio(NovaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ExceptionErros erros = new ExceptionErros();
		erros.setStatus(status.value());
		erros.setDataHora(OffsetDateTime.now());
		erros.setTitulo(ex.getMessage());
				
		return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
	}
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ExceptionErros erros = new ExceptionErros();
		erros.setStatus(status.value());
		erros.setDataHora(OffsetDateTime.now());
		erros.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
	}
}
