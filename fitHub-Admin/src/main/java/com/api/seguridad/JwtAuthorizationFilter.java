package com.api.seguridad;

import com.api.modelos.Usuario;
import com.api.repositorios.UsuarioRepositorio;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    UsuarioRepositorio usuarioRepositorio;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UsuarioRepositorio usuarioRepositorio) {
        super(authenticationManager);
        this.usuarioRepositorio = this.usuarioRepositorio;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request){

        String token = request.getHeader(JwtProperties.HEADER_STRING);

        if(token != null){

            String userName = JWT .require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            if(userName != null){
                Usuario usuario = usuarioRepositorio.findByCorreo(userName);
                UserPrincipal principal = new UserPrincipal(usuario);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName,null,principal.getAuthorities());
                return auth;

            }
            return null;
        }
        return null;
    }
}