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

@CrossOrigin(origins = { "http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class IndexControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    //Registro
    @GetMapping("/register")
    public String registroUsuario(WebRequest request, Model model){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        return "registro";
    }

    @PostMapping("/register")
    public String registroUsuario(@RequestBody @Valid UsuarioDTO accountDto, BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            if ((usuarioServicio.getUserByCorreo(accountDto.getCorreo()) == null) && (usuarioServicio.getUserByCedula(accountDto.getCedula()) == null)){
                usuarioServicio.addUsuario(accountDto);
                return "Usuario Creado";
            }else if(!(usuarioServicio.getUserByCedula(accountDto.getCedula())==null)){
        		return "Ya existe esa cedula en BD";
        	}else{
        		return "Ya existe ese correo en BD";
        	}
        }else{
            return "Error de Validacion";
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