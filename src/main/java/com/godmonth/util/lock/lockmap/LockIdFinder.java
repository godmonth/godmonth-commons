package com.godmonth.util.lock.lockmap;

import org.aspectj.lang.ProceedingJoinPoint;

public interface LockIdFinder {

	String getLockId(ProceedingJoinPoint joinPoint);
}
