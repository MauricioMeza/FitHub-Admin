package com.api.seguridad;

import com.api.modelos.Usuario;
import com.api.modelos.Instructor;
import com.api.repositorios.InstructorRepositorio;
import com.api.repositorios.UsuarioRepositorio;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	@Autowired
    UsuarioRepositorio usuarioRepositorio;
	@Autowired
    InstructorRepositorio instructorRepositorio;
    

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UsuarioRepositorio usuarioRepositorio) {
        super(authenticationManager);
        this.usuarioRepositorio = usuarioRepositorio;
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
        Instructor instructor;
        UserPrincipal principal;
        Usuario usuario;
        List<GrantedAuthority> authorityList = new ArrayList<>();

        if(token != null){

            String userName = JWT .require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            if(userName != null){
            	if(userName.contains("@Fithub.com")) {
            		//instructor = instructorRepositorio.findByCorreo(userName);
            		authorityList.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
            		//principal = new UserPrincipal(instructor);
            	} else{
            		//usuario = usuarioRepositorio.findByCorreo(userName);
            		authorityList.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
            		//principal = new UserPrincipal(instructor);
            	}

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName,null,authorityList);
                return auth;

            }
            return null;
        }
        return null;
    }
}