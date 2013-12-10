package com.godmonth.util.advices.lock.lockmap;

import org.aspectj.lang.ProceedingJoinPoint;

public interface LockIdFinder {

	String getLockId(ProceedingJoinPoint joinPoint);
}
