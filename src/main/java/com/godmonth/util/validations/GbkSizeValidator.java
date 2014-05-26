package com.godmonth.util.validations;

import java.nio.charset.Charset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.Charsets;

/**
 * gbk字符长度校验
 * 
 * @author shenyue
 */
public class GbkSizeValidator implements ConstraintValidator<GbkSize, String> {

	private GbkSize constraintAnnotation;

	@Override
	public void initialize(GbkSize constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	private static final Charset gbkCharset = Charsets.toCharset("gbk");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			return value.getBytes(gbkCharset).length <= constraintAnnotation.max();
		} else {
			return true;
		}
	}
}
