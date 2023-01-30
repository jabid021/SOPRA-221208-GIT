package tp.spring.orchestre;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Guitariste implements IMusicien {
	@Autowired
	@Qualifier("guitare")
	private IInstrument instrument;
	@Value("Vive le vent ...")
	private String morceau;

	public Guitariste() {
		super();
	}

	public Guitariste(IInstrument instrument, String morceau) {
		super();
		this.instrument = instrument;
		this.morceau = morceau;
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

	@Override
	public void jouer() {
		System.out.println("Le guitariste joue : " + this.morceau + " (" + this.instrument.toString() + ")");
	}
	
	@PostConstruct
	public void init() {
		this.morceau = this.morceau.toUpperCase();
	}

}
