package com.example.alimentos.controllers;

import com.example.alimentos.entidades.Receta;
import com.example.alimentos.servicio.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;



    // ruta privada
    @GetMapping("/buscar")
    public String buscarRecetas(@RequestParam("q") String query, Model model) {
        List<Receta> resultados = List.of();
        if (query != null && !query.isBlank()) {
            resultados = recetaService.buscarPorTitulo(query);
        }
        model.addAttribute("recetas", resultados);
        model.addAttribute("query", query);

        return "buscar";
    }

    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Receta receta = recetaService.obtenerPorId(id);
        if (receta == null) {
            return "redirect:/recetas/inicio";
        }
        model.addAttribute("receta", receta);
        return "detalle";
    }


}
