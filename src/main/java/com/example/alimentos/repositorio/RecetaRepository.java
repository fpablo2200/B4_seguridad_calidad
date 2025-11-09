package com.example.alimentos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alimentos.entidades.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long>{
    List<Receta> findByTituloContainingIgnoreCase(String titulo);
    List<Receta> findByPaisOrigenIgnoreCase(String paisOrigen);
}
