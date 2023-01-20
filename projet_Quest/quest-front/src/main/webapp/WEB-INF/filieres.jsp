<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script
  src="https://code.jquery.com/jquery-3.6.3.min.js"
  integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
  crossorigin="anonymous"></script>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Filieres</title>
</head>
<body>



	<div id="content" >
		<h1>Liste des Filieres</h1>
		<input id="btnAddFiliere" type="button" class="btn btn-success"
			value="Ajouter"> <a href="index.html"><input
			type="button" class="btn btn-info" value="Retour"></a> <input
			type="date" id="dateSearch">
			<input type="button" id="cancelBtn" value="Cancel">
			<div id = "tabFilieres">
			
			
			
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Libelle</th>
					<th>Debut</th>
					<th>Fin</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${filieres}" var="filiere">
					<tr>
						<td>${filiere.id}</td>
						<td>${filiere.libelle}</td>
						<td>${filiere.debut}</td>
						<td>${filiere.fin}</td>
						<td><a href="filiere?id=${filiere.id}"><input
								type="button" class="btn btn-warning" value="Modifier"></a>
							<a href="filiere?id=${filiere.id}&delete"><input
								type="button" class="btn btn-danger" value="Supprimer"></a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>




		<div id="addFormFiliere" class="formAjout">
			<h3>Ajouter Filiere</h3>
			<form action="filiere" method="post">
				Libelle :<input name="libelle" type="text"
					placeholder="Saisir le libelle"><br> Debut :<input
					name="debut" type="date"><br> Fin :<input name="fin"
					type="date"><br> <input class="btn btn-success"
					type="submit" value="Ajouter">
			</form>
		</div>
	</div>


</body>
</html>


<script>

btnAddFiliere.onclick=function()
{
  addFormFiliere.style.display="block";
}


dateSearch.onchange = function(){
	
$.ajax("filiere", {
			type: "GET",
			data: {
				search: $("#dateSearch").val()
				},
			success: function (resp) {
				$('#tabFilieres').html(resp);
				}
			});
	}


cancelBtn.onclick=function()
{
	//location.reload();
	
	$.ajax("filiere", {
		type: "GET",
		data: {
			search: "no-search"
			},
		success: function (resp) {
			$('#tabFilieres').html(resp);
			}
		});
}


</script>