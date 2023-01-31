package tp.spring.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Order(1)
public class Spectateur {

	@Pointcut("execution(* tp.spring.orchestre.*.jouer(..))")
	public void intercept() {
		
	}

	@Before("intercept()")
	public void beforeSpectateur() {
		System.out.println("before spectateur");
	}
	
	//@AfterReturning(pointcut = "intercept()")
	void applaudir() {
		System.out.println("bravo");
	}
	
	//@AfterThrowing("intercept()")
	void huer() {
		System.out.println("bouhh");
	}
	
	@After("intercept()")
	void after() {
		System.out.println("after spectateur");
	}
}
