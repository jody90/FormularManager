<form action="EditController" method="post">

	<input type="hidden" name="form_id" value="${formId}">

	<label for="formType">Formular Type</label>
	<select name="formType">
		<option value="umfrage">Umfrage</option>
		<option value="antrag">Antrag</option>
	</select>
	<br>
	
	<label for="country">Land</label>
	<select name="country">
		<option value="DE">Deutschland</option>
		<option value="CH">Schweiz</option>
		<option value="AT">Östereich</option>
	</select>
	<br>

	<label for="form_title">Formular Titel</label>
	<input type="text" name="form_title_countryPlaceholder" value="${formData['form_title_DE']}"><br>
	<br>

	<label for="validFrom">Gültig von:</label>
	<input type="text" name="valid_from_countryPlaceholder" value="${formData['valid_from_DE']}"><br>
	<br>
	
	<label for="validTo">Gültig bis:</label>
	<input type="text" name="valid_to_countryPlaceholder" value="${formData['valid_to_DE']}"><br>
	<br>
	
	<textarea name="form_content_countryPlaceholder" cols="45" rows="20">
		${formData['form_content_DE']}
	</textarea>
	
	<button class="btn btn-success" type="submit" name="action" value="save">
		Speichern
	</button>
			
</form>