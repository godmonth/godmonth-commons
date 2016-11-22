package com.godmonth.util.jackson;

import java.io.IOException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * only single bean
 * 
 * @author shenyue
 */
public class JacksonObjectFactoryBean<T> implements FactoryBean<T>, InitializingBean {

	private Resource jsonResource;

	private ObjectMapper objectMapper;

	private Class<T> objectType;

	private T object;

	@Override
	public void afterPropertiesSet() throws JsonParseException, JsonMappingException, IOException {
		JacksonObjectBuilder<T> builder = new JacksonObjectBuilder<T>();
		builder.setObjectMapper(objectMapper);
		builder.setObjectType(objectType);
		builder.setResource(jsonResource);
		object = (T) builder.createObject();
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

	@Required
	public void setJsonResource(Resource jsonResource) {
		this.jsonResource = jsonResource;
	}

	@Required
	public void setObjectType(Class<T> objectType) {
		this.objectType = objectType;
	}

}
