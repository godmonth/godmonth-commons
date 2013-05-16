package com.godmonth.util.curator.mutex;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.recipes.atomic.PromotedToLock;
import org.apache.curator.framework.recipes.atomic.PromotedToLock.Builder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class PromotedToLockFactoryBean implements FactoryBean<PromotedToLock>, InitializingBean {
	private String lockPath;
	private RetryPolicy retryPolicy;
	private Integer timeoutSceond;
	private PromotedToLock promotedToLock;

	@Override
	public void afterPropertiesSet() throws Exception {
		Builder builder = PromotedToLock.builder().lockPath(lockPath);
		if (retryPolicy != null) {
			builder = builder.retryPolicy(retryPolicy);
		}
		if (timeoutSceond != null && timeoutSceond > 0) {
			builder.timeout(timeoutSceond, TimeUnit.SECONDS);
		}
		promotedToLock = builder.build();
	}

	@Override
	public PromotedToLock getObject() throws Exception {
		return promotedToLock;
	}

	@Override
	public Class<?> getObjectType() {
		return PromotedToLock.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setLockPath(String lockPath) {
		this.lockPath = lockPath;
	}

	public void setRetryPolicy(RetryPolicy retryPolicy) {
		this.retryPolicy = retryPolicy;
	}

	public void setTimeoutSceond(Integer timeoutSceond) {
		this.timeoutSceond = timeoutSceond;
	}

}
