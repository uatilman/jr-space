package com.space.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ProdDateValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface ProdDateBetween {
    String message() default "Invalid product date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String after() default "0";

    boolean beforeNow() default true;

}
