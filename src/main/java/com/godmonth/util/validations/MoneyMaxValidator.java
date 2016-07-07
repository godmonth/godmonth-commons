package com.godmonth.util.validations;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;
import org.joda.money.Money;

public class MoneyMaxValidator implements ConstraintValidator<MoneyMax, Money> {

	private BigDecimal maxValue;
	private boolean inclusive;

	private String[] currencyUnits;

	@Override
	public void initialize(MoneyMax maxValue) {
		this.maxValue = new BigDecimal(maxValue.value());

		this.inclusive = maxValue.inclusive();

		this.currencyUnits = maxValue.currencyUnits();

	}

	@Override
	public boolean isValid(Money value, ConstraintValidatorContext context) {
		// null values are valid
		if (value == null) {
			return true;
		}
		boolean passed = false;
		try {
			int comparisonResult = value.getAmount().compareTo(maxValue);
			passed = inclusive ? comparisonResult <= 0 : comparisonResult < 0;
			if (!passed) {
				return passed;
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
		if (ArrayUtils.isNotEmpty(currencyUnits)) {
			return ArrayUtils.contains(currencyUnits, value.getCurrencyUnit().getCode());
		}
		return passed;
	}

}
