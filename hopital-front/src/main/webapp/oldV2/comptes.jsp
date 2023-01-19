<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Compte" %>
<%@ page import="java.util.List" %>
<%@ page import="context.Singleton" %>

<html>
<head>
<meta charset="UTF-8">
<title>Liste des Comptes !</title>
</head>
<body>


<h1> Liste des Comptes </h1>
<%

List<Compte> comptes = Singleton.getInstance().getDaoCompte().findAll();

%>
	<table border>
		<tr>
		<th>ID</th>
		<th>Login</th>
		<th>Password</th>
		<th>Type</th>
		<th>Action</th>
		</tr>
	<%
	
		for(Compte c : comptes) 
	{
		out.println("<tr>");
		out.println("<td>"+c.getId()+"</td>");
		out.println("<td>"+c.getLogin()+"</td>");
		out.println("<td>"+c.getPassword()+"</td>");
		out.println("<td>"+c.getClass().getSimpleName()+"</td>");
		out.println("<td><a href='ficheCompte.jsp?id="+c.getId()+"'><input type='button' value='Modifier'></a></td>");
		out.println("</tr>");
	}
	%>
		
		</table>
		
		
		<form action='' method='post'>
		
		Login <input type='text' name='login' placeholder='Saisir login'><br>
		Password <input type='password' name='password' placeholder='Saisir password'><br>
		<select name="type">
		<option>Medecin</option>
		<option>Secretaire</option>
		</select>
		<input type='submit' value='Ajouter Compte'><br>

		</form>


</body>
</html>