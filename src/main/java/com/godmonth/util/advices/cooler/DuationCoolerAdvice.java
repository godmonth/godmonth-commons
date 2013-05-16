package com.godmonth.util.advices.cooler;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Required;

public class DuationCoolerAdvice extends CoolerAdvice {
	private Duration leastDuation;

	@Override
	public boolean checkCool(DateTime end) {
		DateTime lastDate = lastExecution.getValue();
		return lastDate == null || new Duration(lastDate, end).compareTo(leastDuation) > 0;
	}

	@Required
	public void setLeastDuation(Duration leastDuation) {
		this.leastDuation = leastDuation;
	}

}
