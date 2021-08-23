package com.EDG.BackEndEDG.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EDG.BackEndEDG.model.Tema;



@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	public List<Tema>findAllByTituloContainingIgnoreCase(String titulo);


}

