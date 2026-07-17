package com.tierrafertil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propiedades")
@Getter
@Setter
@NoArgsConstructor
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Long idPropiedad;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    // Agente que gestiona la propiedad (1 agente -> muchas propiedades)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agente", nullable = false)
    private Usuario agente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoPropiedad tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoPropiedad estado;

    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes = new ArrayList<>();
}
