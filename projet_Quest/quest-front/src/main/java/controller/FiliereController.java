package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Filiere;

@WebServlet("/filiere")
public class FiliereController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null) 
		{
			if(request.getParameter("delete")!=null) 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Singleton.getInstance().getDaoFiliere().delete(id);
				response.sendRedirect("filiere");
			}
			else 
			{
				Integer id = Integer.parseInt(request.getParameter("id"));
				Filiere f = Singleton.getInstance().getDaoFiliere().findById(id);

				request.setAttribute("filiere", f);

				this.getServletContext().getRequestDispatcher("/WEB-INF/updateFiliere.jsp").forward(request, response);
			}
		}
		else 
		{
			List<Filiere> filieres;
			if(request.getParameter("search")!=null) 
			{
				if(request.getParameter("search").equals("no-search"))
				{
					filieres= Singleton.getInstance().getDaoFiliere().findAll();
				}
				else {
					filieres= Singleton.getInstance().getDaoFiliere().findAllByDateBetween(request.getParameter("search"));
				}
				request.setAttribute("filieres", filieres);
				this.getServletContext().getRequestDispatcher("/WEB-INF/searchFilieres.jsp").forward(request, response);
			}
			else {
				filieres= Singleton.getInstance().getDaoFiliere().findAll();
				request.setAttribute("filieres", filieres);
				this.getServletContext().getRequestDispatcher("/WEB-INF/filieres.jsp").forward(request, response);
			}


		}



	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("id")==null) 
		{
			String libelle = request.getParameter("libelle");
			LocalDate debut = LocalDate.parse(request.getParameter("debut"));
			LocalDate fin= LocalDate.parse(request.getParameter("fin"));

			Filiere f = new Filiere(libelle,debut,fin);
			Singleton.getInstance().getDaoFiliere().insert(f);
		}
		else 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));

			String libelle = request.getParameter("libelle");
			LocalDate debut = LocalDate.parse(request.getParameter("debut"));
			LocalDate fin= LocalDate.parse(request.getParameter("fin"));

			Filiere f = new Filiere(id,libelle,debut,fin);
			Singleton.getInstance().getDaoFiliere().update(f);
		}

		response.sendRedirect("filiere");
	}
}
