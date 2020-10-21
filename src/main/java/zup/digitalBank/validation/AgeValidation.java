package zup.digitalBank.validation;

import zup.digitalBank.models.CustomerPersonalDetail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidation implements ConstraintValidator<Age, CustomerPersonalDetail> {

    @Override
    public void initialize(Age constraintAnnotation) {

    }

    @Override
    public boolean isValid(CustomerPersonalDetail customerPersonalDetail, ConstraintValidatorContext constraintValidatorContext) {
        Period p = Period.between(customerPersonalDetail.getBirthDate(), LocalDate.now());

        return p.getYears() >= 18;
    }
}