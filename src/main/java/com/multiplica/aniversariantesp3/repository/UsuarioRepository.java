package com.multiplica.aniversariantesp3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.multiplica.aniversariantesp3.model.PessoaDto;

public interface UsuarioRepository extends JpaRepository<PessoaDto, Long> {
	
	Optional<PessoaDto> findByEmail(String email);
}
