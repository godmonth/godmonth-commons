package com.godmonth.util.curator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.curator.framework.recipes.locks.InterProcessMutex;
import com.netflix.curator.framework.recipes.shared.SharedValue;

/**
 * @author shenyue
 */
public class ClusterInsure implements MethodInterceptor {
	private SharedValue sharedValue;
	private InterProcessMutex interProcessMutex;

	private static final Logger logger = LoggerFactory.getLogger("ubs.stats");
	private static final String DATE_FORMAT = "yyyy-MM-dd:HH:mm:ss";

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			interProcessMutex.acquire();
			Date lastDate = getLastDate();
			if (lastDate == null || DateUtils.truncatedCompareTo(new Date(), getLastDate(), Calendar.DATE) > 0) {
				setLastDate(new Date());
				return invocation.proceed();
			} else {
				logger.info("skip");
			}
			return null;
		} finally {
			interProcessMutex.release();
		}
	}

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
	private static FastDateFormat fastDateFormat = FastDateFormat.getInstance(DATE_FORMAT);

	private void setLastDate(Date date) {
		try {
			sharedValue.setValue(fastDateFormat.format(date).getBytes());
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	private Date getLastDate() {
		byte[] value = sharedValue.getValue();
		if (ArrayUtils.isNotEmpty(value)) {
			synchronized (simpleDateFormat) {
				try {
					return simpleDateFormat.parse(new String(value));
				} catch (ParseException e) {
					throw new ContextedRuntimeException(e);
				}
			}
		}
		return null;
	}

	public void setSharedValue(SharedValue sharedValue) {
		this.sharedValue = sharedValue;
	}

	public void setInterProcessMutex(InterProcessMutex interProcessMutex) {
		this.interProcessMutex = interProcessMutex;
	}

}
