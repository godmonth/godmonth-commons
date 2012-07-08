package com.godmonth.util.json;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author shenyue
 */
public class JacksonObjectFactoryBean<T> implements FactoryBean<T>, InitializingBean {
	private String json;
	private Resource jsonResource;
	private ObjectMapper objectMapper;
	private Class<T> objectType;
	private TypeReference typeReference;
	private T object;
	private boolean singleton = true;

	@Override
	public void afterPropertiesSet() throws JsonParseException, JsonMappingException, IOException {
		if (json == null && jsonResource != null) {
			json = IOUtils.toString(jsonResource.getInputStream(), "utf-8");
		}
		Validate.notBlank(json, "json is blank");
		if (singleton) {
			if (typeReference != null) {
				object = objectMapper.readValue(json, typeReference);
			} else {
				object = objectMapper.readValue(json, objectType);
			}
		}
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setObjectType(Class<T> objectType) {
		this.objectType = objectType;
	}

	@Override
	public T getObject() throws Exception {
		if (singleton) {
			return object;
		} else {
			if (typeReference != null) {
				return objectMapper.readValue(json, typeReference);
			} else {
				return objectMapper.readValue(json, objectType);
			}
		}
	}

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setJsonResource(Resource jsonResource) {
		this.jsonResource = jsonResource;
	}

	public void setTypeReference(TypeReference typeReference) {
		this.typeReference = typeReference;
	}

}
