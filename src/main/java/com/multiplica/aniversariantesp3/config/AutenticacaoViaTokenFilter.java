package com.multiplica.aniversariantesp3.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.multiplica.aniversariantesp3.model.PessoaDto;
import com.multiplica.aniversariantesp3.repository.UsuarioRepository;
import com.multiplica.aniversariantesp3.security.TokenService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private UsuarioRepository userRepo;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository userRepo) {
		this.tokenService = tokenService;
		this.userRepo = userRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean valido = tokenService.isTokenValido(token);
		if(valido) {
			autenticaPessoa(token);
		}
		System.out.println(valido);
		filterChain.doFilter(request, response);
	}

	private void autenticaPessoa(String token){
		Long idPessoa = tokenService.getIdPessoa(token);
		PessoaDto  pessoa = userRepo.getById(idPessoa);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(pessoa, null, pessoa.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
