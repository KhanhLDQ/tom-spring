package org.tommap.tomlearnspring.eazy_school.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LoggerAspect {
    @Around("execution(* org.tommap.tomlearnspring.eazy_school..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("{} method execution start", joinPoint.getSignature().toLongString());
        Instant start = Instant.now();

        Object returnObj = joinPoint.proceed();

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.debug("Time to execute {} method: {}", joinPoint.getSignature().toLongString(), timeElapsed);
        log.debug("{} method execution end", joinPoint.getSignature().toLongString());

        return returnObj;
    }

    @AfterThrowing(value = "execution(* org.tommap.tomlearnspring.eazy_school..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("Exception happened from {} due to: {}", joinPoint.getSignature().toLongString(), ex.getMessage());
    }
}
