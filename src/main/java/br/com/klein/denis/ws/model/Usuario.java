package br.com.klein.denis.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of="id")
@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String senha;
	
}
