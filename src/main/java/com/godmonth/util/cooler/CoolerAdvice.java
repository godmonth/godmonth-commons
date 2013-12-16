package com.godmonth.util.cooler;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.mutable.Mutable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public abstract class CoolerAdvice {
	protected Mutable<DateTime> lastExecution;

	private static final Logger logger = LoggerFactory.getLogger(CoolerAdvice.class);
	private Object skipValue;

	public Object cooldown(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
		DateTime now = new DateTime();
		if (checkCool(now)) {
			logger.debug("pass");
			lastExecution.setValue(now);
			return joinPoint.proceed();
		} else {
			logger.debug("skip");
			return skipValue;
		}
	}

	public abstract boolean checkCool(@NotNull DateTime end);

	@Required
	public void setLastExecution(Mutable<DateTime> lastExecution) {
		this.lastExecution = lastExecution;
	}

	public void setSkipValue(Object skipValue) {
		this.skipValue = skipValue;
	}

}
