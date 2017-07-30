package br.com.klein.denis.ws.controller;


//import java.security.Key;
//import java.util.Base64;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.klein.denis.ws.model.Usuario;
import br.com.klein.denis.ws.service.UsuarioService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
//	  private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
//    private static final byte[] secretBytes = secret.getEncoded();
//    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	/**
	 * Este método autentica o usuário de utiliza o framework de seguranca JWT para encripitar dados no JSON enviado como resposta.
	 */
	@RequestMapping(method=RequestMethod.POST,
			value="/autenticar",
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse autenticarCliente(@RequestBody Usuario usuario) throws ServletException {
		
		// ***************************** Verificações de validação das infos informadas
		if(usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e senha são de preenchimento obrigatórios");
		}
		
		Usuario usuAutenticado = usuarioService.buscarPorNome(usuario.getNome());
		
		if (usuAutenticado == null) {
			throw new ServletException("Usuário não encontrado");
		}
		
		if(!usuAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Senha inválida");
			
		}
		System.out.println("************ Cliente em autenticação: token será criado ***************");
		// ***************************** Encripta a informação para enviar ao front-end
		JwtBuilder builder = Jwts.builder(); // Isto é o que cria um retorno encriptografado do login do seu usuário.
		String token = builder.setSubject(usuario.getNome())
				.signWith(SignatureAlgorithm.HS512, "chavecriptografia") // o primeiro argumento é o algoritmo de segurança para gerar a criptografia. O segundo é a chave de segurança para decriptar o objeto.
//				.signWith(SignatureAlgorithm.HS256, base64SecretBytes)
//				.setAudience(arg0) // no curso ele não mostra isto, mas aqui é onde você passa os permissionamentos contidos no seu token
				// .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)) // você passa em um objeto tipo Date o período de validade deste token.
				.compact(); // transforma o objeto encriptado em String.
		
		return new LoginResponse(token);
//		return new ResponseEntity<Usuario>(usuAutenticado, HttpStatus.OK); // isto não tem o token
	}
	
	
	@AllArgsConstructor
	@Getter
	private class LoginResponse {
	
		private String token;
		
	}
	
	public Usuario cadastrarPorNome(String nome) {
		return usuarioService.buscarPorNome(nome);
		
	}
}
