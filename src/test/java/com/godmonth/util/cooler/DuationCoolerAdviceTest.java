package com.godmonth.util.cooler;

import java.util.Date;

import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.easymock.EasyMock;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DuationCoolerAdviceTest {

	@Test
	public void testCooldown() throws Throwable {
		DuationCoolerAdvice dca = new DuationCoolerAdvice();
		MutableObject<DateTime> mutableObject = new MutableObject<DateTime>();
		dca.setLastExecution(mutableObject);
		dca.setSkipValue("abc");
		dca.setLeastDuation(Duration.standardHours(1));
		ProceedingJoinPoint pjp = EasyMock.createMock(ProceedingJoinPoint.class);
		EasyMock.expect(pjp.proceed()).andReturn("aaa");
		EasyMock.replay(pjp);
		// 1
		Assert.assertEquals(dca.cooldown(pjp), "aaa");
		System.out.println(mutableObject.getValue());
		Assert.assertNotNull(mutableObject.getValue());
		// 2
		Assert.assertEquals(dca.cooldown(pjp), "abc");

		EasyMock.verify(pjp);
	}

	public static void main(String[] args) {
		Duration standardMinutes = Duration.standardMinutes(10);
		Date d = new Date();
		Date d2 = DateUtils.addMinutes(d, 10);
//		d2 = DateUtils.addSeconds(d2, 1);
		Duration duration = new Duration(d.getTime(), d2.getTime());
		System.out.println(duration);
		System.out.println(standardMinutes);
		int compareTo = duration.compareTo(standardMinutes);

		System.out.println(compareTo);
	}
}
