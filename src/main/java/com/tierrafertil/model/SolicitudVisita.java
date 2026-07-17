package com.tierrafertil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_visitas")
@Getter
@Setter
@NoArgsConstructor
public class SolicitudVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    // Cliente que solicita (usuario con rol CLIENTE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    private Propiedad propiedad;

    @Column(name = "fecha_visita", nullable = false)
    private LocalDateTime fechaVisita;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_solicitud", nullable = false, length = 20)
    private EstadoSolicitud estadoSolicitud = EstadoSolicitud.PENDIENTE;

    public enum EstadoSolicitud {
        PENDIENTE, APROBADA, RECHAZADA
    }
}
