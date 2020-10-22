package zup.digitalBank.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CEPValidation implements ConstraintValidator<CEP, String> {
    @Override
    public void initialize(CEP constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length() == 9){
            String cepFormatted = String.format("%s%s",s.substring(0,5), s.substring(5));
            if(s.equals(cepFormatted)){
                return true;
            }
        }

        return false;
    }
}