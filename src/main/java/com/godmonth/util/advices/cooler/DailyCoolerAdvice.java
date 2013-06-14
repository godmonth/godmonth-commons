package com.godmonth.util.advices.cooler;

import java.util.Calendar;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

public class DailyCoolerAdvice extends CoolerAdvice {
	private int wakenHour;

	public boolean checkCool( DateTime end) {
		DateTime lastDate = lastExecution.getValue();
		return end.getHourOfDay() >= wakenHour
				&& (lastDate == null || DateUtils.truncatedCompareTo(end.toDate(), lastDate.toDate(), Calendar.DATE) > 0);
	}

	public void setWakenHour(@Min(0) int wakenHour) {
		this.wakenHour = wakenHour;
	}

}
