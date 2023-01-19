package dao;

import java.util.List;

public interface IDAO<T,K> {


	String url = "jdbc:mysql://localhost:";
	String port="3306";
	String bdd ="/quest_jsp";
	String login="root";
	String password="";


	
	public T findById(K id);
	public List<T> findAll();
	public void insert(T o);
	public void update(T o);
	public void delete(K id);
	
	
	
}
