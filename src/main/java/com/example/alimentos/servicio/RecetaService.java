package com.example.alimentos.servicio;

import com.example.alimentos.entidades.Receta;
import com.example.alimentos.repositorio.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> listarTodas() {
        return recetaRepository.findAll();
    }

    public Receta obtenerPorId(Long id) {
        Optional<Receta> receta = recetaRepository.findById(id);
        return receta.orElse(null);
    }

    public List<Receta> buscarPorTitulo(String titulo) {
        return recetaRepository.findByTituloContainingIgnoreCase(titulo);
    }


    public List<Receta> buscarPorPais(String paisOrigen) {
        return recetaRepository.findByPaisOrigenIgnoreCase(paisOrigen);
    }
}
