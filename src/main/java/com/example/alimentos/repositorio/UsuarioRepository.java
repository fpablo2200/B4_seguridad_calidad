package com.example.alimentos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.alimentos.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por usuario y clave
   Usuario findByUsername(String username);
}

