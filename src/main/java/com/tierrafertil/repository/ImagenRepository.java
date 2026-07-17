package com.tierrafertil.repository;

import com.tierrafertil.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    List<Imagen> findByPropiedad_IdPropiedad(Long idPropiedad);
}
