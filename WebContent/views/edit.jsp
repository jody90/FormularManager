<h1>
	${formData["form_title_DE"]}
	<hr>
	${country}
	<hr>
	${formData}
	<hr>
	${formData["form_content_DE"]}
	<hr>
	Hallo
</h1>

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
	<input type="text" name="form_title_countryPlaceholder" value="${formData.get('page_title_DE')}"><br>
	<br>

	<label for="validFrom">Gültig von:</label>
	<input type="text" name="valid_from_countryPlaceholder"><br>
	<br>
	
	<label for="validTo">Gültig bis:</label>
	<input type="text" name="valid_to_countryPlaceholder"><br>
	<br>
	
	<textarea name="form_content_countryPlaceholder" cols="45" rows="20"></textarea>
	
	<button class="btn btn-success" type="submit" name="action" value="save">
		Speichern
	</button>
			
</form>