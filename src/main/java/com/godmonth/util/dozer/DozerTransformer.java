package com.godmonth.util.dozer;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;

import com.google.common.base.Function;

public class DozerTransformer<OUT> implements Transformer<Object, OUT>, Function<Object, OUT> {
	private Mapper mapper;
	private Class<OUT> clazz;
	private final String mapId;

	public DozerTransformer(Mapper mapper, Class<OUT> clazz) {
		this(mapper, clazz, null);
	}

	public DozerTransformer(Mapper mapper, Class<OUT> clazz, String mapId) {
		this.mapper = mapper;
		this.clazz = clazz;
		this.mapId = mapId;
	}

	public OUT transform(Object input) {
		if (StringUtils.isNotBlank(mapId)) {
			return mapper.map(input, clazz, mapId);
		} else {
			return mapper.map(input, clazz);
		}
	}

	@Override
	public OUT apply(Object input) {
		return transform(input);
	}

}
