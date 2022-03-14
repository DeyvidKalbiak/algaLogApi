package com.algalog.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algalog.api.domain.model.Entrega;
import com.algalog.api.domain.repository.EntregaRepository;
import com.algalog.api.domain.service.FinalizacaoEntregaService;
import com.algalog.api.domain.service.SolicitacaoEntregaService;
import com.algalog.api.model.EntregaModel;
import com.algalog.api.model.request.EntregaRequest;
import com.algalog.api.util.EntregaAssemblerUtil;

import lombok.AllArgsConstructor;

/**
 * Processamento das requisições e respostas Entregas
 * 
 * @author dkalbiak
 *
 */
@AllArgsConstructor
@RestController
@RequestMapping("/entrega")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	private EntregaRepository entregaRepository;

	// usa a biblioteca Model Mapper para atribuir todas as propriedade para cada
	// atributo de classes
	private EntregaAssemblerUtil entregaAssembler;

	/**
	 * Cria uma nova entrega
	 * 
	 * @param entrega
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel criarEntrega(@RequestBody EntregaRequest entrega) {
		Entrega novaEntrega = entregaAssembler.toEntity(entrega);
		Entrega entraSolicitada = solicitacaoEntregaService.solicitarEntrega(novaEntrega);

		return entregaAssembler.toModel(entraSolicitada);

	}

	/**
	 * Finaliza um entrega
	 * 
	 * @param id
	 */
	@PutMapping("/{id}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long id) {
		finalizacaoEntregaService.finalizar(id);
	}

	/**
	 * Lista todas as entregas
	 * 
	 * @return
	 */
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}

	/**
	 * Lista entrega por id
	 * 
	 * @param entregaId
	 * @return
	 */
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
}