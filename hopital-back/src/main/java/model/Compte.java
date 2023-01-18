package model;

public  abstract class Compte {

	protected Integer id;
	protected String login;
	protected String password;
	
	
	public Compte(Integer id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}


	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}


	public Integer getId() {
		return id;
	}


	public String getLogin() {
		return login;
	}


	public String getPassword() {
		return password;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	


	
	
}
