package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Filiere;
import model.Stagiaire;

@WebServlet("/stagiaire")
public class StagiaireController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null) 
		{
			if(request.getParameter("delete")!=null) 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Singleton.getInstance().getDaoStagiaire().delete(id);
				response.sendRedirect("stagiaire");
			}
			else 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Stagiaire s = Singleton.getInstance().getDaoStagiaire().findById(id);
				List<Filiere> filieres = Singleton.getInstance().getDaoFiliere().findAll();

				request.setAttribute("stagiaire", s);
				request.setAttribute("filieres", filieres);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/updateStagiaire.jsp").forward(request, response);
			}
		}
		else 
		{
			List<Stagiaire> stagiaires = Singleton.getInstance().getDaoStagiaire().findAll();
			List<Filiere> filieres = Singleton.getInstance().getDaoFiliere().findAll();
			request.setAttribute("stagiaires", stagiaires);
			request.setAttribute("filieres", filieres);
			this.getServletContext().getRequestDispatcher("/WEB-INF/stagiaires.jsp").forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id")==null) 
		{
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");
			String email= request.getParameter("email");
			Integer idFiliere = Integer.parseInt(request.getParameter("filiere"));
			Filiere f = Singleton.getInstance().getDaoFiliere().findById(idFiliere);
					
			Stagiaire s = new Stagiaire(nom,prenom,email,f);
			Singleton.getInstance().getDaoStagiaire().insert(s);
		}
		else 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");
			String email= request.getParameter("email");
			Integer idFiliere = Integer.parseInt(request.getParameter("filiere"));
			Filiere f = Singleton.getInstance().getDaoFiliere().findById(idFiliere);
					
			Stagiaire s = new Stagiaire(id,nom,prenom,email,f);
			Singleton.getInstance().getDaoStagiaire().update(s);
		}
		
		response.sendRedirect("stagiaire");
	}
}
