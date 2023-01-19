<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche Stagiaire</title>
</head>
<body>



<div id="content">

  <h3>Modifier Stagiaire ${stagiaire.id}</h3>
  <form action="stagiaire" method="post">
  <input type="hidden" name="id" value="${stagiaire.id}">
  Nom :<input value="${stagiaire.nom}" name="nom" type="text" placeholder="Saisir votre nom"><br>
  Prenom :<input value="${stagiaire.prenom}" name="prenom" type="text" placeholder="Saisir votre prenm"><br>
  Email :<input value="${stagiaire.email}" name="email" type="email" placeholder="Saisir votre email"><br>
  Filiere
    <select name="filiere">
         <c:forEach items="${filieres}" var="filiere">
              
              	<c:choose>
              	<c:when test="${filiere.id==stagiaire.filiere.id}">
              		<option selected value="${filiere.id}" >${filiere.id} - ${filiere.libelle}</option>
              	</c:when>
              	
              	<c:otherwise>
              		<option value="${filiere.id}" >${filiere.id} - ${filiere.libelle}</option>
              	
              	</c:otherwise>
              	</c:choose>
               
              
              </c:forEach>
    </select><br>

    <input class ="btn btn-warning" type="submit" value="Modifier">
    <a href="stagiaire"><input type="button" class ="btn btn-info" value="Retour"></a>
  </form>
</div>

</body>
</html>