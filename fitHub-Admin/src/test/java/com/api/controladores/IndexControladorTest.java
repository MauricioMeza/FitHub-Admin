package com.api.controladores;

import com.api.modelos.Usuario;
import com.api.repositorios.UsuarioRepositorio;
import com.api.seguridad.UserPrincipalDetailsService;
import com.api.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WithMockUser(username = "user1", password = "secret1")
@EnableAutoConfiguration(exclude = WebSecurityEnablerConfiguration.class)
@WebMvcTest(IndexControlador.class)
class IndexControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    @MockBean
    private UsuarioServicio usuarioServicio;

    @Test
    public void registroUsuario() throws Exception {

        Usuario mockUsuario = new Usuario("usuarioTest",
                "usuariotest@micorreo.com",
                "12345655",
                "112233",
                "USER");

        String usuario = "{\"nombre\":\"usuarioTest\",\"correo\":\"usuariotest@micorreo.com\",\"cedula\":\"12345633\",\"contrasena\":\"112233\",\"role\":\"USER\"}";

        Mockito.when(usuarioServicio.addUser(Mockito.any(Usuario.class))).thenReturn(mockUsuario);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").
                accept(MediaType.APPLICATION_JSON).content(usuario).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    void loginUsuario() {
    }
}