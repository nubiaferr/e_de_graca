package com.EDG.BackEndEDG.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EDG.BackEndEDG.Model.ModelTema;
import com.EDG.BackEndEDG.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ModelTema>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ModelTema> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/area/{area}")
	public ResponseEntity<List<ModelTema>> GetByArea(@PathVariable String area){
		return ResponseEntity.ok(repository.findAllByAreaContainingIgnoreCase(area));
	}
	
	@GetMapping("/tipo_de_acao/{tipo_de_acao}")
	public ResponseEntity<List<ModelTema>> GetByTipodeAcao (@PathVariable String tipo_de_acao){
		return ResponseEntity.ok(repository.findAllByTipoDeAcaoContainingIgnoreCase(tipo_de_acao));
	}
	
	@GetMapping("/publico/{publico}")
	public ResponseEntity<List<ModelTema>> GetByPublico (@PathVariable String publico){
		return ResponseEntity.ok(repository.findAllByPublicoContainingIgnoreCase(publico));
	}
	
	@GetMapping("/cidade/{cidade}")
	public ResponseEntity<List<ModelTema>> GetByCidade (@PathVariable String cidade){
		return ResponseEntity.ok(repository.findAllByCidadeContainingIgnoreCase(cidade));
	}
	
	@PostMapping
	public ResponseEntity<ModelTema> Post(@RequestBody ModelTema post){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
	}
	
	@PutMapping
	public ResponseEntity<ModelTema> Put(@RequestBody ModelTema put){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(put));
	}
	
	@DeleteMapping("/{id}")
	public String Delete(@PathVariable long id) {
		repository.deleteById(id);
		return "Post deletado";
	}
}
