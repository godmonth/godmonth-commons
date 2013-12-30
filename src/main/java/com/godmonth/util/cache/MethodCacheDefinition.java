package com.godmonth.util.cache;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author shenyue
 */
public class MethodCacheDefinition {
	private String methodName;

	private String cacheKeyExpression;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getCacheKeyExpression() {
		return cacheKeyExpression;
	}

	public void setCacheKeyExpression(String cacheKey) {
		this.cacheKeyExpression = cacheKey;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("methodName", this.methodName)
				.append("cacheKeyExpression", this.cacheKeyExpression).toString();
	}

}
