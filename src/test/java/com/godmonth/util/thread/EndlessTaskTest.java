package com.godmonth.util.thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndlessTaskTest {
	@Test(enabled = true)
	public void testRunSleep10() throws InterruptedException {
		EndlessTask et = new EndlessTask(new Runnable() {

			@Override
			public void run() {
			}
		}, 10);
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.execute(et);
		Thread.sleep(100);
		List<Runnable> shutdownNow = es.shutdownNow();
		Assert.assertTrue(shutdownNow.isEmpty());
		System.out.println(es.isTerminated());
		boolean awaitTermination = es.awaitTermination(1000, TimeUnit.SECONDS);
		Assert.assertTrue(awaitTermination);
	}

	@Test(enabled = false)
	public void testRunNoSleep() throws InterruptedException {
		EndlessTask et = new EndlessTask(new Runnable() {

			@Override
			public void run() {

			}
		}, 0);
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.execute(et);

		Thread.sleep(100);

		List<Runnable> shutdownNow = es.shutdownNow();
		Assert.assertTrue(shutdownNow.isEmpty());
		boolean awaitTermination = es.awaitTermination(1000, TimeUnit.SECONDS);
		Assert.assertTrue(awaitTermination);
	}
}
