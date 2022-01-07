package com.multiplica.aniversariantesp3.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.multiplica.aniversariantesp3.form.UsuarioForm;
import com.multiplica.aniversariantesp3.security.TokenService;


@RestController
public class AutenticaController {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	@Transactional
	public ResponseEntity<?>autentica(@RequestBody @Valid UsuarioForm  form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
		
			return ResponseEntity.ok(token);
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}		
  }
}
