package com.multiplica.aniversariantesp3.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.multiplica.aniversariantesp3.model.PessoaDto;
import com.multiplica.aniversariantesp3.repository.UsuarioRepository;

@Service
public class AuthenticateService implements UserDetailsService {

	@Autowired
	private UsuarioRepository pessoaRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<PessoaDto> pessoa = pessoaRepo.findByEmail(username);
		if (pessoa.isPresent()) {
			return pessoa.get();
		}{
			throw new UsernameNotFoundException("deu ruim ");
		}
	}
}
