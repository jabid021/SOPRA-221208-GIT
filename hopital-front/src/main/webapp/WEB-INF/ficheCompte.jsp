<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche Compte</title>
</head>
<body>

	<h1>Modifier Compte</h1>

	<form action='compte' method='post'>
		<input type='hidden' name='id' value="${compte.id}"><br>
		Login <input type='text' name='login' placeholder='Saisir login'
			value="${compte.login}"><br> Password <input
			type='password' name='password' placeholder='Saisir password'
			value="${compte.password}"><br> Type de compte<select
			name="typeCompte">
			<!--<c:if test="${compte.getClass().getSimpleName()=='Medecin'}">
					<option selected>Medecin</option>
					<option>Secretaire</option>
				</c:if>
				
				<c:if test="${compte.getClass().getSimpleName()=='Secretaire'}">
					<option >Medecin</option>
					<option selected>Secretaire</option>
				</c:if>
				-->

			<c:choose>
				<c:when test="${compte.getClass().getSimpleName()=='Medecin'}">
					<option selected>Medecin</option>
					<option>Secretaire</option>
				</c:when>

				<c:otherwise>
					<option>Medecin</option>
					<option selected>Secretaire</option>
				</c:otherwise>
			</c:choose>


		</select> <input type='submit' value='Modifier compte'><a href="compte"><input
			type='button' value='Retour'></a><br>
	</form>


</body>
</html>