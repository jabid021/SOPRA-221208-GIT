package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Ordinateur;
import model.Stagiaire;

@WebServlet("/ordinateur")
public class OrdinateurController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null) 
		{
			if(request.getParameter("delete")!=null) 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Singleton.getInstance().getDaoOrdinateur().delete(id);
				response.sendRedirect("ordinateur");
			}
			else 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Ordinateur o = Singleton.getInstance().getDaoOrdinateur().findById(id);
				List<Stagiaire> stagiaires = Singleton.getInstance().getDaoStagiaire().findAll();

				request.setAttribute("ordinateur", o);
				request.setAttribute("stagiaires", stagiaires);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/updateOrdinateur.jsp").forward(request, response);
			}
		}
		else 
		{
			List<Ordinateur> ordinateurs = Singleton.getInstance().getDaoOrdinateur().findAll();
			List<Stagiaire> stagiaires = Singleton.getInstance().getDaoStagiaire().findAll();
			request.setAttribute("ordinateurs", ordinateurs);
			request.setAttribute("stagiaires", stagiaires);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ordinateurs.jsp").forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id")==null) 
		{
			String marque = request.getParameter("marque");
			Integer ram = Integer.parseInt(request.getParameter("ram"));
			Integer idStagiaire = Integer.parseInt(request.getParameter("stagiaire"));
			Stagiaire s= Singleton.getInstance().getDaoStagiaire().findById(idStagiaire);
					
			Ordinateur o = new Ordinateur(marque,ram,s);
			Singleton.getInstance().getDaoOrdinateur().insert(o);
		}
		else 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			String marque = request.getParameter("marque");
			Integer ram = Integer.parseInt(request.getParameter("ram"));
			Integer idStagiaire = Integer.parseInt(request.getParameter("stagiaire"));
			Stagiaire s= Singleton.getInstance().getDaoStagiaire().findById(idStagiaire);
					
			Ordinateur o = new Ordinateur(id,marque,ram,s);
			Singleton.getInstance().getDaoOrdinateur().update(o);
		}
		
		response.sendRedirect("ordinateur");
	}
}
