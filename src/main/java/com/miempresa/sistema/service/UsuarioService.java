package com.miempresa.sistema.service;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String contrasena) {
        Optional<Usuario> optional = usuarioRepository.findByEmailAndContrasena(correo, contrasena);
        return optional.orElse(null);
    }
}
