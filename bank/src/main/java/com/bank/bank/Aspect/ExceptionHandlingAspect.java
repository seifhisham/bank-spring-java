package com.bank.bank.Aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    @Pointcut("within(com.bank.bank.Controller.*)")
    void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object handleException(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            Map<String, Integer> res = new HashMap<>();
            res.put(" The Process Was Not Completed Successfully.", 404);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }
}
