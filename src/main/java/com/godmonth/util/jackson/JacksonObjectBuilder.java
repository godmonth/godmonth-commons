package com.godmonth.util.jackson;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author shenyue
 */
public class JacksonObjectBuilder<T> {

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
		InputStream inputStream = null;
		try {
			inputStream = resource.getInputStream();
			return JacksonObjectFactory.createObject(objectMapper, objectType, inputStream);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	public List<T> createObjectList() {
		InputStream inputStream = null;
		try {
			inputStream = resource.getInputStream();
			return JacksonObjectFactory.createObjectList(objectMapper, objectType, inputStream);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
