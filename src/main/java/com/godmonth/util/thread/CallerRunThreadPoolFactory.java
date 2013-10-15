package com.godmonth.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.MoreExecutors;

public class CallerRunThreadPoolFactory {
	public static ExecutorService create(int threadNum) {
		if (threadNum > 1) {
			int actualThreadNum = threadNum - 1;
			return new ThreadPoolExecutor(actualThreadNum, actualThreadNum, 0L, TimeUnit.MILLISECONDS,
					new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
		} else {
			return MoreExecutors.sameThreadExecutor();
		}
	}
}
