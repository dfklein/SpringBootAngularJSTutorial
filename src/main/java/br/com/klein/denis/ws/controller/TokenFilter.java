package br.com.klein.denis.ws.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String header = req.getHeader("Authorization"); // esta é a chave de um valor do header onde você vai colocar o token para todas as requisiçoes com o token vindo do Angular.
		
		if(header == null || !header.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou inválido");
		}
		
		String token = header.substring(7); // extrai a string do header a partir do "Bearer " que é onde está o token.
		
		// verifica se o token é válido
		try {
			Jwts.parser().setSigningKey("chavecriptografia").parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			throw new ServletException("Token inválido ExpiredJwtException");
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
			throw new ServletException("Token inválido UnsupportedJwtException");
		} catch (MalformedJwtException e) {
			e.printStackTrace();
			throw new ServletException("Token inválido MalformedJwtException");
		} catch (SignatureException e) {
			e.printStackTrace();
			throw new ServletException("Token inválido SignatureException");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new ServletException("Token inválido IllegalArgumentException");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Token inválido Exception");
		} finally {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado"); // este é  cara que lança o erro 401 que você busca no token-interceptor.js
			
		}
		
		chain.doFilter(request, response); // é o comando que permite que o filtro continue a execução da requisição
		
	}

}
