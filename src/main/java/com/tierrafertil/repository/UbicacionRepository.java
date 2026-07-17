package com.tierrafertil.repository;

import com.tierrafertil.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    List<Ubicacion> findByProvinciaContainingIgnoreCase(String provincia);
}
