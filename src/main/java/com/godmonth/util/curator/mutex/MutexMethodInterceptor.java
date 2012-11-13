package com.godmonth.util.curator.mutex;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

/**
 * @author shenyue
 */
public class MutexMethodInterceptor implements MethodInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MutexMethodInterceptor.class);
	private InterProcessSemaphoreMutex interProcessSemaphoreMutex;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			logger.trace("start acquiring lock");
			interProcessSemaphoreMutex.acquire();
			logger.trace("lock acquired,execute method:{}", invocation.getMethod().getName());

			return invocation.proceed();
		} finally {
			logger.trace("release lock");
			interProcessSemaphoreMutex.release();
			logger.trace("lock released");
		}
	}

	public void setInterProcessSemaphoreMutex(InterProcessSemaphoreMutex interProcessSemaphoreMutex) {
		this.interProcessSemaphoreMutex = interProcessSemaphoreMutex;
	}

}
