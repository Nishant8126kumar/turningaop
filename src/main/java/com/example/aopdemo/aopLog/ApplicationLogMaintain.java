package com.example.aopdemo.aopLog;

import com.example.aopdemo.AopdemoApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLogMaintain {

    final static Logger LOGGER = LoggerFactory.getLogger(AopdemoApplication.class);

    @Pointcut(value = "execution(* com.example.aopdemo.controllers.VehicleController.*(..))")
    public void studentcontrollerPointcut() {
    }

    @Pointcut(value = "execution(* com.example.aopdemo.services.VehicleService.*(..))")
    public void studentServicePointcut() {
    }

    @Before(value ="studentcontrollerPointcut()" )
    public void studentControllerPointcutLog(JoinPoint joinPoint) {
        LOGGER.info("Action Start on this class "+joinPoint.getSignature().getDeclaringTypeName()+"");
        LOGGER.info("Action Start on This " + joinPoint.getSignature().getName() + " Method");
    }

    @Before(value ="studentServicePointcut()" )
    public void studentServicePointcutLog(JoinPoint joinPoint) {
        LOGGER.info("Action Start on this class "+joinPoint.getSignature().getDeclaringTypeName()+"");
        LOGGER.info("Action Start on This " + joinPoint.getSignature().getName() + " Method");
    }
}
//within(@org.springframework.stereotype.Repository *)
