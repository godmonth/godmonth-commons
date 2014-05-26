package com.godmonth.util.validations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * gbk字符的字节长度
 * 
 * @author shenyue
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GbkSizeValidator.class)
@Documented
public @interface GbkSize {
	String message() default "{com.godmonth.util.validations.GbkSize.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int max();
}
