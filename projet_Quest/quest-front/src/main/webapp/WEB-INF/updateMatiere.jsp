<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fiche Matiere</title>
</head>
<body>



<div id="content">

  <h3>Modifier Matiere ${matiere.id}</h3>
  <form action="matiere" method="post">
  <input type="hidden" name="id" value="${matiere.id}">
        Libelle :<input name="libelle" type="text" placeholder="Saisir le libelle" value="${matiere.libelle}"><br>
            Code Quest :<input name="quest" type="number" placeholder="Saisir le code Quest" value="${matiere.quest}"><br>

    <input class ="btn btn-warning" type="submit" value="Modifier">
    <a href="matiere"><input type="button" class ="btn btn-info" value="Retour"></a>
  </form>
</div>

</body>
</html>