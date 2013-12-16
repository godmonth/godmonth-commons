package com.godmonth.util.lock.lockmap.vm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.godmonth.util.lock.lockmap.LockMap;

public class VmLockMap implements LockMap {
	@Override
	public Lock getLock(String key) {
		return new ReentrantLock();
	}

}
