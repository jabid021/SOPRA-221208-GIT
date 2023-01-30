package tp.spring.orchestre;

import org.springframework.stereotype.Component;

@Component
public class Guitare implements IInstrument {
	@Override
	public String toString() {
		return "GLINK GLINK GLINK"; 
	}
}
