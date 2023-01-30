package tp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tp.spring.orchestre.Guitare;
import tp.spring.orchestre.IInstrument;
import tp.spring.orchestre.IMusicien;
import tp.spring.orchestre.Pianiste;
import tp.spring.orchestre.Ukulele;

@Configuration
@ComponentScan("tp.spring.orchestre")
public class ApplicationConfig {

	@Bean
	public IInstrument synthe() {
		return new Guitare();
	}

	@Bean
	public IInstrument piano() {
		return new Ukulele();
	}

	@Bean
	public IMusicien pianiste(IInstrument piano) {
		Pianiste pianiste = new Pianiste();
		pianiste.setInstrument(piano);
		return pianiste;
	}

}
