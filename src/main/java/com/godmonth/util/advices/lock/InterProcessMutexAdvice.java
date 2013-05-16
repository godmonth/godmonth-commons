package com.godmonth.util.advices.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;

public class InterProcessMutexAdvice {
	private InterProcessMutex interProcessMutex;

	public Object cool(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			interProcessMutex.acquire();
			return joinPoint.proceed();
		} finally {
			interProcessMutex.release();
		}
	}

	public void setInterProcessMutex(InterProcessMutex interProcessMutex) {
		this.interProcessMutex = interProcessMutex;
	}

}
