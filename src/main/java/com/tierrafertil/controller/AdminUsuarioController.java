package com.tierrafertil.controller;

import com.tierrafertil.repository.RolRepository;
import com.tierrafertil.security.UserDetailsImpl;
import com.tierrafertil.service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    public AdminUsuarioController(UsuarioService usuarioService, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
    }

    // HU-04: visualizar los usuarios registrados
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("roles", rolRepository.findAll());
        return "admin/usuarios/lista";
    }

    // HU-05: asignar/cambiar el rol de un usuario
    @PostMapping("/{id}/rol")
    public String cambiarRol(@PathVariable Long id,
                              @RequestParam Long idRol,
                              @AuthenticationPrincipal UserDetailsImpl usuarioActual,
                              Model model) {
        // Evita que un admin se quite su propio rol de ADMIN y se bloquee a si mismo del panel
        if (usuarioActual.getUsuario().getIdUsuario().equals(id)) {
            model.addAttribute("usuarios", usuarioService.listarTodos());
            model.addAttribute("roles", rolRepository.findAll());
            model.addAttribute("errorRol", "No podes cambiar tu propio rol desde aqui.");
            return "admin/usuarios/lista";
        }
        usuarioService.cambiarRol(id, idRol);
        return "redirect:/admin/usuarios";
    }
}
