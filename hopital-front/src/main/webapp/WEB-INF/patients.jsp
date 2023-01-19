<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Liste des patients !</title>
</head>
<body>




	<h1>Liste des patients</h1>

	<table border>
		<tr>
			<th>ID</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${patients}" var="patient">
			<tr>
				<td>${patient.id}</td>
				<td>${patient.nom}</td>
				<td>${patient.prenom}</td>
				<td><a href='patient?id=${patient.id}'><input type='button'
						value='Modifier'></a> <a
					href='patient?id=${patient.id}&delete'><input type='button'
						value='Delete'></a></td>
			</tr>
		</c:forEach>


	</table>


	<form action='patient' method='post'>
		<input type="hidden" name="tache" value="insert"> ID <input
			type='number' name='id'><br> nom <input type='text'
			name='nom' placeholder='Saisir nom'><br> prenom <input
			type='text' name='prenom' placeholder='Saisir prenom'><br>
		<input type='submit' value='Ajouter patient'><br>

	</form>


</body>
</html>