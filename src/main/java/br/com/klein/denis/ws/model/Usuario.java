package br.com.klein.denis.ws.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of="nome")
@Entity(name="S_USUARIO")
public class Usuario {

	@Id
	private String nome;
	
	private String senha;
}
