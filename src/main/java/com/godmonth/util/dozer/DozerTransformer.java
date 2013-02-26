package com.godmonth.util.dozer;

import org.apache.commons.collections15.Transformer;
import org.dozer.Mapper;

public class DozerTransformer<I, O> implements Transformer<I, O> {
	private Mapper mapper;
	private Class<O> clazz;

	public DozerTransformer(Mapper mapper, Class<O> clazz) {
		this.mapper = mapper;
		this.clazz = clazz;
	}

	@Override
	public O transform(I input) {
		return mapper.map(input, clazz);
	}

}
