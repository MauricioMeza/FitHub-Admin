package com.api.dto;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class contrasenaIgualValidador implements ConstraintValidator<contrasenaIgualValidacion, Object>{

    private String campo;
    private String campoRepetido;

    public void initialize(contrasenaIgualValidacion constraintAnnotation) {
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UsuarioDTO user = (UsuarioDTO) value;
       return user.getContrasena().equals(user.getContrasenaRepetir());
    }
}