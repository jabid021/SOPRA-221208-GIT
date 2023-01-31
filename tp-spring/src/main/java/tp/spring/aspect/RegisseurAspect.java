package tp.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
//order pour les befores plus petit en premier 
//inverse pour les afters (@After @AfterReturning @AfterThrowing)
public class RegisseurAspect {
	
	@Pointcut("execution(* tp.spring.orchestre.*.jouer(..))")
	public void intercept() {
		
	}
	
	@Before("intercept()")
	public void eteindreLumiere() {
		System.out.println("Le régisseur eteint les lumières");
	}
	
	@After("intercept()")
	public void allumerLumiere() {
		System.out.println("Le régisseur allume les lumières");
	}
	
	@Around("intercept()")
	public void around(ProceedingJoinPoint pJP)throws Throwable{
		System.out.println("replacement");
		
		pJP.proceed();
		
		System.out.println("dans around apres execution");
	}
}
