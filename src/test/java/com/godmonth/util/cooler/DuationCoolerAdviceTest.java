package com.godmonth.util.cooler;

import org.apache.commons.lang3.mutable.MutableObject;
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

	
}
