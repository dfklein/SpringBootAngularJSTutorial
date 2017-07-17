package br.com.klein.denis.ws.controller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.klein.denis.ws.model.Cliente;
import br.com.klein.denis.ws.service.ClienteService;

@RestController("/privado")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	// endpoints
	@RequestMapping(method=RequestMethod.POST, 
			value="/clientes", 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) { // o @RequestBody indica que aquela variável é o parâmetro que será recebido no JSON.
		// O ResponseEntity é o objeto que você usa para retornar o JSON para o browser
		Cliente novoCliente = clienteService.cadastrar(cliente);
		
		return new ResponseEntity<Cliente>(novoCliente, HttpStatus.CREATED); // o segundo parâmetro é o status Http que você vai retornar junto do seu objeto.
		

	}
	
			
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
		Collection<Cliente> clientesBuscados = clienteService.buscarTodos();
		
		return new ResponseEntity<Collection<Cliente>>(clientesBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscarPorId(id);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			clienteService.excluir(cliente);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/clientes/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@PathVariable Cliente cliente) {
		Cliente clienteAlterado = clienteService.alterar(cliente);
		
		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
		
	}

}
