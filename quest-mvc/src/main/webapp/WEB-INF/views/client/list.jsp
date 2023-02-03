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
		<h1>liste des clients</h1>
		<table class="table">
			<thead>
				<tr>
					<th>nom</th>
					<th>type</th>
					<th>siret</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${clients}">
					<tr>
						<td>${c.id.nom}</td>
						<td>${c.id.type}</td>
						<td>${c.siret}</td>
						<td><a
							href="client/delete?nom=${c.id.nom}&type=${c.id.type}"
							class="btn btn-outline-danger"> suppression</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="client/add" class="btn btn-link">nouveau client</a>
	</div>
</body>
</html>