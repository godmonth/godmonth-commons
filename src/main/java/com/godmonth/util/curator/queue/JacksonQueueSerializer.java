package com.godmonth.util.curator.queue;

import java.io.IOException;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.curator.framework.recipes.queue.QueueSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonQueueSerializer<T> implements QueueSerializer<T> {
	private ObjectMapper objectMapper;
	private Class<T> clazz;

	public JacksonQueueSerializer() {
	}

	public JacksonQueueSerializer(ObjectMapper objectMapper, Class<T> clazz) {
		this.objectMapper = objectMapper;
		this.clazz = clazz;
	}

	@Override
	public byte[] serialize(T item) {
		try {
			return objectMapper.writeValueAsBytes(item);
		} catch (JsonProcessingException e) {
			throw new ContextedRuntimeException(e);
		}
	}

	@Override
	public T deserialize(byte[] bytes) {
		try {
			return objectMapper.readValue(bytes, clazz);
		} catch (IOException e) {
			throw new ContextedRuntimeException(e);
		}
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

}
