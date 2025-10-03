package cn.violetgarden.blog.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BodyAspect {
    @Before("within(cn.violetgarden.blog.controller.ControllerView) || within(cn.violetgarden.blog.controller.ControllerManager)")
    public void handleControllerMethod(JoinPoint joinPoint) throws Throwable {
        System.out.println( ((MethodSignature)joinPoint.getSignature()).getMethod().getName()+": "+Arrays.toString(joinPoint.getArgs()) );
    }

    @AfterReturning(
        value = "within(cn.violetgarden.blog.controller.ControllerView) || within(cn.violetgarden.blog.controller.ControllerManager)",
        returning = "result"
    )
    public void handleControllerMethod(JoinPoint joinPoint, Object result) throws Throwable {
        System.out.println( ((MethodSignature)joinPoint.getSignature()).getMethod().getName()+": "+result );
    }
}
