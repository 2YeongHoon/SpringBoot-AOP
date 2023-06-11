package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.order.aop.Pointcuts.allOrderAndAllService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[트랜젝션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜젝션 종료] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백]", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[리소스 릴리즈]", joinPoint.getSignature());
        }
    }

    @Before("hello.aop.order.aop.Pointcuts.allOrderAndAllService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[트랜젝션 시작] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrderAndAllService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[Result] {}, return {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.allOrderAndAllService()", throwing = "ex")
    public void doThrow(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {}, return {}", joinPoint.getSignature(), ex);
    }

    @After(value = "hello.aop.order.aop.Pointcuts.allOrderAndAllService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
