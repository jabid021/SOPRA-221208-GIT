<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>quest</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
<base href="${pageContext.request.contextPath}/">
</head>
<body>
	<div class="container">
		<h1>edition formateur</h1>
		<form action="formateur" method="post">
			<div class="form-group">
				<label for="id">id:</label> <input  name="id"
					class="form-control" id="id" readonly="readonly" value="${formateur.id}">
			</div>
			<div class="form-group">
				<label for="prenom">prenom:</label> <input 
					name="prenom" id="prenom" class="form-control" value="${formateur.prenom}">
			</div>
			<div class="form-group">
				<label for="nom">nom:</label> <input  name="nom"
					class="form-control" id="nom" value="${formateur.nom}">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-outline-primary">enregistrer</button>
				<a href="formateur" class="btn btn-outline-warning">annuler</a>
			</div>
		</form>
	</div>
</body>
</html>