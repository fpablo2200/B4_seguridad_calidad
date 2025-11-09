package com.example.alimentos.controllers;

import java.util.List;

import com.example.alimentos.servicio.RecetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.alimentos.entidades.Receta;

@Controller
public class ControllerHome {

    @Autowired
    private RecetaService recetaService;

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(name="name", required=false, defaultValue="Seguridad y Calidad en el Desarrollo") String name, Model model) {
        model.addAttribute("name", name);
        List<Receta> recetas = recetaService.listarTodas();
        model.addAttribute("recetas", recetas);
        return "Home";
    }

    @GetMapping("/detalle")
    public String buscarRecetas(@RequestParam("q") String query, Model model) {
        List<Receta> resultados = recetaService.buscarPorTitulo(query);
        model.addAttribute("recetas", resultados);
        model.addAttribute("busqueda", query);
        return "detalle";
    }

        
    @GetMapping("/buscar")
    public String buscar() {
        return "buscar";
    }

}

