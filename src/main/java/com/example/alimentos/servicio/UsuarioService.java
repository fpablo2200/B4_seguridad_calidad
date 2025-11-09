package com.example.alimentos.servicio;

import java.util.List;

import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.alimentos.entidades.Usuario;
import com.example.alimentos.repositorio.UsuarioRepository;
import org.springframework.security.core.userdetails.*;

import org.slf4j.Logger;

@Configuration
@Service
public class UsuarioService implements UserDetailsService{

    Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Devuelve un objeto User (de Spring Security)
        return User.builder()
                .username(user.getUsername())
                // ⚠️ Si tus contraseñas no están encriptadas, usa {noop}
                .password("{noop}" + user.getPassword())
                .roles(user.getRol()) // Asigna rol
                .build();
    }



    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios;
    }
    
    public Usuario obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.orElse(null);
    }



}
