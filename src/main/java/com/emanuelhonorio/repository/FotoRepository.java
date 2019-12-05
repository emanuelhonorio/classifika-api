package com.emanuelhonorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.model.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {
	
	void deleteAllByAnuncioId(Long id);

}
