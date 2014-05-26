package com.godmonth.util.validations;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DecimalScaleValidator implements
		ConstraintValidator<DecimalScale, BigDecimal> {

	private DecimalScale constraintAnnotation;

	@Override
	public void initialize(DecimalScale constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		if (value != null) {
			return value.scale() <= constraintAnnotation.scale();
		} else {
			return true;
		}
	}

}
