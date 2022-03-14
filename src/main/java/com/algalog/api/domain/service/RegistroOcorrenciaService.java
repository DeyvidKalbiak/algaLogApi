package com.algalog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algalog.api.domain.model.Entrega;
import com.algalog.api.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public Ocorrencia registrar(Long id, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(id);
		
		return entrega.adicionarOcorrencia(descricao);
	}
}
