package com.godmonth.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

/**
 * @deprecated use dozer instead
 * 
 * @author shenyue
 * 
 */
@Deprecated
public class BeanCopyUtils {
	private BeanCopyUtils() {
	}

	public static void copyProperties(Object from, Object to, String... propertyNames) {
		for (String propertyName : propertyNames) {
			try {
				PropertyUtils.setProperty(to, propertyName, PropertyUtils.getProperty(from, propertyName));
			} catch (Exception e) {
				throw new ContextedRuntimeException(e);
			}
		}
	}

	public static <T> T copyProperties(T from, String... propertyNames) {
		try {
			@SuppressWarnings("unchecked")
			T to = (T) from.getClass().newInstance();
			copyProperties(from, to, propertyNames);
			return to;
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}
}
