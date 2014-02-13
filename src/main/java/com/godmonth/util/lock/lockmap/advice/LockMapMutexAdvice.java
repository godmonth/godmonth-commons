package com.godmonth.util.lock.lockmap.advice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.godmonth.util.lock.lockmap.LockIdFinder;
import com.godmonth.util.lock.lockmap.LockMap;

/**
 * 
 * @author shenyue
 * 
 */
public class LockMapMutexAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LockMapMutexAdvice.class);

	public static final int DEFAULT_WAIT_SECOND = 5;

	private LockMap lockMap;
	private LockIdFinder lockIdFinder;
	private int waitSecond = DEFAULT_WAIT_SECOND;

	public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
		Lock lock = null;
		String lockId = null;
		try {
			lockId = lockIdFinder.getLockId(joinPoint);
			lock = lockMap.getLock(lockId);
			logger.debug("lock acquiring :{}", lockId);
			if (lock.tryLock(waitSecond, TimeUnit.SECONDS)) {
				logger.debug("lock acquiried :{}", lockId);
				return joinPoint.proceed();
			}
			throw new TimeoutException("wait timeout exceeded");
		} finally {
			if (lock != null) {
				logger.debug("lock released :{}", lockId);
				lock.unlock();
			}
		}
	}

	public void setWaitSecond(int waitSecond) {
		this.waitSecond = waitSecond;
	}

	public void setLockMap(LockMap lockMap) {
		this.lockMap = lockMap;
	}

	public void setLockIdFinder(LockIdFinder lockIdFinder) {
		this.lockIdFinder = lockIdFinder;
	}

}
