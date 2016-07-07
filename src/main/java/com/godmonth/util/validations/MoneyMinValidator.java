package com.godmonth.util.validations;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;
import org.joda.money.Money;

public class MoneyMinValidator implements ConstraintValidator<MoneyMin, Money> {

	private BigDecimal minValue;
	private boolean inclusive;
	private String[] currencyUnits;

	@Override
	public void initialize(MoneyMin minValue) {
		this.minValue = new BigDecimal(minValue.value());

		this.inclusive = minValue.inclusive();

		this.currencyUnits = minValue.currencyUnits();

	}

	@Override
	public boolean isValid(Money value, ConstraintValidatorContext context) {
		// null values are valid
		if (value == null) {
			return true;
		}
		boolean passed = false;

		try {
			int comparisonResult = value.getAmount().compareTo(minValue);
			passed = inclusive ? comparisonResult >= 0 : comparisonResult > 0;
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
