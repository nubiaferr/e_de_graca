package com.EDG.BackEndEDG.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.EDG.BackEndEDG.Model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	public List<Postagem>findAllByTituloContainingIgnoreCase(String titulo);
}
