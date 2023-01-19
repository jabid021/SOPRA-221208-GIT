<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche Patient</title>
</head>
<body>

	<h1>Modifier Patient</h1>

	<form action='patient' method='post'>
		<input type="hidden" name="tache" value="update"> 
		<input  type='hidden' name='id' value="${patient.id}"><br>
		nom <input type='text' name='nom' placeholder='Saisir nom'
			value="${patient.nom}"><br> prenom <input
			type='text' name='prenom' placeholder='Saisir prenom'
			value="${patient.prenom}"><br> <input type='submit'
			value='Modifier patient'><a href="patient"><input type='button'
			value='Retour'></a><br>
	</form>


</body>
</html>