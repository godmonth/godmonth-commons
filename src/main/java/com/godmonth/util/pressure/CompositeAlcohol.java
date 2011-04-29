package com.godmonth.util.pressure;

import java.util.List;

public class CompositeAlcohol implements Alcohol {
	private List<Alcohol> elements;

	public void setElements(List<Alcohol> elements) {
		this.elements = elements;
	}

	@Override
	public void begin() {
		for (Alcohol alcohol : elements) {
			alcohol.begin();
		}
	}

	@Override
	public void end() {
		for (Alcohol alcohol : elements) {
			alcohol.end();
		}
	}

}
