package com.godmonth.util.cooler;

import org.apache.commons.lang3.mutable.MutableObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.mockito.Mockito;
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
		ProceedingJoinPoint pjp = Mockito.mock(ProceedingJoinPoint.class);

		Mockito.when(pjp.proceed()).thenReturn("aaa");
		// 1
		Assert.assertEquals(dca.cooldown(pjp), "aaa");
		Assert.assertNotNull(mutableObject.getValue());
	}

	@Test
	public void testCooldown2() throws Throwable {
		DuationCoolerAdvice dca = new DuationCoolerAdvice();
		MutableObject<DateTime> mutableObject = new MutableObject<DateTime>();
		dca.setLastExecution(mutableObject);
		dca.setLeastDuation(Duration.standardHours(1));
		Assert.assertTrue(dca.checkCool(new DateTime()));

		mutableObject.setValue(new DateTime());
		Assert.assertFalse(dca.checkCool(new DateTime()));

		mutableObject.setValue(new DateTime().minusHours(2));
		Assert.assertTrue(dca.checkCool(new DateTime()));

	}
}
