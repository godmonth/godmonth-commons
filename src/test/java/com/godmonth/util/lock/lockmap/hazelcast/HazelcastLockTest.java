package com.godmonth.util.lock.lockmap.hazelcast;

import org.testng.annotations.Test;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;

public class HazelcastLockTest {
	@Test(enabled = false)
	public void lock() throws InterruptedException {

		Config cfg = new Config();
		final HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				ILock lock = instance.getLock("abc");
				lock.lock();
				System.out.println("thread 2 got lock");
				lock.lock();
				System.out.println("thread 2 got deep lock");
				lock.unlock();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.unlock();
				System.out.println("thread 2 released lock");

			}
		};
		new Thread(runnable).start();
		Thread.sleep(100);
		ILock lock = instance.getLock("abc");
		lock.lock();
		System.out.println("thread 1 got lock");

		Thread.sleep(10000l);

		lock.unlock();
		System.out.println("thread 1 released lock");
	}
}
