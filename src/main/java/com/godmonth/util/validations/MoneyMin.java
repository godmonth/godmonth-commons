package com.godmonth.util.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoneyMinValidator.class)
@Documented
public @interface MoneyMin {
	String message() default "{com.godmonth.util.validations.MoneyMin.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean inclusive() default true;

	String value();

	String[] currencyUnits() default {};
}
