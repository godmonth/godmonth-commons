package com.godmonth.util.collections;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

public class NotBlankPredicate implements Predicate<CharSequence> {

	@Override
	public boolean evaluate(CharSequence object) {
		return StringUtils.isNotBlank(object);
	}

}
