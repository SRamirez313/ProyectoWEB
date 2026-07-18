package com.tierrafertil.config;

import com.tierrafertil.model.EstadoPropiedad;
import com.tierrafertil.model.Rol;
import com.tierrafertil.model.TipoPropiedad;
import com.tierrafertil.model.Usuario;
import com.tierrafertil.repository.EstadoPropiedadRepository;
import com.tierrafertil.repository.RolRepository;
import com.tierrafertil.repository.TipoPropiedadRepository;
import com.tierrafertil.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    // Cuenta admin de arranque, solo para desarrollo del equipo.
    private static final String ADMIN_CORREO = "admin@tierrafertil.com";
    private static final String ADMIN_PASSWORD = "Admin1234";

    private final RolRepository rolRepository;
    private final EstadoPropiedadRepository estadoPropiedadRepository;
    private final TipoPropiedadRepository tipoPropiedadRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RolRepository rolRepository,
                       EstadoPropiedadRepository estadoPropiedadRepository,
                       TipoPropiedadRepository tipoPropiedadRepository,
                       UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.estadoPropiedadRepository = estadoPropiedadRepository;
        this.tipoPropiedadRepository = tipoPropiedadRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        sembrarRoles();
        sembrarEstados();
        sembrarTipos();
        sembrarAdmin();
    }

    private void sembrarRoles() {
        if (rolRepository.count() == 0) {
            rolRepository.save(new Rol("ADMIN"));
            rolRepository.save(new Rol("AGENTE"));
            rolRepository.save(new Rol("CLIENTE"));
        }
    }

    private void sembrarEstados() {
        if (estadoPropiedadRepository.count() == 0) {
            estadoPropiedadRepository.save(new EstadoPropiedad("Disponible"));
            estadoPropiedadRepository.save(new EstadoPropiedad("Vendida"));
            estadoPropiedadRepository.save(new EstadoPropiedad("Alquilada"));
        }
    }

    private void sembrarTipos() {
        if (tipoPropiedadRepository.count() == 0) {
            tipoPropiedadRepository.save(new TipoPropiedad("Casa"));
            tipoPropiedadRepository.save(new TipoPropiedad("Apartamento"));
            tipoPropiedadRepository.save(new TipoPropiedad("Local comercial"));
            tipoPropiedadRepository.save(new TipoPropiedad("Terreno"));
        }
    }

    private void sembrarAdmin() {
        if (usuarioRepository.existsByCorreo(ADMIN_CORREO)) {
            return; // ya existe, no crear duplicado
        }
        Rol rolAdmin = rolRepository.findByNombreRol("ADMIN")
                .orElseThrow(() -> new IllegalStateException("El rol ADMIN no existe. Verifique sembrarRoles()."));

        Usuario admin = new Usuario();
        admin.setNombre("Administrador Tierra Fertil");
        admin.setCorreo(ADMIN_CORREO);
        admin.setContrasena(passwordEncoder.encode(ADMIN_PASSWORD));
        admin.setRol(rolAdmin);
        usuarioRepository.save(admin);

        System.out.println("=====================================================");
        System.out.println(" Usuario ADMIN de arranque creado:");
        System.out.println(" Correo:     " + ADMIN_CORREO);
        System.out.println(" Contrasena: " + ADMIN_PASSWORD);
        System.out.println(" (Cambien esta contrasena antes de la entrega final)");
        System.out.println("=====================================================");
    }
}
