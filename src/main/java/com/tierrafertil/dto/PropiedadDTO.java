package com.tierrafertil.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PropiedadDTO {

    private Long idPropiedad; // null cuando es creacion, con valor cuando es edicion

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private BigDecimal precio;

    @NotNull(message = "Selecciona un tipo de propiedad")
    private Long idTipo;

    @NotNull(message = "Selecciona un estado")
    private Long idEstado;

    @NotBlank(message = "La provincia es obligatoria")
    private String provincia;

    @NotBlank(message = "El canton es obligatorio")
    private String canton;

    private String distrito;

    // Imagenes nuevas que el agente sube en el formulario (opcional)
    private List<MultipartFile> imagenes;
}
