package com.tierrafertil.service;

import com.tierrafertil.dto.RegistroDTO;
import com.tierrafertil.model.Rol;
import com.tierrafertil.model.Usuario;
import com.tierrafertil.repository.RolRepository;
import com.tierrafertil.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private static final String ROL_POR_DEFECTO = "CLIENTE";

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                           RolRepository rolRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario registrar(RegistroDTO dto) {
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new CorreoYaRegistradoException(dto.getCorreo());
        }

        Rol rolCliente = rolRepository.findByNombreRol(ROL_POR_DEFECTO)
                .orElseThrow(() -> new IllegalStateException(
                        "El rol " + ROL_POR_DEFECTO + " no existe. Verifique los datos semilla."));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena())); // cifrado BCrypt
        usuario.setRol(rolCliente);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario cambiarRol(Long idUsuario, Long idRol) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + idUsuario));
        Rol nuevoRol = rolRepository.findById(idRol)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + idRol));
        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }
}
