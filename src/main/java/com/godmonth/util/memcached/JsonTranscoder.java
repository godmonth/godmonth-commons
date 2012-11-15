package com.godmonth.util.memcached;

import net.rubyeye.xmemcached.transcoders.CachedData;
import net.rubyeye.xmemcached.transcoders.CompressionMode;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.transcoders.Transcoder;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTranscoder<T> implements Transcoder<T> {
	private static StringTranscoder stringTranscoder = new StringTranscoder();
	private ObjectMapper objectMapper;
	private Class<T> clazz;

	@Override
	public CachedData encode(T o) {
		try {
			return stringTranscoder.encode(objectMapper.writeValueAsString(o));
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	@Override
	public T decode(CachedData d) {
		try {
			return objectMapper.readValue(stringTranscoder.decode(d), clazz);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	public void setPackZeros(boolean packZeros) {
		stringTranscoder.setPackZeros(packZeros);
	}

	public boolean isPackZeros() {
		return stringTranscoder.isPackZeros();
	}

	public boolean isPrimitiveAsString() {
		return stringTranscoder.isPrimitiveAsString();
	}

	public void setPrimitiveAsString(boolean primitiveAsString) {
		stringTranscoder.setPrimitiveAsString(primitiveAsString);
	}

	public void setCompressionThreshold(int to) {
		stringTranscoder.setCompressionThreshold(to);
	}

	public void setCharset(String to) {
		stringTranscoder.setCharset(to);
	}

	public int hashCode() {
		return stringTranscoder.hashCode();
	}

	public boolean equals(Object obj) {
		return stringTranscoder.equals(obj);
	}

	public String toString() {
		return stringTranscoder.toString();
	}

	@Override
	public void setCompressionMode(CompressionMode compressMode) {
		stringTranscoder.setCompressionMode(compressMode);
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
