<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="model.Compte" %>
    <%@ page import="model.Medecin" %>

	<%@ page import="context.Singleton" %>
	
	
	<%
	Integer id = Integer.parseInt(request.getParameter("id"));
	Compte c = Singleton.getInstance().getDaoCompte().findById(id);
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche Compte</title>
</head>
<body>


<h1>Modifier Compte</h1>

<form action='' method='post'>
		<input type="hidden" name="id" value ="<%=c.getId()%>">
		Login <input type='text' name='login' placeholder='Saisir login' value="<%=c.getLogin()%>"><br>
		Password <input type='password' name='password' placeholder='Saisir password' value="<%=c.getPassword()%>"><br>
		<select>
		<% 
		if(c instanceof Medecin)
		{
			out.println("<option selected>Medecin</option><option>Secretaire</option>");
		}
		else
		{
			out.println("<option>Medecin</option><option selected>Secretaire</option>");
		}
		
		%>
		
		</select>
		<input type='submit' value='Modifier Compte'><br>

		</form>

</body>
</html>