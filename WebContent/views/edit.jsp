<form action="EditController" method="post">

	<label for="form_type">Formular Type</label>
	<select name="form_type">
		<option value="umfrage">Umfrage</option>
		<option value="antrag">Antrag</option>
	</select>
	<br>

	<label for="form_title">Formular Titel</label>
	<input type="text" name="form_title"><br>
	<br>

	<label for="validFrom">Gültig von:</label>
	<input type="text" name="valid_from"><br>
	<br>
	
	<label for="validTo">Gültig bis:</label>
	<input type="text" name="valid_to"><br>
	<br>
	
	<textarea name="form_content" cols="45" rows="20"></textarea>
	
	<button class="btn btn-success" type="submit" name="action" value="save">
		Speichern
	</button>
			
</form>