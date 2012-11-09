package com.godmonth.util.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author shenyue
 */
public final class UrlStringUtils {

	private UrlStringUtils() {
	}

	public static URI appendQueryString(String url, Pair<String, ?>... params) {
		UriComponentsBuilder ucb = UriComponentsBuilder.fromHttpUrl(url);
		for (Pair<String, ?> pair : params) {
			ucb = ucb.queryParam(pair.getKey(), pair.getValue());
		}
		return ucb.build().encode().toUri();
	}

	public static String getDomainFromUrl(String urlStr) {
		try {
			URL url = new URL(urlStr);
			return url.getHost();
		} catch (MalformedURLException e) {
			return null;
		}

	}

}
