package com.godmonth.util.dozer;

import org.apache.commons.collections4.Transformer;
import org.dozer.Mapper;

public class DozerTransformer<OUT> implements Transformer<Object, OUT> {
	private Mapper mapper;
	private Class<OUT> clazz;

	public DozerTransformer(Mapper mapper, Class<OUT> clazz) {
		this.mapper = mapper;
		this.clazz = clazz;
	}

	@Override
	public OUT transform(Object input) {
		return mapper.map(input, clazz);
	}

}
