package com.godmonth.util.jackson;

import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * @author shenyue
 */
public class JacksonObjectFactoryBean<T> implements FactoryBean<T>, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(JacksonObjectFactoryBean.class);

	private String json;

	private Resource jsonResource;

	private ObjectMapper objectMapper;

	private Class<T> objectType;

	private T object;

	private Class<? extends Collection<T>> collectionType;

	@Override
	public void afterPropertiesSet() throws JsonParseException, JsonMappingException, IOException {
		if (json == null && jsonResource != null) {
			json = IOUtils.toString(jsonResource.getInputStream(), "utf-8");
		}
		Validate.notBlank(json, "json is blank");
		logger.trace("jsonResource:{},content:{}", jsonResource.getDescription(), json);
		object = initialValue();
	}

	private T initialValue() throws JsonParseException, JsonMappingException, IOException {
		if (collectionType != null) {
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			CollectionLikeType collectionLikeType = typeFactory.constructCollectionLikeType(collectionType, objectType);
			return objectMapper.readValue(json, collectionLikeType);
		} else {
			return objectMapper.readValue(json, objectType);
		}
	}

	@Override
	public T getObject() throws Exception {
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Required
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setJsonResource(Resource jsonResource) {
		this.jsonResource = jsonResource;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Required
	public void setObjectType(Class<T> objectType) {
		this.objectType = objectType;
	}

	public void setCollectionType(Class<? extends Collection<T>> collectionType) {
		this.collectionType = collectionType;
	}

}
