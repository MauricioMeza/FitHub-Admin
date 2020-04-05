package com.api.controladores;

import com.api.dto.LoginDTO;
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
        if (!result.hasErrors()) {
            usuarioServicio.saveUsuario(accountDto);
        }

        if (result.hasErrors()) {
            return new ModelAndView("register", "user", accountDto);
        }else{
            return new ModelAndView("index", "user", accountDto);
        }

    }

    //Login
    @GetMapping("/login")
    public String loginUsuario(WebRequest request, Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("usuarioLogin", loginDTO);
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView registroUsuario(@ModelAttribute("usuarioLogin") @Valid LoginDTO loginAccountDto,
                                        BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            //usuarioServicio.saveUsuario(loginAccountDto);
        }

        if (result.hasErrors()) {
            return new ModelAndView("login", "user", loginAccountDto);
        } else {
            return new ModelAndView("index", "user", loginAccountDto);
        }
    }

}
