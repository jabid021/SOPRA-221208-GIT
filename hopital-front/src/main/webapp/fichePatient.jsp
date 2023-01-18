<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="model.Patient" %>

	<%@ page import="context.Singleton" %>
	
	
	<%
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
		nom <input type='text' name='nom' placeholder='Saisir nom' value="<%=p.getPrenom()%>"><br>
		prenom <input type='text' name='prenom' placeholder='Saisir prenom' value="<%=p.getNom()%>"><br>
		<input type='submit' value='Modifier patient'><br>
</form>


</body>
</html>