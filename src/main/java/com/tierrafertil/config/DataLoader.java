package com.tierrafertil.config;

import com.tierrafertil.model.EstadoPropiedad;
import com.tierrafertil.model.Rol;
import com.tierrafertil.model.TipoPropiedad;
import com.tierrafertil.repository.EstadoPropiedadRepository;
import com.tierrafertil.repository.RolRepository;
import com.tierrafertil.repository.TipoPropiedadRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final EstadoPropiedadRepository estadoPropiedadRepository;
    private final TipoPropiedadRepository tipoPropiedadRepository;

    public DataLoader(RolRepository rolRepository,
                       EstadoPropiedadRepository estadoPropiedadRepository,
                       TipoPropiedadRepository tipoPropiedadRepository) {
        this.rolRepository = rolRepository;
        this.estadoPropiedadRepository = estadoPropiedadRepository;
        this.tipoPropiedadRepository = tipoPropiedadRepository;
    }

    @Override
    public void run(String... args) {
        sembrarRoles();
        sembrarEstados();
        sembrarTipos();
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
}
