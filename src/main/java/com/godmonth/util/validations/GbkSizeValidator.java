package com.godmonth.util.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.Charsets;

/**
 * @author shenyue
 */
public class GbkSizeValidator implements ConstraintValidator<GbkSize, String> {

	private GbkSize constraintAnnotation;

	@Override
	public void initialize(GbkSize constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			return value.getBytes(Charsets.toCharset("gbk")).length <= constraintAnnotation.max();
		} else {
			return true;
		}
	}
}
