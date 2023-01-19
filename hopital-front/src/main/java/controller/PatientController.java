package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Patient;

@WebServlet("/patient")
public class PatientController extends HttpServlet {
	
	
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
				Singleton.getInstance().getDaoPatient().delete(id);
				response.sendRedirect("patient");
			}
			else 
			{
				//findById
				Integer id = Integer.parseInt(request.getParameter("id"));
				Patient p = Singleton.getInstance().getDaoPatient().findById(id);
				request.setAttribute("patient", p);
				
				this.getServletContext().getRequestDispatcher("/fichePatient.jsp").forward(request, response);
			}
		}
		else 
		{
			List<Patient> patients = Singleton.getInstance().getDaoPatient().findAll();
			request.setAttribute("patients", patients);
			this.getServletContext().getRequestDispatcher("/patients.jsp").forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("tache").equals("insert")) 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");
			
			Patient p = new Patient(id,nom,prenom);
			Singleton.getInstance().getDaoPatient().insert(p);
		}
		else 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");
			
			Patient p = new Patient(id,nom,prenom);
			Singleton.getInstance().getDaoPatient().update(p);
		}
		
		response.sendRedirect("patient");
	}
	

}
