package com.godmonth.util.curator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.curator.framework.recipes.shared.SharedValue;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CuratorDateTimeValue implements Mutable<DateTime> {
	private SharedValue sharedValue;
	protected final static DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormat
			.forPattern("yyyy-MM-dd'T'HH:mm:ss");

	private DateTimeFormatter dateTimeFormatter = DEFAULT_DATE_TIME_FORMATTER;

	public void setValue(DateTime value) {
		try {
			sharedValue.setValue(dateTimeFormatter.print(value).getBytes());
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	@Override
	public DateTime getValue() {
		byte[] value = sharedValue.getValue();
		if (ArrayUtils.isNotEmpty(value)) {
			return dateTimeFormatter.parseDateTime(new String(value));
		}
		return null;
	}

	public void setSharedValue(SharedValue sharedValue) {
		this.sharedValue = sharedValue;
	}

	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

}
