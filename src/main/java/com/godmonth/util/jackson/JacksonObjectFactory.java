package com.godmonth.util.jackson;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * @author shenyue
 */
public class JacksonObjectFactory {

	public static <T> T createObject(ObjectMapper objectMapper, Class<T> objectType, InputStream inputStream) {
		try {
			return objectMapper.readValue(inputStream, objectType);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	public static <T> List<T> createObjectList(ObjectMapper objectMapper, Class<T> objectType,
			InputStream inputStream) {
		try {
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(List.class, objectType);
			return objectMapper.readValue(inputStream, collectionType);
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

}
