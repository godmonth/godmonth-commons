	package com.godmonth.util;

import org.apache.commons.lang3.StringUtils;

public class StringConverter {
	private StringConverter() {

	}

	public static String dbColumn2BeanProperty(String column) {
		char[] charArray = column.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '_' && i < charArray.length - 1) {
				charArray[i + 1] = Character.toUpperCase(charArray[i + 1]);
			}
		}
		return StringUtils.remove(String.valueOf(charArray), '_');
	}

}
