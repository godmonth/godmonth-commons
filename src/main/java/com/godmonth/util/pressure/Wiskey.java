package com.godmonth.util.pressure;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.godmonth.util.thread.EndlessTask;

@ManagedResource
public class Wiskey extends Liquor {
	private Measure measure;
	private EndlessTask monitorTask;
	private EndlessTask pressureTask;

	@Override
	protected EndlessTask getMonitorTask() {
		return monitorTask;
	}

	@Override
	protected EndlessTask getPressureTask() {
		return pressureTask;
	}

	@Override
	protected void beforeBegin() {
		measure.reset();
		monitorTask.ready();
		pressureTask.ready();
	}

	@Override
	protected void afterEnd() {
		monitorTask.shutdown();
		pressureTask.shutdown();
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public void setMonitorTask(EndlessTask monitorTask) {
		this.monitorTask = monitorTask;
	}

	public void setPressureTask(EndlessTask pressureTask) {
		this.pressureTask = pressureTask;
	}

}
