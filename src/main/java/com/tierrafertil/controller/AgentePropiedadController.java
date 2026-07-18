package com.tierrafertil.controller;

import com.tierrafertil.dto.PropiedadDTO;
import com.tierrafertil.model.Propiedad;
import com.tierrafertil.security.UserDetailsImpl;
import com.tierrafertil.service.PropiedadService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agente/propiedades")
public class AgentePropiedadController {

    private final PropiedadService propiedadService;

    public AgentePropiedadController(PropiedadService propiedadService) {
        this.propiedadService = propiedadService;
    }

    // HU-07: listado de propiedades registradas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("propiedades", propiedadService.listarTodas());
        return "agente/propiedades/lista";
    }

    // HU-06: formulario de registro de una nueva propiedad
    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("propiedadDTO", new PropiedadDTO());
        cargarListasDesplegables(model);
        return "agente/propiedades/formulario";
    }

    @PostMapping("/nueva")
    public String crear(@Valid @ModelAttribute PropiedadDTO propiedadDTO,
                         BindingResult resultado,
                         @AuthenticationPrincipal UserDetailsImpl usuarioActual,
                         Model model) {
        if (resultado.hasErrors()) {
            cargarListasDesplegables(model);
            return "agente/propiedades/formulario";
        }
        propiedadService.crear(propiedadDTO, usuarioActual.getUsuario());
        return "redirect:/agente/propiedades";
    }

    // HU-08: edicion de una propiedad existente
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Propiedad propiedad = propiedadService.obtenerPorId(id);

        PropiedadDTO dto = new PropiedadDTO();
        dto.setIdPropiedad(propiedad.getIdPropiedad());
        dto.setTitulo(propiedad.getTitulo());
        dto.setDescripcion(propiedad.getDescripcion());
        dto.setPrecio(propiedad.getPrecio());
        dto.setIdTipo(propiedad.getTipo().getIdTipo());
        dto.setIdEstado(propiedad.getEstado().getIdEstado());
        dto.setProvincia(propiedad.getUbicacion().getProvincia());
        dto.setCanton(propiedad.getUbicacion().getCanton());
        dto.setDistrito(propiedad.getUbicacion().getDistrito());

        model.addAttribute("propiedadDTO", dto);
        model.addAttribute("propiedad", propiedad);
        cargarListasDesplegables(model);
        return "agente/propiedades/formulario";
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                              @Valid @ModelAttribute PropiedadDTO propiedadDTO,
                              BindingResult resultado,
                              Model model) {
        if (resultado.hasErrors()) {
            cargarListasDesplegables(model);
            return "agente/propiedades/formulario";
        }
        propiedadService.actualizar(id, propiedadDTO);
        return "redirect:/agente/propiedades";
    }

    // HU-09: eliminacion con confirmacion (la confirmacion se hace en la vista)
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        propiedadService.eliminar(id);
        return "redirect:/agente/propiedades";
    }

    // HU-19: cambio rapido de estado desde el listado
    @PostMapping("/{id}/estado")
    public String cambiarEstado(@PathVariable Long id, @RequestParam Long idEstado) {
        propiedadService.cambiarEstado(id, idEstado);
        return "redirect:/agente/propiedades";
    }

    private void cargarListasDesplegables(Model model) {
        model.addAttribute("tipos", propiedadService.listarTipos());
        model.addAttribute("estados", propiedadService.listarEstados());
    }
}
