package com.algalog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algalog.api.domain.model.Entrega;
import com.algalog.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public void finalizar(Long id) {
		Entrega entrega = buscaEntregaService.buscar(id);
			
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
}
