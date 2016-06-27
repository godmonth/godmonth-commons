package com.godmonth.util.jaxb.adapters.joda.time;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.Days;

public class DaysAdapter extends XmlAdapter<String, Days> {

	@Override
	public Days unmarshal(String v) throws Exception {
		return Days.days(Integer.parseInt(v));
	}

	@Override
	public String marshal(Days v) throws Exception {
		return String.valueOf(v.getDays());
	}

}
