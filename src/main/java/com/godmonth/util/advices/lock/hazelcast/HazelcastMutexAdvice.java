package com.godmonth.util.advices.lock.hazelcast;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;

/**
 * 
 * @author shenyue
 * 
 */
public abstract class HazelcastMutexAdvice {

	private static final Logger logger = LoggerFactory.getLogger(HazelcastMutexAdvice.class);

	public static final int DEFAULT_WAIT_SECOND = 5;

	private HazelcastInstance hazelcastInstance;
	private int waitSecond = DEFAULT_WAIT_SECOND;

	public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
		Lock lock = null;
		try {
			String lockId = getLockId(joinPoint);
			lock = hazelcastInstance.getLock(lockId);
			logger.debug("lock acquiring :{}", lockId);
			if (lock.tryLock(waitSecond, TimeUnit.SECONDS)) {
				return joinPoint.proceed();
			}
			logger.debug("lock acquiried :{}", lockId);
			throw new TimeoutException("wait timeout exceeded");
		} finally {
			if (lock != null) {
				logger.debug("lock released");
				lock.unlock();
			}

		}
	}

	protected abstract String getLockId(ProceedingJoinPoint joinPoint);

	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

	public void setWaitSecond(int waitSecond) {
		this.waitSecond = waitSecond;
	}

}
