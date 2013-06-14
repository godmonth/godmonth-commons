package com.godmonth.util.thread;

public interface StoppableTask extends Runnable {
	void shutdown();
}
