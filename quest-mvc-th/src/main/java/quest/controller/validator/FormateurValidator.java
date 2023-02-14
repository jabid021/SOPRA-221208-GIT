package quest.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import quest.model.Formateur;

public class FormateurValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Formateur.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Formateur formateur = (Formateur) target;

		if (formateur.getAdresse() != null
				&& (!formateur.getAdresse().getCodePostal().isBlank() ^ !formateur.getAdresse().getVille().isBlank())) {
			errors.rejectValue("adresse.codePostal", "codePostal.ville.required",
					"Le code postal et la ville doivent être renseignés conjointement");
		}

	}

}
