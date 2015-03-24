package com.godmonth.util.dozer;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;

import com.google.common.base.Function;

public class DozerMapperFunction<IN, OUT> implements Function<IN, OUT> {

	private final Mapper mapper;
	private final Class<OUT> clazz;
	private final String mapId;

	public DozerMapperFunction(Mapper mapper, Class<OUT> clazz) {
		this(mapper, clazz, null);
	}

	public DozerMapperFunction(Mapper mapper, Class<OUT> clazz, String mapId) {
		this.mapper = mapper;
		this.clazz = clazz;
		this.mapId = mapId;
	}

	@Override
	public OUT apply(IN input) {
		if (StringUtils.isNotBlank(mapId)) {
			return mapper.map(input, clazz, mapId);
		} else {
			return mapper.map(input, clazz);
		}
	}

}
