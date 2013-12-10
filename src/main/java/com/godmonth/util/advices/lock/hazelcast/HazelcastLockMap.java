package com.godmonth.util.advices.lock.hazelcast;

import java.util.concurrent.locks.Lock;

import com.godmonth.util.advices.lock.lockmap.LockMap;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastLockMap implements LockMap {
	private HazelcastInstance hazelcastInstance;

	@Override
	public Lock getLock(String key) {
		return hazelcastInstance.getLock(key);
	}

	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

}
