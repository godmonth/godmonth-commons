package com.godmonth.util.jackson.converter;

import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class UnixTimestampConverterSerializer extends StdConverter<Date, Long> {

	@Override
	public Long convert(Date value) {
		if (value != null) {
			return value.getTime() / 1000;
		} else {
			return null;
		}
	}

}
