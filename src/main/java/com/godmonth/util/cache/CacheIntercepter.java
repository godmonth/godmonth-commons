package com.godmonth.util.cache;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 无法区分overload方法
 * 
 * @author shenyue
 */
public class CacheIntercepter implements MethodInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(CacheIntercepter.class);

	private static final ExpressionParser parser = new SpelExpressionParser();

	private List<MethodCacheDefinition> methodCacheDefinitions;
	private Cache cache;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		for (MethodCacheDefinition methodCacheDefinition : methodCacheDefinitions) {
			if (methodCacheDefinition.getMethodName().equals(invocation.getMethod().getName())) {
				Expression parseExpression = parser.parseExpression(methodCacheDefinition.getCacheKeyExpression());
				String cacheKey = (String) parseExpression.getValue(invocation.getArguments());
				ValueWrapper valueWrapper = cache.get(cacheKey);
				if (valueWrapper != null) {
					logger.trace("key found: {}", cacheKey);
					return valueWrapper.get();
				} else {
					logger.trace("key notfound: {}", cacheKey);
					Object proceed = invocation.proceed();
					if (proceed != null) {
						cache.put(cacheKey, proceed);
						logger.trace("key put: {}", cacheKey);
					}
					return proceed;
				}
			}
		}
		return invocation.proceed();
	}

	@Required
	public void setMethodCacheDefinitions(List<MethodCacheDefinition> methodCacheDefinitions) {
		this.methodCacheDefinitions = methodCacheDefinitions;
	}

	@Required
	public void setCache(Cache cache) {
		this.cache = cache;
	}

}
