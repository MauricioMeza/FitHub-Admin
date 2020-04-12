package com.api.controladores;

import com.api.dto.LoginDTO;
import com.api.dto.UsuarioDTO;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> registroUsuario(@RequestBody @Valid UsuarioDTO accountDto, BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            if ((usuarioServicio.getUserByCorreo(accountDto.getCorreo()) == null) && (usuarioServicio.getUserByCedula(accountDto.getCedula()) == null)){
                usuarioServicio.addUsuario(accountDto);
                return ResponseEntity.accepted().body("Usuario creado");
            } else if(!(usuarioServicio.getUserByCedula(accountDto.getCedula())==null)) {
            	return ResponseEntity.badRequest().body("Ya existe esa cédula en BD");
        	} else {
        		return ResponseEntity.badRequest().body("Ya existe ese correo en BD");
        	}
        } else {
        	return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
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
    public ResponseEntity<String> registroUsuario(@RequestBody @Valid LoginDTO loginAccountDto, BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            return ResponseEntity.ok().body("Usuario validado");
        }else{
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
    }

}