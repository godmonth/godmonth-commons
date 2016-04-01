package com.godmonth.util.jackson.converter;

import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class UnixTimestampConverterDeserializer extends StdConverter<Long, Date> {

	@Override
	public Date convert(Long value) {
		if (value != null) {
			long timestamp = value * 1000;
			return new Date(timestamp);
		} else {
			return null;
		}
	}

}
