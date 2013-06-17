package com.godmonth.util.jackson;

import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author shenyue
 */
public class ObjectMapperFactoryBean extends Jackson2ObjectMapperFactoryBean {
	private boolean camel;

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		if (camel) {
			getObject().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		getObject().setSerializationInclusion(Include.NON_NULL);
	}

	public void setCamel(boolean camel) {
		this.camel = camel;
	}

}
