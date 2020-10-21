package zup.digitalBank.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidation.class)
public @interface Age {
    String message() default "Customer must be over 18 years old";
    Class<?>[] groups() default{};
    public abstract Class<? extends Payload>[] payload() default{};
}
