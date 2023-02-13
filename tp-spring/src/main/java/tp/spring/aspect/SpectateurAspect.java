package tp.spring.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SpectateurAspect {
	@AfterReturning(pointcut = "execution(* tp.spring.orchestre.*.jouer(..))")
	public void applaudir() {
		System.out.println("CLAP CLAP CLAP");
	}
	
	@AfterThrowing(pointcut = "execution(* tp.spring.orchestre.*.jouer(..))")
	public void rembourser() {
		System.out.println("BOUH !!!! REMBOURSEZ !!!");
	}
}
