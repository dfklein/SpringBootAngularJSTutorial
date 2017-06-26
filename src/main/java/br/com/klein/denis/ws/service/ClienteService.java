package br.com.klein.denis.ws.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.klein.denis.ws.model.Cliente;
import br.com.klein.denis.ws.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	private HashMap<Integer, Cliente> clientes = new HashMap<>();
	private Integer proximoId = 1;
	
	// regras de negócio (isso ficaria em uma classe separada anotada com @Service)
	public Cliente cadastrar(Cliente cliente) {
		
		return clienteRepository.save(cliente);
		
	}
	
	public void excluir(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	public Collection<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}
	
	public Cliente buscarPorId(Integer id) {
		return clienteRepository.findOne(id);
	}
	
	public Cliente alterar(Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}

}
