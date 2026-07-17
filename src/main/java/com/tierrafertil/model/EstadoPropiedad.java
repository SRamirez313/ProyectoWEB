package com.tierrafertil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado_propiedad")
@Getter
@Setter
@NoArgsConstructor
public class EstadoPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long idEstado;

    @Column(name = "nombre_estado", nullable = false, unique = true, length = 30)
    private String nombreEstado; // Disponible, Vendida, Alquilada

    public EstadoPropiedad(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
