package com.godmonth.util.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

/**
 * @author shenyue
 */
public class ServletInputStreamWrapper extends ServletInputStream {

	private final InputStream inputStream;

	public ServletInputStreamWrapper(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}

	public int read() throws IOException {
		return inputStream.read();
	}

	public int read(byte[] b) throws IOException {
		return inputStream.read(b);
	}

	public int read(byte[] b, int off, int len) throws IOException {
		return inputStream.read(b, off, len);
	}

	public long skip(long n) throws IOException {
		return inputStream.skip(n);
	}

	public int available() throws IOException {
		return inputStream.available();
	}

	public void close() throws IOException {
		inputStream.close();
	}

	public void mark(int readlimit) {
		inputStream.mark(readlimit);
	}

	public void reset() throws IOException {
		inputStream.reset();
	}

	public boolean markSupported() {
		return inputStream.markSupported();
	}

}
