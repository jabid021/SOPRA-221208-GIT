package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Matiere;

@WebServlet("/matiere")
public class MatiereController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null) 
		{
			if(request.getParameter("delete")!=null) 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Singleton.getInstance().getDaoMatiere().delete(id);
				response.sendRedirect("matiere");
			}
			else 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Matiere m = Singleton.getInstance().getDaoMatiere().findById(id);

				request.setAttribute("matiere", m);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/updateMatiere.jsp").forward(request, response);
			}
		}
		else 
		{
			List<Matiere> matieres = Singleton.getInstance().getDaoMatiere().findAll();
			request.setAttribute("matieres", matieres);
			this.getServletContext().getRequestDispatcher("/WEB-INF/matieres.jsp").forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id")==null) 
		{
			String libelle = request.getParameter("libelle");
			Integer quest = Integer.parseInt(request.getParameter("quest"));
			
					
			Matiere m = new Matiere(libelle,quest);
			Singleton.getInstance().getDaoMatiere().insert(m);
		}
		else 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			String libelle = request.getParameter("libelle");
			Integer quest = Integer.parseInt(request.getParameter("quest"));
			
					
			Matiere m = new Matiere(id,libelle,quest);
			Singleton.getInstance().getDaoMatiere().update(m);
		}
		
		response.sendRedirect("matiere");
	}
}
