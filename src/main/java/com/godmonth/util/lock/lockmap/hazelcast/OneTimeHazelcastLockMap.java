package com.godmonth.util.lock.lockmap.hazelcast;

import java.util.concurrent.locks.Lock;

import com.godmonth.util.lock.lockmap.LockMap;
import com.hazelcast.core.HazelcastInstance;

public class OneTimeHazelcastLockMap implements LockMap {
	private HazelcastInstance hazelcastInstance;

	public OneTimeHazelcastLockMap() {
	}

	public OneTimeHazelcastLockMap(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

	@Override
	public Lock getLock(String key) {
		return new OneTimeHazelcastLockWrapper(hazelcastInstance.getLock(key),
				key);
	}

	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}
}
