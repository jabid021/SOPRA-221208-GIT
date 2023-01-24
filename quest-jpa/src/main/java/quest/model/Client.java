package quest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Client {
	@EmbeddedId
	private ClientId id;
	@Column(length = 50)
	private String siret;
	@OneToMany(mappedBy = "client")
	private List<Filiere> filieres = new ArrayList<>();

	public Client() {
		super();
	}

	public ClientId getId() {
		return id;
	}

	public void setId(ClientId id) {
		this.id = id;
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

}
