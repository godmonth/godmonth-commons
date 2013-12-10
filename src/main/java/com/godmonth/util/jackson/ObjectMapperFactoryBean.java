package com.godmonth.util.jackson;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author shenyue
 */
public class ObjectMapperFactoryBean extends Jackson2ObjectMapperFactoryBean {
	private boolean camel;
	private List<Mixin> mixins;

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		if (camel) {
			getObject()
					.setPropertyNamingStrategy(
							PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		getObject().setSerializationInclusion(Include.NON_NULL);
		if (CollectionUtils.isNotEmpty(mixins)) {
			for (Mixin mixin : mixins) {
				getObject().addMixInAnnotations(mixin.getTarget(),
						mixin.getMixinSource());
			}
		}

	}

	public void setCamel(boolean camel) {
		this.camel = camel;
	}

	public void setMixins(List<Mixin> mixins) {
		this.mixins = mixins;
	}

	public static class Mixin {
		private Class<?> target;
		private Class<?> mixinSource;

		public Class<?> getTarget() {
			return target;
		}

		public void setTarget(Class<?> target) {
			this.target = target;
		}

		public Class<?> getMixinSource() {
			return mixinSource;
		}

		public void setMixinSource(Class<?> mixinSource) {
			this.mixinSource = mixinSource;
		}

	}
}
