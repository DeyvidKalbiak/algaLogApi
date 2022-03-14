package com.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algalog.api.domain.model.Cliente;
import com.algalog.api.domain.repository.ClienteRepository;
import com.algalog.api.domain.service.CatalogoClienteService;

/**
 * Processamento das requisições e respostas cliente
 * 
 * @author dkalbiak
 *
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CatalogoClienteService catalogoClienteService;

	/**
	 * Lista todos o clientes
	 * 
	 * @return
	 */
	@GetMapping()
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	/**
	 * Lista Cliente por id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id) {

		// MÉTODO SIMPLES COM LAMBDA PARA FAZER O RETORNO
		return clienteRepository.findById(id).map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());

		/**
		 * código longo
		 */
		// Ele vai retorar um optional então tem que declarar
//		Optional<Cliente> cliente = clienteRepository.findById(id);
//		if(cliente.isPresent())
//			return ResponseEntity.ok(cliente.get());
//		
//		else
//			return ResponseEntity.notFound().build();

	}// FIM listarCliente

	/**
	 * A ANOTAÇÃO requestBody faz com que o json vindo na url seja vinculado ao meu
	 * objeto cliente passado no parametro basciamente ele desserializa o json para
	 * um objeto cliente
	 * 
	 * @param cliente
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK) // o response status é uma maneira mais simples de retornar o status desejado
	// com ele eu não preciso alterar a assinatura do método
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
	}

	/**
	 * Atualiza os dados de cliente quando necessário
	 * 
	 * @param id
	 * @param cliente
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarDados(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(id))
			return ResponseEntity.notFound().build();

		cliente.setId(id);// estou atribuindo o id do cliente pra ele mesmo, se não ele não vai atualizar
							// e sim cliar outro cliente
		cliente = catalogoClienteService.salvar(cliente);

		return ResponseEntity.ok(cliente);

	}

	/**
	 * Excluir um usuário ou cliente
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		if (!clienteRepository.existsById(id))
			return ResponseEntity.notFound().build();

		catalogoClienteService.excluir(id);

		return ResponseEntity.noContent().build();
	}
}
