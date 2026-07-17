package com.tierrafertil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagenes")
@Getter
@Setter
@NoArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    private Propiedad propiedad;

    // Ruta local donde se guarda el archivo subido (no es un link externo)
    @Column(name = "ruta_imagen", nullable = false, length = 255)
    private String rutaImagen;
}
