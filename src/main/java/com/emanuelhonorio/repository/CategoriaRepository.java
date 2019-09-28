package com.emanuelhonorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
