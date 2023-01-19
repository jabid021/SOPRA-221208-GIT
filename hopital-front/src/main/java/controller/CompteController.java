package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Compte;
import model.Medecin;
import model.Secretaire;

@WebServlet("/compte")
public class CompteController extends HttpServlet {
	
	
	//findById/findAll/insert/update/delete
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//findById/findAll/delete
		
		
		if(request.getParameter("id")!=null) 
		{
			//Si on recoit un param ID => findById ou delete
			if(request.getParameter("delete")!=null) 
			{
				//delete
				Integer id = Integer.parseInt(request.getParameter("id"));
				Singleton.getInstance().getDaoCompte().delete(id);
				response.sendRedirect("compte");
			}
			else 
			{
				//findById
				Integer id = Integer.parseInt(request.getParameter("id"));
				Compte c = Singleton.getInstance().getDaoCompte().findById(id);
				request.setAttribute("compte", c);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/ficheCompte.jsp").forward(request, response);
			}
		}
		else 
		{
			List<Compte> comptes = Singleton.getInstance().getDaoCompte().findAll();
			request.setAttribute("listComptes", comptes);
			this.getServletContext().getRequestDispatcher("/WEB-INF/comptes.jsp").forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id")==null) 
		{
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			Compte c ;
			if(request.getParameter("typeCompte").equals("Medecin"))
			{
				c = new Medecin(login,password);
			}
			else 
			{
				c = new Secretaire(login,password);
			}
			
			Singleton.getInstance().getDaoCompte().insert(c);
		}
		else 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			Compte c ;
			if(request.getParameter("typeCompte").equals("Medecin"))
			{
				c = new Medecin(id,login,password);
			}
			else 
			{
				c = new Secretaire(id,login,password);
			}
			
			Singleton.getInstance().getDaoCompte().update(c);
		}
		
		response.sendRedirect("compte");
	}
	

}
