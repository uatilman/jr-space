package com.space.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ProdDateValidator implements ConstraintValidator<ProdDateBetween, Date> {

    private Date after;
    private Boolean beforeNow;

    @Override
    public void initialize(ProdDateBetween constraintAnnotation) {
        this.after = new Date(Long.parseLong(constraintAnnotation.after()));
        this.beforeNow = constraintAnnotation.beforeNow();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        Date now = new Date();
        now.setYear(now.getYear() + 1000);
        return value != null && value.after(after) && (!beforeNow || value.before(now));
    }
}
