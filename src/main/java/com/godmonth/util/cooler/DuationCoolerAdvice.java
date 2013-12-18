package com.godmonth.util.cooler;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class DuationCoolerAdvice extends CoolerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(DuationCoolerAdvice.class);

	private Duration leastDuation;

	@Override
	public boolean checkCool(DateTime end) {
		DateTime lastDate = lastExecution.getValue();
		logger.trace("lastExecution:{}", lastExecution);
		return lastDate == null || new Duration(lastDate, end).compareTo(leastDuation) > 0;
	}

	@Required
	public void setLeastDuation(Duration leastDuation) {
		this.leastDuation = leastDuation;
	}

}
