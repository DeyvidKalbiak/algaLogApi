package com.algalog.api.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algalog.api.domain.exception.NovaException;
import com.algalog.api.domain.model.Cliente;
import com.algalog.api.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

/**
 * Classe de serviços
 * 
 * @author dkalbiak
 *
 */
@Service
@AllArgsConstructor
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(emailExistente -> !emailExistente.equals(cliente)); // verifica se o e-mail já esta em uso

		if (emailEmUso)
			throw new NovaException("E-mail j\u00E1 cadastrado, entre com um novo e-mail!");

		return clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente buscar(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new NovaException("Cliente n\u00E3o foi encontrado!"));
	}

}
