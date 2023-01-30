package tp.spring.orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Pianiste implements IMusicien {
	private IInstrument instrument;

	public Pianiste() {
		super();
	}

	@Autowired
	public Pianiste(@Qualifier("piano") IInstrument instrument) {
		super();
		this.instrument = instrument;
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	@Override
	public void jouer() {
		System.out.println("Le guitariste joue : (" + this.instrument.toString() + ")");
	}

}
