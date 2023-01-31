package tp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tp.spring.orchestre.IInstrument;
import tp.spring.orchestre.IMusicien;
import tp.spring.orchestre.Pianiste;
import tp.spring.orchestre.Piano;
import tp.spring.orchestre.Synthe;

@Configuration
@ComponentScan({"tp.spring.orchestre","tp.spring.aspect"})
@EnableAspectJAutoProxy //activation annotation aspect
public class ApplicationConfig {

	@Bean
	public IInstrument synthe() {
		return new Synthe();
	}

	@Bean
	public IInstrument piano() {
		return new Piano();
	}

	@Bean
	public IMusicien pianiste(IInstrument piano) {
		Pianiste pianiste = new Pianiste();
		pianiste.setInstrument(piano);
		return pianiste;
	}

}
