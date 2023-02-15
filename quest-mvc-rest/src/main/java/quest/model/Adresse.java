package quest.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Adresse {
	@Column(name = "street", length = 255)
	@JsonView(Views.ViewBase.class)
	private String rue;
	@Column(length = 255)
	@JsonView(Views.ViewBase.class)
	private String complement;
	@Column(name = "zipcode", length = 10)
	@JsonView(Views.ViewBase.class)
	private String codePostal;
	@Column(name = "city", length = 100)
	@JsonView(Views.ViewBase.class)
	private String ville;

	public Adresse() {
		super();
	}

	public Adresse(String rue, String complement, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.complement = complement;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
