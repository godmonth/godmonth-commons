package com.godmonth.util.advices.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterProcessMutexAdvice {
	private InterProcessMutex interProcessMutex;

	private static final Logger logger = LoggerFactory.getLogger(InterProcessMutexAdvice.class);

	public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			logger.debug("acquiring lock");
			interProcessMutex.acquire();
			return joinPoint.proceed();
		} finally {
			interProcessMutex.release();
			logger.debug("acquire released");
		}
	}

	public void setInterProcessMutex(InterProcessMutex interProcessMutex) {
		this.interProcessMutex = interProcessMutex;
	}

}
