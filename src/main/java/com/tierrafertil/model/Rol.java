package com.tierrafertil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre_rol", nullable = false, unique = true, length = 30)
    private String nombreRol; // ADMIN, AGENTE, CLIENTE

    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
