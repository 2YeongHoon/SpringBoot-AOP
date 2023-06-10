package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Pointcuts {

    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {
    } // pointcut signature

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    } // pointcut signature

    @Pointcut("allOrder() && allService()")
    public void allOrderAndAllService() {
    } // pointcut signature

}
