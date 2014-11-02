package com.godmonth.util.cooler;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class DuationCoolerAdvice extends CoolerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(DuationCoolerAdvice.class);

	private Duration leastDuation;

	@Override
	public boolean checkCool(DateTime end) {
		DateTime start = lastExecution.getValue();
		if (start == null) {
			logger.trace("start DateTime is null, return true");
			return true;
		}
		Interval interval = new Interval(start, end);
		Duration challengeDuration = interval.toDuration();
		boolean testResult = challengeDuration.isLongerThan(leastDuation);
		logger.trace("interval:{}, challenge:{}, cooling:{}, test result:{}", interval, challengeDuration,
				leastDuation, testResult);
		return testResult;
	}

	@Required
	public void setLeastDuation(Duration leastDuation) {
		this.leastDuation = leastDuation;
	}

}
