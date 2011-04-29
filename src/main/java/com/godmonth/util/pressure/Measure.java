package com.godmonth.util.pressure;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.math.stat.descriptive.SummaryStatistics;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource
public class Measure implements Runnable {
	private AtomicInteger readCount = new AtomicInteger();
	private AtomicInteger writeCount = new AtomicInteger();
	private AtomicInteger failureCount = new AtomicInteger();
	private int readPeriod;
	private int writePeriod;
	private int failurePeriod;
	private SummaryStatistics readPsAvg = new SummaryStatistics();
	private SummaryStatistics respAvg = new SummaryStatistics();

	public void run() {
		readPeriod = readCount.getAndSet(0);
		writePeriod = writeCount.getAndSet(0);
		failurePeriod = failureCount.getAndSet(0);
	}

	@ManagedOperation
	public void reset() {
		readCount.set(0);
		writeCount.set(0);
		failureCount.set(0);
		readPeriod = 0;
		writePeriod = 0;
		failurePeriod = 0;
		readPsAvg = new SummaryStatistics();
		respAvg = new SummaryStatistics();

	}

	public AtomicInteger getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(AtomicInteger failureCount) {
		this.failureCount = failureCount;
	}

	public AtomicInteger getReadCount() {
		return readCount;
	}

	public void setReadCount(AtomicInteger readCount) {
		this.readCount = readCount;
	}

	@ManagedAttribute
	public int getReadPeriod() {
		return readPeriod;
	}

	@ManagedAttribute
	public int getWritePeriod() {
		return writePeriod;
	}

	@ManagedAttribute
	public int getFailurePeriod() {
		return failurePeriod;
	}

	public SummaryStatistics getReadPsAvg() {
		return readPsAvg;
	}

	public SummaryStatistics getRespAvg() {
		return respAvg;
	}

	@ManagedAttribute
	public int getRespMean() {
		return (int) respAvg.getMean();
	}

	public AtomicInteger getWriteCount() {
		return writeCount;
	}

	public void setWriteCount(AtomicInteger writeCount) {
		this.writeCount = writeCount;
	}

}
