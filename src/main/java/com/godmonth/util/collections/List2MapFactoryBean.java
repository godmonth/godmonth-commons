package com.godmonth.util.collections;

import java.util.List;
import java.util.Map;

import jodd.bean.BeanUtil;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class List2MapFactoryBean<KEY, VALUE> implements FactoryBean<Map<KEY, VALUE>>, InitializingBean {
	private List<VALUE> sourceList;
	private String propertyPath;
	protected Map<KEY, VALUE> map;

	@Override
	public void afterPropertiesSet() throws Exception {
		map = list2Map(sourceList, propertyPath);
	}

	public static <KEY, VALUE> Map<KEY, VALUE> list2Map(List<VALUE> sourceList, final String propertyName) {
		Function<VALUE, KEY> function = new Function<VALUE, KEY>() {

			@Override
			public KEY apply(VALUE input) {
				return (KEY) BeanUtil.getProperty(input, propertyName);
			}

		};
		Map<KEY, VALUE> map = Maps.uniqueIndex(sourceList, function);
		return map;
	}

	public void setSourceList(List<VALUE> sourceList) {
		this.sourceList = sourceList;
	}

	@Override
	public Map<KEY, VALUE> getObject() throws Exception {
		return map;
	}

	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
