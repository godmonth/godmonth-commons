package com.godmonth.util.lock.lockmap.hazelcast;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.ILock;

public class HazelcastLockWrapper implements Lock {

	private static final Logger logger = LoggerFactory.getLogger(HazelcastLockWrapper.class);

	private final ILock lock;
	private String lockId;

	public HazelcastLockWrapper(ILock lock, String lockId) {
		Validate.notNull(lock);
		this.lock = lock;
		this.lockId = lockId;
	}

	@Override
	public void lock() {
		logger.trace("lock acquiring :{}", lockId);
		lock.lock();
		logger.trace("lock acquiried :{}", lockId);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		lock.lockInterruptibly();
	}

	@Override
	public boolean tryLock() {
		return lock.tryLock();
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		logger.trace("lock acquiring :{}", lockId);
		boolean tryLock = lock.tryLock(time, unit);
		if (tryLock) {
			logger.trace("lock acquiried :{}", lockId);
		}
		return tryLock;
	}

	@Override
	public void unlock() {
		if (lock.isLockedByCurrentThread()) {
			logger.trace("lock releasing :{}", lockId);
			lock.unlock();
			logger.trace("lock released :{}", lockId);
		} else {
			logger.trace("lock owner is changed :{}", lockId);
		}
		if (!lock.isLocked()) {
			logger.trace("lock destorying :{}", lockId);
			lock.destroy();
			logger.trace("lock destoried :{}", lockId);
		} else {
			logger.trace("lock keep alive :{}", lockId);
		}
	}

	@Override
	public Condition newCondition() {
		return lock.newCondition();
	}

}
