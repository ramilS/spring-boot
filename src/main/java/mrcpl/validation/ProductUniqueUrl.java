package mrcpl.validation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = { ProductUniqueUrlValidator.class })
@Documented
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface ProductUniqueUrl {
    String message() default "There is already product with this url!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
