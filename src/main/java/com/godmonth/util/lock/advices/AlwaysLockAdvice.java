package com.godmonth.util.lock.advices;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class AlwaysLockAdvice implements InitializingBean, DisposableBean {
	private InterProcessMutex interProcessMutex;

	private static final Logger logger = LoggerFactory.getLogger(AlwaysLockAdvice.class);

	public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
		if (interProcessMutex.isAcquiredInThisProcess()) {
			logger.debug("grant processing");
			return joinPoint.proceed();
		} else {
			return null;
		}

	}

	public void setInterProcessMutex(InterProcessMutex interProcessMutex) {
		this.interProcessMutex = interProcessMutex;
	}

	@Override
	public void destroy() throws Exception {
		interProcessMutex.release();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		interProcessMutex.acquire();
	}

}
