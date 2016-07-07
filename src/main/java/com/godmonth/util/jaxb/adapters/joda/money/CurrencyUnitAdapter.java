package com.godmonth.util.jaxb.adapters.joda.money;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.money.CurrencyUnit;

public class CurrencyUnitAdapter extends XmlAdapter<String, CurrencyUnit> {
	public CurrencyUnit unmarshal(String v) throws Exception {
		return CurrencyUnit.of(v);
	}

	public String marshal(CurrencyUnit v) throws Exception {
		return v.getCode();
	}

}
