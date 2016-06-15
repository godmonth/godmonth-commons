package com.godmonth.util.jaxb.adapters.joda.money;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.money.Money;

public class MoneyAdapter extends XmlAdapter<String, Money> {

	public Money unmarshal(String v) throws Exception {
		return Money.parse(v);
	}

	public String marshal(Money v) throws Exception {
		return v.toString();
	}

}
