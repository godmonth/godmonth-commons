package com.godmonth.util.cooler;

import org.apache.commons.lang3.mutable.MutableObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.joda.time.DateTime;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DailyCoolerAdviceTest {
	@Test
	public void testCooldown() throws Throwable {
		DailyCoolerAdvice dca = new DailyCoolerAdvice();
		MutableObject<DateTime> mutableObject = new MutableObject<DateTime>();
		dca.setLastExecution(mutableObject);
		dca.setSkipValue("abc");

		ProceedingJoinPoint pjp = Mockito.mock(ProceedingJoinPoint.class);
		Mockito.when(pjp.proceed()).thenReturn("aaa");
		// 1
		Assert.assertEquals(dca.cooldown(pjp), "aaa");
		Assert.assertNotNull(mutableObject.getValue());

	}
}
