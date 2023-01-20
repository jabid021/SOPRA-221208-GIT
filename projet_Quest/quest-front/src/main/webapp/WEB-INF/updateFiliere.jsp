<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche Filiere</title>
</head>
<body>



<div id="content">

  <h3>Modifier Filiere ${filiere.id}</h3>
  <form action="filiere" method="post">
  <input type="hidden" name="id" value="${filiere.id}">
  Libelle :<input name="libelle" type="text" placeholder="Saisir le libelle" value="${filiere.libelle}"><br>
            Debut :<input name="debut" type="date" value="${filiere.debut}"><br>
            Fin :<input name="fin" type="date" value="${filiere.fin}"><br>

    <input class ="btn btn-warning" type="submit" value="Modifier">
    <a href="filiere"><input type="button" class ="btn btn-info" value="Retour"></a>
  </form>
</div>

</body>
</html>