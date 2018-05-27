package com.tmobile.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TModileLogger {

    private final Log log = LogFactory.getLog(this.getClass());

    @Value("${tmobile.log.enabled}")
    private static boolean enabled;

    @Pointcut("execution(* com.tmobile.service.*.*(..))")
    public static boolean servicePointcut() {
        return enabled;
    }

    @Pointcut("execution(* com.tmobile.controller.*.*(..))")
    public static boolean controllerPointcut() {
        return enabled;
    }

    @Around("servicePointcut()")
    public void logService(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = MethodSignature.class.cast(pjp.getSignature()).getMethod().getName();
        log.debug("Start method " + methodName + "with parameters: " + pjp.getArgs());
        Object result = pjp.proceed();
        log.debug("Stop method " + methodName + "with parameters: " + result);
    }

    @Around("controllerPointcut()")
    public void logController(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = MethodSignature.class.cast(pjp.getSignature()).getMethod().getName();
        log.debug("Request method " + methodName + "with parameters: " + pjp.getArgs());
        Object result = pjp.proceed();
        log.debug("Response method " + methodName + "with parameters: " + result);
    }
}
