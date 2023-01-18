package servletV1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.Singleton;
import model.Patient;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Patient> patients = Singleton.getInstance().getDaoPatient().findAll();
		System.out.println(patients);

		response.getWriter().println("<html>");
		response.getWriter().println("<body>");
		response.getWriter().println("<table border>");
		response.getWriter().println("<tr>");
		response.getWriter().println("<th>ID</th>");
		response.getWriter().println("<th>Nom</th>");
		response.getWriter().println("<th>Prenom</th>");
		response.getWriter().println("</tr>");
	
		for(Patient p : patients) 
		{
			response.getWriter().println("<tr>");
			response.getWriter().println("<th>"+p.getId()+"</th>");
			response.getWriter().println("<th>"+p.getNom()+"</th>");
			response.getWriter().println("<th>"+p.getPrenom()+"</th>");
			response.getWriter().println("</tr>");	
		}
		
		response.getWriter().println("</table>");
		
		
		response.getWriter().println("<form action='demo' method='post'>");
		
		response.getWriter().println("ID <input type='number' name='id'><br>");
		response.getWriter().println("nom <input type='text' name='nom' placeholder='Saisir nom'><br>");
		response.getWriter().println("prenom <input type='text' name='prenom' placeholder='Saisir prenom'><br>");
		response.getWriter().println("<input type='submit' value='Ajouter patient'><br>");

		response.getWriter().println("</form>");
		
		
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		
		Patient p = new Patient(id,nom,prenom);
		Singleton.getInstance().getDaoPatient().insert(p);
		response.sendRedirect("demo");
	
	
	
	}


}
