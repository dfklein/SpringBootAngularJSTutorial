package br.com.klein.denis.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.klein.denis.ws.model.Cliente;
import br.com.klein.denis.ws.model.Usuario;

@RestController
public class LoginController {

	@RequestMapping(method=RequestMethod.POST,
			value="/autenticar",
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> autenticarCliente(@RequestBody Usuario usuario) {
		
		
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	
}
