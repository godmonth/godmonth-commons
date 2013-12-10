package com.godmonth.util.advices.expression;

import org.aspectj.lang.ProceedingJoinPoint;

public interface LockIdFinder {

	String getLockId(ProceedingJoinPoint joinPoint);
}
