package tp.spring.orchestre;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"tp.spring.orchestre"})

public class AppConfig {
	
	@Bean
	public IInstrument piano(){
		return new Piano();
	}
	
	@Bean IInstrument synthe() {
		return new Synthe();
	}
	
	@Bean
	public IMusicien pianiste(IInstrument piano) {
		return new Pianiste(piano());
	}
		
	}

