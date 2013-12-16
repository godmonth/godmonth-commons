package com.godmonth.util.lock.lockmap.expression;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LockId {
	/**
	 * 表达式
	 * 
	 * @return lockId的值
	 */
	String expression();

}
