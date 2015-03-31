package com.godmonth.util.jaxb.adapters.joda;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.Interval;

public class IntervalAdapter extends XmlAdapter<String, Interval> {

	public Interval unmarshal(String v) throws Exception {
		return new Interval(v);
	}

	public String marshal(Interval v) throws Exception {
		return v.toString();
	}

}
