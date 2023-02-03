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
<base href="${pageContext.request.contextPath }/">
</head>
<body>
	<div class="container">
		<h1>creation client</h1>
		<c:if test="${error }">
			<div class="alert alert-danger">
				erreur pendant la creation
			</div>
		</c:if>
		<form action="client" method="post">
			<div class="form-group">
				<label for="id.nom">nom:</label> <input id="id.nom" name="id.nom"
					class="form-control">
			</div>
			<div class="form-group">
				<label for="id.type">type:</label> <select name="id.type"
					class="form-control">
					<c:forEach var="type" items="${types}">
						<option value="${type}">${type}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="siret">siret:</label> <input id="siret" name="siret"
					class="form-control">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-outline-primary">enregistrer</button>
				<a href="client" class="btn btn-link">annuler</a>
			</div>
		</form>
	</div>
</body>
</html>