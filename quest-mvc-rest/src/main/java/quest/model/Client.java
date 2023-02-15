package quest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Client {
	@EmbeddedId
	private ClientId id;
	@Version
	private int version;
	@Column(length = 50)
	private String siret;
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private List<Filiere> filieres = new ArrayList<>();

	public Client() {
		super();
	}

	public Client(String nom, TypeClient type) {
		super();
		this.id = new ClientId(nom, type);
	}

	public Client(String nom, TypeClient type, String siret) {
		super();
		this.id = new ClientId(nom, type);
		this.siret = siret;
	}

	public ClientId getId() {
		return id;
	}

	public void setId(ClientId id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

}
