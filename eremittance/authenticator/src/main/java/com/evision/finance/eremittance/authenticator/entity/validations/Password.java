package com.evision.finance.eremittance.authenticator.entity.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
public @interface Password {
    String message() default "Password complexity not met";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}