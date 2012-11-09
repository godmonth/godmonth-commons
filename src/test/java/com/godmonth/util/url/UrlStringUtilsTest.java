package com.godmonth.util.url;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import com.godmonth.util.url.UrlStringUtils;

public class UrlStringUtilsTest {

	@Test
	public void appendQueryString() throws UnsupportedEncodingException, EncoderException {
		UrlStringUtils.appendQueryString("http://a?fff", Pair.of("aaa", "ppp&bbb"));
	}
}
