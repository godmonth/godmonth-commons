package com.godmonth.util.json;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author shenyue
 */
public class JacksonObjectFactory<T> {
	private Resource resource;
	private ObjectMapper objectMapper;
	private Class<T> objectType;

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setObjectType(Class<T> objectType) {
		this.objectType = objectType;
	}

	public T createObject() {
		try {
			return objectMapper.readValue(resource.getInputStream(), objectType);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
