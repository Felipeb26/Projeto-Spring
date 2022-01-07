package com.multiplica.aniversariantesp3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multiplica.aniversariantesp3.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByNome(String nome);
	
	Page<Pessoa> findById(Long id, Pageable paginar);

	Optional<Pessoa> findByEmail(String email);

	List<Pessoa> findByNomeUnico(String nome);

	List<Pessoa> findAllById(String nome);
}
