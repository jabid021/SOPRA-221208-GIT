<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html>
<head>
<meta charset="UTF-8">
<title>Liste des patients !</title>
</head>
<body>


<h1> Liste des patients </h1>

	<table border>
		<tr>
		<th>ID</th>
		<th>Nom</th>
		<th>Prenom</th>
		<th>Action</th>
		</tr>

		<tr>
		<td>${patients[0].id}</td>
		<td>${patients[0].nom}</td>
		<td>${patients[0].prenom}</td>
		<td>
		<a href='patient?id=${patients[0].id}'><input type='button' value='Modifier'></a>
		<a href='patient?id=${patients[0].id}&delete'><input type='button' value='Delete'></a>
		</td>
		</tr>
		
		</table>
		
		
		<form action='patient' method='post'>
		<input type="hidden" name="tache" value="insert"> 
		ID <input type='number' name='id'><br>
		nom <input type='text' name='nom' placeholder='Saisir nom'><br>
		prenom <input type='text' name='prenom' placeholder='Saisir prenom'><br>
		<input type='submit' value='Ajouter patient'><br>

		</form>


</body>
</html>