<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Patient" %>
<%@ page import="java.util.List" %>
<%@ page import="context.Singleton" %>

<html>
<head>
<meta charset="UTF-8">
<title>Liste des patients !</title>
</head>
<body>


<h1> Liste des patients </h1>
<%

List<Patient> patients = Singleton.getInstance().getDaoPatient().findAll();
System.out.println(patients);

%>
	<table border>
		<tr>
		<th>ID</th>
		<th>Nom</th>
		<th>Prenom</th>
		<th>Action</th>
		</tr>
	<%
	
		for(Patient p : patients) 
	{
		out.println("<tr>");
		out.println("<td>"+p.getId()+"</td>");
		out.println("<td>"+p.getNom()+"</td>");
		out.println("<td>"+p.getPrenom()+"</td>");
		out.println("<td><a href='fichePatient.jsp?id="+p.getId()+"'><input type='button' value='Modifier'></a></td>");
		out.println("</tr>");
	}
	%>
		
		</table>
		
		
		<form action='' method='post'>
		
		ID <input type='number' name='id'><br>
		nom <input type='text' name='nom' placeholder='Saisir nom'><br>
		prenom <input type='text' name='prenom' placeholder='Saisir prenom'><br>
		<input type='submit' value='Ajouter patient'><br>

		</form>


</body>
</html>