package com.api.controladores;


import com.api.modelos.Usuario;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registro")
public class RegistroControllador {

    /*@Autowired
    UsuarioServicio servicio;

    @PostMapping
    public ResponseEntity<Usuario> registerUser(@RequestBody Usuario user){

        Usuario newUser = servicio.addUser(user);

        if(newUser == null){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.get_id()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/user")
    public String logTest(){
        return "it work";
    }*/
}
