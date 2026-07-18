package com.tierrafertil.service;

import com.tierrafertil.dto.PropiedadDTO;
import com.tierrafertil.model.*;
import com.tierrafertil.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PropiedadService {

    private final PropiedadRepository propiedadRepository;
    private final TipoPropiedadRepository tipoPropiedadRepository;
    private final EstadoPropiedadRepository estadoPropiedadRepository;
    private final UbicacionRepository ubicacionRepository;
    private final ImagenRepository imagenRepository;
    private final FileStorageService fileStorageService;

    public PropiedadService(PropiedadRepository propiedadRepository,
                             TipoPropiedadRepository tipoPropiedadRepository,
                             EstadoPropiedadRepository estadoPropiedadRepository,
                             UbicacionRepository ubicacionRepository,
                             ImagenRepository imagenRepository,
                             FileStorageService fileStorageService) {
        this.propiedadRepository = propiedadRepository;
        this.tipoPropiedadRepository = tipoPropiedadRepository;
        this.estadoPropiedadRepository = estadoPropiedadRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.imagenRepository = imagenRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<Propiedad> listarTodas() {
        return propiedadRepository.findAll();
    }

    public Propiedad obtenerPorId(Long id) {
        return propiedadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada: " + id));
    }

    @Transactional
    public Propiedad crear(PropiedadDTO dto, Usuario agente) {
        // Regla de negocio: solo un usuario con rol AGENTE o ADMIN puede quedar como agente de una propiedad.
        String rol = agente.getRol().getNombreRol();
        if (!rol.equals("AGENTE") && !rol.equals("ADMIN")) {
            throw new IllegalStateException("Solo un agente inmobiliario o administrador puede registrar propiedades");
        }

        Propiedad propiedad = new Propiedad();
        aplicarDatosBasicos(propiedad, dto);
        propiedad.setAgente(agente);

        Propiedad guardada = propiedadRepository.save(propiedad);
        guardarImagenesNuevas(guardada, dto.getImagenes());
        return guardada;
    }

    @Transactional
    public Propiedad actualizar(Long id, PropiedadDTO dto) {
        Propiedad propiedad = obtenerPorId(id);
        aplicarDatosBasicos(propiedad, dto);
        Propiedad actualizada = propiedadRepository.save(propiedad);
        guardarImagenesNuevas(actualizada, dto.getImagenes());
        return actualizada;
    }

    @Transactional
    public void eliminar(Long id) {
        if (!propiedadRepository.existsById(id)) {
            throw new IllegalArgumentException("Propiedad no encontrada: " + id);
        }
        propiedadRepository.deleteById(id);
    }

    @Transactional
    public Propiedad cambiarEstado(Long id, Long idEstado) {
        Propiedad propiedad = obtenerPorId(id);
        EstadoPropiedad estado = estadoPropiedadRepository.findById(idEstado)
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado: " + idEstado));
        propiedad.setEstado(estado);
        return propiedadRepository.save(propiedad);
    }

    // --- Auxiliares para los formularios (listas desplegables) ---
    public List<TipoPropiedad> listarTipos() {
        return tipoPropiedadRepository.findAll();
    }

    public List<EstadoPropiedad> listarEstados() {
        return estadoPropiedadRepository.findAll();
    }

    private void aplicarDatosBasicos(Propiedad propiedad, PropiedadDTO dto) {
        propiedad.setTitulo(dto.getTitulo());
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setPrecio(dto.getPrecio());

        TipoPropiedad tipo = tipoPropiedadRepository.findById(dto.getIdTipo())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de propiedad no encontrado: " + dto.getIdTipo()));
        propiedad.setTipo(tipo);

        EstadoPropiedad estado = estadoPropiedadRepository.findById(dto.getIdEstado())
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado: " + dto.getIdEstado()));
        propiedad.setEstado(estado);

        propiedad.setUbicacion(buscarOCrearUbicacion(dto.getProvincia(), dto.getCanton(), dto.getDistrito()));
    }

    private Ubicacion buscarOCrearUbicacion(String provincia, String canton, String distrito) {
        return ubicacionRepository.findAll().stream()
                .filter(u -> u.getProvincia().equalsIgnoreCase(provincia)
                        && u.getCanton().equalsIgnoreCase(canton)
                        && ((u.getDistrito() == null && distrito == null)
                            || (u.getDistrito() != null && u.getDistrito().equalsIgnoreCase(distrito))))
                .findFirst()
                .orElseGet(() -> {
                    Ubicacion nueva = new Ubicacion();
                    nueva.setProvincia(provincia);
                    nueva.setCanton(canton);
                    nueva.setDistrito(distrito);
                    return ubicacionRepository.save(nueva);
                });
    }

    private void guardarImagenesNuevas(Propiedad propiedad, List<MultipartFile> archivos) {
        if (archivos == null) return;
        for (MultipartFile archivo : archivos) {
            if (archivo == null || archivo.isEmpty()) continue;
            String ruta = fileStorageService.guardarImagen(archivo);
            Imagen imagen = new Imagen();
            imagen.setPropiedad(propiedad);
            imagen.setRutaImagen(ruta);
            imagenRepository.save(imagen);
        }
    }
}
