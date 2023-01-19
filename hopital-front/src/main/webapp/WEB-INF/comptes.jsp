<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<html>
<head>
<meta charset="UTF-8">
<title>Liste des comptes !</title>
</head>
<body>


	<h1>Liste des comptes</h1>

	<table border>
		<tr>
			<th>ID</th>
			<th>Login</th>
			<th>Password</th>
			<th>Type</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${listComptes}" var="compte">
			<tr>
				<td>${compte.id}</td>
				<td>${compte.login}</td>
				<td>${compte.password}</td>
				<td>${compte.getClass().getSimpleName()}</td>
				<td><a href='compte?id=${compte.id}'><input
						type='button' value='Modifier'></a> <a
					href='compte?id=${compte.id}&delete'><input
						type='button' value='Delete'></a></td>
			</tr>
		</c:forEach>
	</table>


	<form action='compte' method='post'>
		Login <input type='text' name='login' placeholder='Saisir login'><br>
		Password <input type='password' name='password'
			placeholder='Saisir password'><br> Type de compte<select
			name="typeCompte">
			<option selected>Medecin</option>
			<option>Secretaire</option>
		</select> <input type='submit' value='Ajouter compte'><br>


	</form>


</body>
</html>