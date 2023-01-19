<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.Patient"%>

<%@ page import="context.Singleton"%>


<%
	
	
	
	

if(request.getMethod().equals("POST"))
{
	Integer id = Integer.parseInt(request.getParameter("id"));
	String prenom = request.getParameter("prenom");
	String nom = request.getParameter("nom");
	
	Patient p = new Patient(id,nom,prenom);
	Singleton.getInstance().getDaoPatient().update(p);
}

	
	
	
	Integer id = Integer.parseInt(request.getParameter("id"));
	Patient p = Singleton.getInstance().getDaoPatient().findById(id);
	
	
	%>
<% out.println(p);%>
<%= p %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche Patient</title>
</head>
<body>


	<h1>Modifier Patient</h1>

	<form action='' method='post'>

		ID <input disabled type='number' name='id' value="<%=p.getId()%>"><br>
		nom <input type='text' name='nom' placeholder='Saisir nom'
			value="<%=p.getNom()%>"><br> prenom <input
			type='text' name='prenom' placeholder='Saisir prenom'
			value="<%=p.getPrenom()%>"><br> <input type='submit'
			value='Modifier patient'><a href="patients.jsp"><input type='button'
			value='Retour'></a><br>
	</form>


</body>
</html>