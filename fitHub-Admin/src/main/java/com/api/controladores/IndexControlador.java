package com.api.controladores;

import com.api.dto.UsuarioDTO;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    // Index
    @GetMapping("/")
    public String index() {
        return "index";
    }

    //Registro
    @GetMapping("/register")
    public String registroUsuario(WebRequest request, Model model) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("usuario", usuarioDTO);
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registroUsuario(@ModelAttribute("usuario") @Valid UsuarioDTO accountDto,
                                        BindingResult result, WebRequest request, Errors errors) {
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", accountDto);
        }
        if ((usuarioServicio.findUsuarioByCorreo(accountDto.getCorreo()) == null) && (usuarioServicio.findUsuarioByCedula(accountDto.getCedula()) == null)){
            usuarioServicio.saveUsuario(accountDto);
            return new ModelAndView("index", "user", accountDto);

        }else{
            return new ModelAndView("register", "user", accountDto);
        }

    }

    //Login
    @GetMapping("/login")
    public String loginUsuario(WebRequest request) {
        return "login";
    }
}