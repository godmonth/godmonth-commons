package com.godmonth.util.cooler;

import java.util.Calendar;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DailyCoolerAdvice extends CoolerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(DailyCoolerAdvice.class);
	private int wakenHour;

	public boolean checkCool(DateTime end) {
		DateTime lastDate = lastExecution.getValue();
		logger.trace("lastExecution:{},lastDate:{},wakenHour:{}", lastExecution, lastDate, wakenHour);

		return end.getHourOfDay() >= wakenHour
				&& (lastDate == null || DateUtils.truncatedCompareTo(end.toDate(), lastDate.toDate(), Calendar.DATE) > 0);
	}

	public void setWakenHour(@Min(0) int wakenHour) {
		this.wakenHour = wakenHour;
	}

}
