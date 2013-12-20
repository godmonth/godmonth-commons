package com.godmonth.util.cooler;

import org.apache.commons.lang3.mutable.MutableObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.easymock.EasyMock;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.godmonth.util.cooler.DailyCoolerAdvice;

public class DailyCoolerAdviceTest {
	@Test
	public void testCooldown() throws Throwable {
		DailyCoolerAdvice dca = new DailyCoolerAdvice();
		MutableObject<DateTime> mutableObject = new MutableObject<DateTime>();
		dca.setLastExecution(mutableObject);
		dca.setSkipValue("abc");

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
}
