package com.godmonth.util.dozer;

import org.apache.commons.collections.Transformer;
import org.dozer.Mapper;

public class DozerTransformer implements Transformer {
	private Mapper mapper;
	private Class<?> clazz;

	public DozerTransformer(Mapper mapper, Class<?> clazz) {
		this.mapper = mapper;
		this.clazz = clazz;
	}

	@Override
	public Object transform(Object input) {
		return mapper.map(input, clazz);
	}

}
