package com.godmonth.util.advices.cooler;

import java.util.Calendar;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

public class DailyCoolerAdvice extends CoolerAdvice {

	public boolean checkCool(DateTime end) {
		DateTime lastDate = lastExecution.getValue();
		return lastDate == null || DateUtils.truncatedCompareTo(end.toDate(), lastDate.toDate(), Calendar.DATE) > 0;
	}
}
