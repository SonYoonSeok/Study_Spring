package sonny.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAopHelperCLS {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAopHelperCLS.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping() {

    }

    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("=====================AspectJ TEST  : Before Logging Start=====================");
        LOGGER.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }

    @Around("GetMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("=====================AspectJ TEST  : Around Logging Start=====================");
        try {
            Object result = joinPoint.proceed();
            LOGGER.info("=====================AspectJ TEST  : Around Logging END=====================");
            return result;
        } catch (Exception e) {
            LOGGER.error("=====================AspectJ Around Exception=====================");
            LOGGER.error(e.toString());
            return null;
        }
    }
}
