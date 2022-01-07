package com.multiplica.aniversariantesp3.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.multiplica.aniversariantesp3.model.Pessoa;
import com.multiplica.aniversariantesp3.repository.PessoaRepository;

public class AtualizaPessoaForm {

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

	public Pessoa atualizar(long id, PessoaRepository pessoaRepositorio) {
		Pessoa pessoa= pessoaRepositorio.findById(id).get();
		
		pessoa.setNome(this.nome);
		pessoa.setCpf(this.cpf);
		pessoa.setNascimento(this.nascimento);
		pessoa.setEmail(this.email);
		pessoa.setBase64(this.base64);

		return pessoa;
	}
}
