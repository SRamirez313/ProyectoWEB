package com.tierrafertil.repository;

import com.tierrafertil.model.EstadoPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstadoPropiedadRepository extends JpaRepository<EstadoPropiedad, Long> {
    Optional<EstadoPropiedad> findByNombreEstado(String nombreEstado);
}
