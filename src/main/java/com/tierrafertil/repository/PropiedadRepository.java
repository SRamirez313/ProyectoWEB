package com.tierrafertil.repository;

import com.tierrafertil.model.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
    List<Propiedad> findByUbicacion_ProvinciaContainingIgnoreCase(String provincia);
    List<Propiedad> findTop10ByOrderByIdPropiedadDesc(); // propiedades recientes (HU-18)
}
