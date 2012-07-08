package com.godmonth.util.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author shenyue
 */
public final class UrlStringUtils {
	private UrlStringUtils() {
	}

	public static String appendQueryString(String url, Pair<String, ?>... params) {
		StringBuilder sb = new StringBuilder(url);
		for (Pair<String, ?> pair : params) {
			String connect = StringUtils.contains(sb, "?") ? "&" : "?";
			sb.append(connect + pair.getKey() + "=" + pair.getValue());
		}
		return sb.toString();
	}
}
