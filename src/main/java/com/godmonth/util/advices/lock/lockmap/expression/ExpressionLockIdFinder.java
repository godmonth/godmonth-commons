package com.godmonth.util.advices.lock.lockmap.expression;

import java.lang.reflect.Method;

import org.apache.commons.lang3.Validate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.godmonth.util.advices.lock.lockmap.LockIdFinder;

public class ExpressionLockIdFinder implements LockIdFinder {
	private ExpressionParser parser = new SpelExpressionParser();

	public String getLockId(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		LockId annotation = method.getAnnotation(LockId.class);
		Validate.notNull(annotation, "annotation not found");
		String expression = annotation.expression();
		Expression exp = parser.parseExpression(expression);
		String lockId = (String) exp.getValue(joinPoint.getArgs());
		Validate.notNull(lockId, "lock id is null");
		return lockId;
	}
}
