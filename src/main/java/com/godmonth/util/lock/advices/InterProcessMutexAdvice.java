package com.godmonth.util.lock.advices;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterProcessMutexAdvice {
	private InterProcessMutex interProcessMutex;

	private static final Logger logger = LoggerFactory.getLogger(InterProcessMutexAdvice.class);

	public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			logger.trace("lock acquiring");
			interProcessMutex.acquire();
			logger.trace("lock acquiried");
			return joinPoint.proceed();
		} finally {
			logger.debug("acquire releasing");
			interProcessMutex.release();
			logger.debug("acquire released");
		}
	}

	public void setInterProcessMutex(InterProcessMutex interProcessMutex) {
		this.interProcessMutex = interProcessMutex;
	}

}
