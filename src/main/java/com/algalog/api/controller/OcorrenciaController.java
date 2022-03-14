package com.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algalog.api.domain.model.Entrega;
import com.algalog.api.domain.model.Ocorrencia;
import com.algalog.api.domain.service.BuscaEntregaService;
import com.algalog.api.domain.service.RegistroOcorrenciaService;
import com.algalog.api.model.OcorrenciaModel;
import com.algalog.api.util.OcorrenciaAssemblerUtil;
import com.algalog.api.util.OcorrenciaInput;

import lombok.AllArgsConstructor;

/**
 * Processamento das requisições e respostas das ocorrencias
 * 
 * @author dkalbiak
 *
 */
@AllArgsConstructor
@RestController
@RequestMapping("/entrega/{id}/ocorrencia")
public class OcorrenciaController {

	private BuscaEntregaService buscaEntregaService;
	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssemblerUtil ocorrenciaAssembler;

	/**
	 * Registra uma nova ocorrencia
	 * 
	 * @param id
	 * @param ocorrenciaInput
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long id, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrencia = registroOcorrenciaService.registrar(id, ocorrenciaInput.getDescricao());

		return ocorrenciaAssembler.toModel(ocorrencia);
	}

	/**
	 * Lista as ocorrencias
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long id) {
		Entrega entrega = buscaEntregaService.buscar(id);

		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}

}