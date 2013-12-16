package com.godmonth.util.lock.advices;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorAcquireLockAdvice {
	private InterProcessMutex interProcessMutex;

	private static final Logger logger = LoggerFactory
			.getLogger(CuratorAcquireLockAdvice.class);

	private Integer acquireTimeoutSecond;

	public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
		boolean acquire = false;
		try {
			logger.debug("acquiring lock");
			if (acquireTimeoutSecond != null) {
				acquire = interProcessMutex.acquire(acquireTimeoutSecond,
						TimeUnit.SECONDS);
			} else {
				interProcessMutex.acquire();
				acquire = true;
			}
			if (acquire) {
				return joinPoint.proceed();
			} else {
				throw new TimeoutException("acquiring lock timeout");
			}
		} finally {
			if (acquire) {
				interProcessMutex.release();
				logger.debug("acquire released");
			}

		}
	}

	public void setInterProcessMutex(InterProcessMutex interProcessMutex) {
		this.interProcessMutex = interProcessMutex;
	}

	public void setAcquireTimeoutSecond(int acquireTimeoutSecond) {
		this.acquireTimeoutSecond = acquireTimeoutSecond;
	}

}
