package com.tierrafertil.repository;

import com.tierrafertil.model.SolicitudVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitudVisitaRepository extends JpaRepository<SolicitudVisita, Long> {
    List<SolicitudVisita> findByCliente_IdUsuario(Long idUsuario);
    List<SolicitudVisita> findByPropiedad_IdPropiedad(Long idPropiedad);
}
