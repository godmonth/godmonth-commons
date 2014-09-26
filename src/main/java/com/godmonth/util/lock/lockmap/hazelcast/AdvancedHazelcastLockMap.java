package com.godmonth.util.lock.lockmap.hazelcast;

import java.util.concurrent.locks.Lock;

import com.godmonth.util.lock.lockmap.LockMap;
import com.hazelcast.core.HazelcastInstance;

public class AdvancedHazelcastLockMap implements LockMap {
	private HazelcastInstance hazelcastInstance;

	@Override
	public Lock getLock(String key) {
		return new HazelcastLockWrapper(hazelcastInstance.getLock(key), key);
	}

	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}
}
