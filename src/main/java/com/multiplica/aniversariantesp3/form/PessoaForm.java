package com.multiplica.aniversariantesp3.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.multiplica.aniversariantesp3.model.Pessoa;
import com.multiplica.aniversariantesp3.repository.PessoaRepository;

public class PessoaForm {
		@NotNull @NotEmpty @Length(max = 50)
		private String nome;
		@NotNull @NotEmpty @Length(min = 11, max = 14)
		private String cpf;
		private String email;
		private String nascimento;
		private String base64;
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getNascimento() {
			return nascimento;
		}
		public void setNascimento(String nascimento) {
			this.nascimento = nascimento;
		}
		public String getBase64() {
			return base64;
		}
		public void setBase64(String base64) {
			this.base64 = base64;
		}
		public Pessoa converter(PessoaRepository pessoaRepo) {
				return new Pessoa(nome , cpf, nascimento, email, base64);
				}
		}
