package com.tierrafertil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ubicacion")
@Getter
@Setter
@NoArgsConstructor
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Long idUbicacion;

    @Column(nullable = false, length = 50)
    private String provincia;

    @Column(nullable = false, length = 50)
    private String canton;

    @Column(length = 50)
    private String distrito;
}
