package com.api.controladores;

import com.api.FitHubAdminApplication;
import com.api.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitHubAdminApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControladorIT {

    @LocalServerPort
    private int port;

    @Test
    public void registroUsuario(){
        String url = "http://localhost:" + port + "/register";

        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        Usuario usuario = new Usuario("usuarioTest",
                "usuariotest@micorreo.com",
                "12345655",
                "112233",
                "USER");

        HttpEntity entity = new HttpEntity<Usuario>(usuario, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "Usuario creado";
    }
}