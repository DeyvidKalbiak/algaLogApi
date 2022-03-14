package com.algalog.api.domain.service;

import org.springframework.stereotype.Service;

import com.algalog.api.domain.exception.EntidadeNaoEncontradaException;
import com.algalog.api.domain.model.Entrega;
import com.algalog.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long id) {
		return entregaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega n\u00E3o encontrada!"));
	}
}
