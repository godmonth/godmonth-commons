package com.godmonth.util.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndlessTask implements StoppableTask {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private Runnable runnable;
	private int sleepTime;
	private boolean breakOnException;
	private boolean shutdown;

	public EndlessTask(Runnable runnable) {
		this(runnable, 0, false);
	}

	public EndlessTask(Runnable runnable, int sleepTime) {
		this(runnable, sleepTime, false);
	}

	public EndlessTask(Runnable runnable, int sleepTime,
			boolean breakOnException) {
		this.runnable = runnable;
		this.sleepTime = sleepTime;
		this.breakOnException = breakOnException;
	}

	public void ready() {
		shutdown = false;
	}

	public void shutdown() {
		shutdown = true;
	}

	public void run() {
		try {
			while (true) {
				if (shutdown) {
					break;
				}

				try {
					runnable.run();
				} catch (RuntimeException e) {
					if (breakOnException) {
						throw e;
					} else {
						logger.warn(null, e);
					}
				}

				if (Thread.currentThread().isInterrupted()) {
					logger.info("interrupted");
					break;
				}

				if (sleepTime > 0) {
					if (shutdown) {
						break;
					}
					Thread.sleep(sleepTime);
				}

			}
		} catch (InterruptedException e) {
			logger.info(e.getMessage());
		}

	}
}
