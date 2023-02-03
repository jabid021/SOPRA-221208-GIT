<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="${pageContext.request.contextPath}/">
</head>

<body>
	<c:if test="${ error }">
		<div>prenom obligatoire</div>
	</c:if>
	<form action="bonjour" method="post">
		<input name="prenom" placeholder="saisir votre prenom"><br/>
		<input name="nom" placeholder="saisir votre nom"><br/>
		<input name="adresse.ville"><br/>
		<button type="submit">envoyer</button>
	</form>

</body>
</html>