package com.godmonth.util.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author shenyue
 */
public final class UrlStringUtils {

	private UrlStringUtils() {
	}

	public static URI appendQueryString(String url, Map<String, ?> params) {
		List<Pair<String, ?>> paramsList = new ArrayList<Pair<String, ?>>();
		for (Map.Entry<String, ?> entry : params.entrySet()) {
			paramsList.add(Pair.of(entry.getKey(), entry.getValue()));
		}
		return appendQueryString(url, paramsList);
	}

	public static URI appendQueryString(String url, Pair<String, ?>... params) {
		return appendQueryString(url, Arrays.asList(params));
	}

	public static URI appendQueryString(String url, List<Pair<String, ?>> params) {
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
