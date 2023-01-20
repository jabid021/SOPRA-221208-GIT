

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
  
