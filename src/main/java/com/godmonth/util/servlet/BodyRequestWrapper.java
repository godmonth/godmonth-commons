package com.godmonth.util.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

/**
 * @author shenyue
 */
public class BodyRequestWrapper extends HttpServletRequestWrapper {

	private boolean wrapped;
	private byte[] byteArray;

	public BodyRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		if (!(request instanceof BodyRequestWrapper)
				&& ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod()))) {
			InputStream inputStream = request.getInputStream();
			byteArray = IOUtils.toByteArray(inputStream);
			IOUtils.closeQuietly(inputStream);
			wrapped = true;
		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (wrapped) {
			return new ServletInputStreamWrapper(new ByteArrayInputStream(byteArray));
		} else {
			return super.getInputStream();
		}
	}

}
