package com.tsystems.javaschool.webshop.controllers.utils;

import com.tsystems.javaschool.webshop.services.api.AccountService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;


/**
 * The type Logging aspect.
 */
@Aspect
public class LoggingAspect {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(LoggingAspect.class);

    /**
     * Debug log before all methods.
     *
     * @param joinPoint the join point
     */
    @Before("execution(* com.tsystems.javaschool.webshop..*(..))")
    public final void logBefore(final JoinPoint joinPoint) {
        LOGGER.debug(joinPoint.toLongString()
                + " with args "
                + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Log controllers.
     *
     * @param joinPoint the join point
     */
    @Before("execution(* com.tsystems.javaschool.webshop.controllers..*(..))")
    public final void logControllers(final JoinPoint joinPoint) {
        LOGGER.info(joinPoint.toLongString()
                + " with args "
                + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Log after throwing.
     *
     * @param joinPoint the join point
     * @param error     the error
     */
    @AfterThrowing(
            pointcut = "execution(* com.tsystems.javaschool.webshop..*(..))",
            throwing= "error")
    public final void logAfterThrowing(final JoinPoint joinPoint, final Throwable error) {

        LOGGER.error(joinPoint.toShortString()
                        + " with args "
                        + Arrays.toString(joinPoint.getArgs())
                        + "thrown exception: " + error.getMessage() + "Stacktrace: " + Arrays.toString(error.getStackTrace()));
    }
}
