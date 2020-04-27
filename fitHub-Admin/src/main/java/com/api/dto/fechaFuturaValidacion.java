package com.api.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = fechaFuturaValidador.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface fechaFuturaValidacion {

    String message() default "La fecha introducida no es una fecha futura";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}