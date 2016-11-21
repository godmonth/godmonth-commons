package com.godmonth.util.jackson;

import java.util.List;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

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

	public List<T> createObjectList() {
		try {
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(List.class, objectType);
			return objectMapper.readValue(resource.getInputStream(), collectionType);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
