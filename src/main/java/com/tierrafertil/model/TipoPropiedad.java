package com.tierrafertil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_propiedad")
@Getter
@Setter
@NoArgsConstructor
public class TipoPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long idTipo;

    @Column(name = "nombre_tipo", nullable = false, length = 50)
    private String nombreTipo; // Casa, Apartamento, Local comercial, Terreno

    public TipoPropiedad(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}
