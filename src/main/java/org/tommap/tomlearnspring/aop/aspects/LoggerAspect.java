package org.tommap.tomlearnspring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class LoggerAspect {
    private final Logger logger = Logger.getLogger(LoggerAspect.class.getName());

//    @Around("execution(* org.tommap.tomlearnspring.aop.services.*.*(..))")
    @Around("@annotation(org.tommap.tomlearnspring.aop.aspects.LogAspect)")
    public String log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(() -> joinPoint.getSignature().toLongString() + " method execution start");
        Instant start = Instant.now();

        String result = (String) joinPoint.proceed(); // invoke the actual method - wait until method completes - continue remaining logic inside aspect

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info(() -> "Time took to execute the method: " + timeElapsed);
        logger.info(() -> joinPoint.getSignature().toString() + " method execution end");

        return result;
    }

    @AfterThrowing(value = "execution(* org.tommap.tomlearnspring.aop.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.severe(() -> "Exception occurred in " + joinPoint.getSignature().toLongString() + " with message: " + ex.getMessage());
    }

    @AfterReturning(value = "execution(* org.tommap.tomlearnspring.aop.services.*.*(..))", returning = "retVal")
    public void logReturnValue(JoinPoint joinPoint, Object retVal) {
        logger.info(() -> "Method successfully executed: " + joinPoint.getSignature().toLongString() + " with return value: " + retVal.toString());
    }
}
