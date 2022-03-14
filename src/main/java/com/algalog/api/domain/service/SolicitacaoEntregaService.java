package com.algalog.api.domain.service;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algalog.api.domain.model.Cliente;
import com.algalog.api.domain.model.Entrega;
import com.algalog.api.domain.model.StatusEntrega;
import com.algalog.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	
	private CatalogoClienteService catalogoClienteService;
	
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitarEntrega(Entrega entrega) {
		
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatusEntrega(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
