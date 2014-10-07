package com.godmonth.util.lock.lockmap.hazelcast;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.ILock;
import com.hazelcast.spi.exception.DistributedObjectDestroyedException;

public class OneTimeHazelcastLockWrapper implements Lock {

	private static final Logger logger = LoggerFactory
			.getLogger(OneTimeHazelcastLockWrapper.class);
	public static final int DEFAULT_MAX_RECURSIVE = 5;
	private final ILock lock;
	private final String lockId;
	private final int maxRecursive;

	public OneTimeHazelcastLockWrapper(ILock lock, String lockId) {
		this(lock, lockId, DEFAULT_MAX_RECURSIVE);
	}

	public OneTimeHazelcastLockWrapper(ILock lock, String lockId,
			int maxRecursive) {
		Validate.notNull(lock);
		Validate.isTrue(maxRecursive > 0);
		this.lock = lock;
		this.lockId = lockId;
		this.maxRecursive = maxRecursive;
	}

	@Override
	public void lock() {
		recursiveLock(0);
	}

	private void recursiveLock(int currentRecursive) {
		if (currentRecursive >= maxRecursive) {
			throw new IllegalStateException("lockId: " + lockId
					+ ", maxRecursive reached" + maxRecursive);
		}
		try {
			logger.trace("lock acquiring :{}, currentRecursive:{}", lockId,
					currentRecursive);
			lock.lock();
			logger.trace("lock acquiried :{}, currentRecursive:{}", lockId,
					currentRecursive);
		} catch (DistributedObjectDestroyedException e) {
			logger.trace("lock destoried by others :{}, currentRecursive:{}",
					lockId, currentRecursive);
			if (currentRecursive + 1 >= maxRecursive) {
				throw e;
			} else {
				recursiveLock(currentRecursive + 1);
			}
		}

	}

	@Override
	public boolean tryLock() {
		logger.trace("try lock acquiring :{}", lockId);
		boolean tryLock = lock.tryLock();
		if (tryLock) {
			logger.trace("try lock acquired :{}", lockId);
		} else {
			logger.trace("try lock failure :{}", lockId);
		}
		return tryLock;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return recursiveTryLock(time, unit, 0);
	}

	private boolean recursiveTryLock(long time, TimeUnit unit,
			int currentRecursive) throws InterruptedException {
		if (currentRecursive >= maxRecursive) {
			throw new IllegalStateException("lockId: " + lockId
					+ ", maxRecursive reached" + maxRecursive);
		}
		boolean result = false;
		try {
			logger.trace("try lock acquiring :{}, currentRecursive:{}", lockId,
					currentRecursive);
			result = lock.tryLock(time, unit);
			if (result) {
				logger.trace("try lock acquired :{}, currentRecursive:{}",
						lockId, currentRecursive);
			} else {
				logger.trace("try lock failure :{}, currentRecursive:{}",
						lockId, currentRecursive);
			}
			return result;
		} catch (DistributedObjectDestroyedException e) {
			logger.trace("lock destroied by others :{}, currentRecursive:{}",
					lockId, currentRecursive);
			if (currentRecursive + 1 >= maxRecursive) {
				throw e;
			} else {
				return recursiveTryLock(time, unit, currentRecursive + 1);
			}
		}

	}

	@Override
	public void unlock() {
		if (lock.isLockedByCurrentThread()) {
			logger.trace("lock destorying :{}", lockId);
			lock.destroy();
			logger.trace("lock destoried :{}", lockId);
		} else {
			logger.trace("lock owner is changed :{}", lockId);
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Condition newCondition() {
		throw new UnsupportedOperationException();
	}

}
