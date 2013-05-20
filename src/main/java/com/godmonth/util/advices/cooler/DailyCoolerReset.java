package com.godmonth.util.advices.cooler;

import org.apache.commons.lang3.mutable.Mutable;
import org.joda.time.DateTime;

public class DailyCoolerReset {
	private boolean enableDelete;
	private Mutable<DateTime> mutable;

	public void deleteLastExecution() {
		if (enableDelete) {
			mutable.setValue(null);
		}
	}

	public void setEnableDelete(boolean enableDelete) {
		this.enableDelete = enableDelete;
	}

	public void setMutable(Mutable<DateTime> mutable) {
		this.mutable = mutable;
	}

}
