package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Compte;
import model.Medecin;
import model.Secretaire;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("disconnect")!=null) 
		{
			request.getSession().removeAttribute("connected");
			response.sendRedirect("home");
		}

		else {
			if(request.getSession().getAttribute("connected")==null) 
			{
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(request.getSession().getAttribute("connected") instanceof Medecin) 
			{
				this.getServletContext().getRequestDispatcher("/menuMedecin.jsp").forward(request, response);
			}
			else if(request.getSession().getAttribute("connected") instanceof Secretaire) 
			{
				this.getServletContext().getRequestDispatcher("/menuSecretaire.jsp").forward(request, response);
			}

		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		Compte c = Singleton.getInstance().getDaoCompte().findByLoginAndPassword(login, password);

		if(c instanceof Medecin) 
		{
			this.getServletContext().getRequestDispatcher("/menuMedecin.jsp").forward(request, response);
			request.getSession().setAttribute("connected", c);
		}
		else if(c instanceof Secretaire) 
		{
			this.getServletContext().getRequestDispatcher("/menuSecretaire.jsp").forward(request, response);
			request.getSession().setAttribute("connected", c);
		}
		else 
		{
			response.sendRedirect("home");
		}

	}

}
