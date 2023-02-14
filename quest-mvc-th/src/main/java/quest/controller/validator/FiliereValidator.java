package quest.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import quest.model.Filiere;

public class FiliereValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {	
		return Filiere.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Filiere filiere = (Filiere) target;
		
		if(filiere.getDebut() != null ^ filiere.getFin() != null) {
			errors.rejectValue("debut", "debut.fin.required", "Les 2 dates doivent être renseignées");
			errors.rejectValue("fin", "debut.fin.required", "Les 2 dates doivent être renseignées");
		} else if(filiere.getDebut() != null && filiere.getDebut().isAfter(filiere.getFin())) {
			errors.rejectValue("debut", "debut.after", "La date de début ne peut être ultérieure à la date de fin");
		}
		
	}

}
