package br.com.klein.denis.ws.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Esta é a classe que cria um filtro para verificar se o token existe e se está válido.
 * @author dklei
 */
public class TokenFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String header = req.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou inválido");
		}
		
		String token = header.substring(7); // extrai a string do header a partir do "Bearer " que é onde está o token.
		
		// verifica se o token é válido
		try {
			Jwts.parser().setSigningKey("chavecriptografia").parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
			throw new ServletException("Token inválido");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		chain.doFilter(request, response); // é o comando que permite que o filtro continue a execução da requisição
		
	}

}
