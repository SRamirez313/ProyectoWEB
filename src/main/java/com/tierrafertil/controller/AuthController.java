package com.tierrafertil.controller;

import com.tierrafertil.dto.RegistroDTO;
import com.tierrafertil.service.CorreoYaRegistradoException;
import com.tierrafertil.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        if (!model.containsAttribute("registroDTO")) {
            model.addAttribute("registroDTO", new RegistroDTO());
        }
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@Valid @ModelAttribute RegistroDTO registroDTO,
                                    BindingResult resultado,
                                    Model model) {
        if (resultado.hasErrors()) {
            return "auth/registro";
        }

        try {
            usuarioService.registrar(registroDTO);
        } catch (CorreoYaRegistradoException ex) {
            model.addAttribute("errorCorreo", ex.getMessage());
            return "auth/registro";
        }

        model.addAttribute("registroExitoso", true);
        model.addAttribute("registroDTO", new RegistroDTO());
        return "auth/registro";
    }
}
