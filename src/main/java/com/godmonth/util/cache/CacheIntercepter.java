package com.godmonth.util.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.rubyeye.xmemcached.MemcachedClient;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 不区分overload方法
 * 
 * @author shenyue
 */
public class CacheIntercepter implements MethodInterceptor {
	public static final TypeReference MethodCacheDefinitionTypeReference = new TypeReference<List<MethodCacheDefinition>>() {
	};

	private static final Logger logger = LoggerFactory.getLogger(CacheIntercepter.class);
	private static final ExpressionParser parser = new SpelExpressionParser();

	private List<MethodCacheDefinition> methodCacheDefinitions;
	private MemcachedClient memcachedClient;
	private Pair<Integer, TimeUnit> expired = Pair.of(0, TimeUnit.SECONDS);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		for (MethodCacheDefinition methodCacheDefinition : methodCacheDefinitions) {
			Expression parseExpression = parser.parseExpression(methodCacheDefinition.getCacheKeyExpression());
			String cacheKey = (String) parseExpression.getValue(invocation.getArguments());
			if (methodCacheDefinition.getMethodName().equals(invocation.getMethod().getName())) {
				switch (methodCacheDefinition.getCacheBehavior()) {
				case delete:
					Object proceed = invocation.proceed();
					logger.trace("memcachedClient.delete({})", cacheKey);
					memcachedClient.delete(cacheKey);
					return proceed;
				case putWhenNull:
					Object object = memcachedClient.get(cacheKey);
					logger.trace("memcachedClient.get({})", cacheKey);
					if (object == null) {
						object = invocation.proceed();
						memcachedClient.set(cacheKey, (int) expired.getRight().toSeconds(expired.getLeft()), object);
						logger.trace("memcachedClient.set({})", cacheKey);
					}
					return object;
				default:
					return invocation.proceed();
				}
			}
		}
		return invocation.proceed();
	}

	@Required
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setExpired(Pair<Integer, TimeUnit> expired) {
		this.expired = expired;
	}

	@Required
	public void setMethodCacheDefinitions(List<MethodCacheDefinition> methodCacheDefinitions) {
		this.methodCacheDefinitions = methodCacheDefinitions;
	}

}
