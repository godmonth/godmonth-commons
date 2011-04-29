package com.godmonth.util.pressure;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedOperation;

import com.godmonth.util.thread.EndlessTask;

public abstract class Liquor implements Alcohol {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected int threadNum;
	protected ThreadPoolExecutor executorService;
	protected Date startTime;

	protected void stopEs() {
		if (executorService != null) {
			getPressureTask().shutdown();
			getMonitorTask().shutdown();
			executorService.shutdownNow();
			while (true) {
				try {
					executorService.awaitTermination(5, TimeUnit.SECONDS);
					break;
				} catch (Exception e) {
					logger.warn(null, e);
				}
			}

		}
	}

	protected void createEs() {
		stopEs();
		executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	@ManagedOperation
	public void begin() {
		startTime = new Date();
		createEs();
		beforeBegin();

		executorService.submit(getMonitorTask());
		for (int i = 0; i < threadNum; i++) {
			executorService.submit(getPressureTask());
		}
	}

	protected abstract EndlessTask getMonitorTask();

	protected abstract EndlessTask getPressureTask();

	protected void beforeBegin() {
	}

	@ManagedOperation
	public void end() {
		stopEs();
		afterEnd();
	}

	protected void afterEnd() {

	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

}
