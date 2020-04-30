package com.api.dto;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class fechaFuturaValidador implements ConstraintValidator<fechaFuturaValidacion, Object>{
    public void initialize(fechaFuturaValidacion constraintAnnotation) {
    	
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
       SesionDTO sesion = (SesionDTO) value;
       Date fecha_actual = new Date();
       return sesion.getFecha().before(fecha_actual);
    }
}