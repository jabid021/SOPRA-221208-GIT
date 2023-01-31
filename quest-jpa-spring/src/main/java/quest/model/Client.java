package quest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

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
	private List<Filiere> filieres = new ArrayList<>();

	public Client() {
		super();
	}

	public Client(String nom, TypeClient type) {
		super();
		this.id = new ClientId(nom, type);
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

}
