package com.multiplica.aniversariantesp3.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioForm {
	private String senha;
	private String email;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
