package br.com.klein.denis.ws.controller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import br.com.klein.denis.ws.model.Cliente;

@RestController
public class ClienteController {

	HashMap<Integer, Cliente> clientes = new HashMap<>();
	Integer proximoId = 1;
	
	// regras de negócio (isso ficaria em uma classe separada anotada com @Service)
	private Cliente cadastrar(Cliente cliente) {
		
		cliente.setId(this.proximoId);
		this.proximoId++;
		
		clientes.put(cliente.getId(), cliente);
		
		return cliente;
	}
	
	private void excluir(Cliente cliente) {
		clientes.remove(cliente.getId()); // se você fizer pelo objeto ao invés da key, você precisa do equals e do hashcode implementados.
	}
	
	private Collection<Cliente> buscarTodos() {
		return clientes.values();
	}
	
	private Cliente buscarPorId(Integer id) {
		return clientes.get(id);
	}
	
	private Cliente alterar(Cliente cliente) {
		clientes.put(cliente.getId(), cliente);
		return cliente;
	}
	
	// endpoints
	@RequestMapping(method=RequestMethod.POST, 
			value="/clientes", 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) { // o @RequestBody indica que aquela variável é o parâmetro que será recebido no JSON.
		// O ResponseEntity é o objeto que você usa para retornar o JSON para o browser
		Cliente novoCliente = cadastrar(cliente);
		
		return new ResponseEntity<Cliente>(novoCliente, HttpStatus.CREATED); // o segundo parâmetro é o status Http que você vai retornar junto do seu objeto.
		

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
		Collection<Cliente> clientesBuscados = buscarTodos();
		
		return new ResponseEntity<Collection<Cliente>>(clientesBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
		Cliente cliente = buscarPorId(id);
		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			excluir(cliente);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/clientes/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@PathVariable Cliente cliente) {
		Cliente clienteAlterado = alterar(cliente);
		
		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
		
	}

}
