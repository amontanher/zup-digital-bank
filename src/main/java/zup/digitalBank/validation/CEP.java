package zup.digitalBank.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CEPValidation.class)
public @interface CEP {
    String message() default "Customer CEP must be with size 9 and formatted.";
    Class<?>[] groups() default{};
    public abstract Class<? extends Payload>[] payload() default{};
}
