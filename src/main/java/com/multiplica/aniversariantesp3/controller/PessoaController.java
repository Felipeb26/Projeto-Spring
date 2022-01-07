package com.multiplica.aniversariantesp3.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.multiplica.aniversariantesp3.form.AtualizaPessoaForm;
import com.multiplica.aniversariantesp3.form.PessoaForm;
import com.multiplica.aniversariantesp3.model.Pessoa;
import com.multiplica.aniversariantesp3.repository.PessoaRepository;

@RestController
@Transactional
@CrossOrigin
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepositorio;	
	
	@GetMapping("/page")
	@Transactional
	@Cacheable(value = "listaDeTopicos")
	public Page<Pessoa> pegar(@PageableDefault(sort = "nome", direction =Direction.ASC) Pageable paginar) {
			Page<Pessoa> pessoa = pessoaRepositorio.findAll(paginar);
			return pessoa;
	}
	
	@GetMapping("/")
	@Transactional
	public List<Pessoa> pegar() {
			List<Pessoa> pessoa = pessoaRepositorio.findAll();
			return pessoa;
	}
	
	
	@GetMapping("/nome/")
	@Transactional
	public List<Pessoa> pegar(@RequestParam("nome") String nome) {
			List<Pessoa> pessoa = pessoaRepositorio.findAllById(nome);
				return pessoa;
		}
	
//	@GetMapping("/nome/")
//	@Transactional
//	public ResponseEntity<Pessoa> pegar(@RequestParam("nome") String nome) {
//			Optional<Pessoa> pessoa = pessoaRepositorio.findByNome(nome);
//			if(pessoa.isPresent()) {
//				return ResponseEntity.ok(pessoa.get());
//			}else {
//				return ResponseEntity.noContent().build();
//			}
//		}
	
	@GetMapping("/email/")
	@Transactional
	public ResponseEntity<Pessoa> pegar1(@RequestParam("email") String email) {
			Optional<Pessoa> pessoa = pessoaRepositorio.findByEmail(email);
			if(pessoa.isPresent()) {
				return ResponseEntity.ok(pessoa.get());
			}else {
				return ResponseEntity.noContent().build();
			}
		}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Pessoa> pegar(@PathVariable Long id) {
			Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
			if(pessoa.isPresent()) {
			return ResponseEntity.ok( pessoa.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseBody
	@Transactional
	@PostMapping("/inclui")
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<Pessoa> incluir(@RequestBody @Valid PessoaForm  pessoaForm, UriComponentsBuilder uriBuilder) {
        Pessoa pessoa = pessoaForm.converter(pessoaRepositorio);
        pessoaRepositorio.save(pessoa);
        
        URI uri = uriBuilder.path("/pessoa/id/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
	}
	

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<Pessoa> alterar(@PathVariable Long id, @RequestBody @Valid AtualizaPessoaForm  pessoaForm){
		Pessoa pessoa = pessoaForm.atualizar(id, pessoaRepositorio);
		
		return ResponseEntity.ok(pessoa);
 }
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<Pessoa> remover(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
		if(pessoa.isPresent()) {
		 pessoaRepositorio.deleteById(id);
		 return ResponseEntity.ok().build();
	}else {
		return ResponseEntity.notFound().build();}
	}
}




