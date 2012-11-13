package com.godmonth.util.curator.mutex;

import java.util.Calendar;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import com.netflix.curator.framework.CuratorFramework;

/**
 * @author shenyue
 */
public class ExecutionDaily implements MethodInterceptor {
	private CuratorFramework client;
	private String path;
	private static final FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyyMMdd");

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (notExecuted()) {
			Object proceed = invocation.proceed();
			afterExecuted();
			return proceed;
		}
		return null;
	}

	public boolean notExecuted() throws Exception {
		byte[] forPath = client.getData().forPath(path);
		if (ArrayUtils.isNotEmpty(forPath)) {
			Date exectedDate = DateUtils.parseDate(new String(forPath), "yyyyMMdd");
			return exectedDate.compareTo(getDate()) <= 0;
		}
		return true;
	}

	public void afterExecuted() throws Exception {
		client.setData().forPath(path, fastDateFormat.format(getDate()).getBytes());
	}

	protected Date getDate() {
		return DateUtils.truncate(new Date(), Calendar.DATE);
	}

	public void setClient(CuratorFramework client) {
		this.client = client;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
