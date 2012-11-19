package com.godmonth.util.jackson;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author shenyue
 */
public class ObjectMapperFactoryBean implements FactoryBean<ObjectMapper>, InitializingBean {
	private ObjectMapper objectMapper;
	private boolean camel;
	private boolean singleton = true;
	private boolean prettyPrint;

	@Override
	public void afterPropertiesSet() {
		objectMapper = new ObjectMapper();
		if (camel) {
			objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	@Override
	public ObjectMapper getObject() throws Exception {
		return objectMapper;
	}

	@Override
	public Class<?> getObjectType() {
		return ObjectMapper.class;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

	public void setCamel(boolean camel) {
		this.camel = camel;
	}

	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

}
