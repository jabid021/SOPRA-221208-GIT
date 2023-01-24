package quest.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClientId implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="name", length = 100)
	private String nom;
	@Column(length = 10)
	private TypeClient type;

	public ClientId() {
		super();
	}

	public ClientId(String nom, TypeClient type) {
		super();
		this.nom = nom;
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public TypeClient getType() {
		return type;
	}

	public void setType(TypeClient type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientId other = (ClientId) obj;
		return Objects.equals(nom, other.nom) && type == other.type;
	}

}
