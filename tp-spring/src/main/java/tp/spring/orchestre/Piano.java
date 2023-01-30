package tp.spring.orchestre;

import org.springframework.stereotype.Component;

@Component
public class Piano implements IInstrument {
	@Override
	public String toString() {
		return "PLINK PLINK PLINK"; 
	}
}
