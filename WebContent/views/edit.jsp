<div class="row">
	<div class="col-xs-3 bg-gray">
		<form action="EditController" method="post">
		
			<input type="hidden" name="form_id" value="${formId}">

			<div class="margin-bottom-md">
				<label for="formType">Formular Type</label>
				<select name="formType" class="form-control">
					<option value="umfrage">Umfrage</option>
					<option value="antrag">Antrag</option>
				</select>
			</div>

			<div class="margin-bottom-md">
				<label for="country">Land</label>
				<select name="country" class="form-control">
					<option value="DE">Deutschland</option>
					<option value="CH">Schweiz</option>
					<option value="AT">Östereich</option>
				</select>
			</div>
			
			<div class="form-group">
				<label for="form_title">Formular Titel</label>
				<input class="form-control" type="text" name="form_title_countryPlaceholder" value="${formData['form_title_DE']}" placeholder="Formular Titel">
			</div>

			<div class="form-group">
				<label for="validFrom">Gültig von:</label>
				<input class="form-control" type="text" name="valid_from_countryPlaceholder" value="${formData['valid_from_DE']}" placeholder="01.01.2016 12:15:00">
			</div>
			
			<div class="form-group">
				<label for="validTo">Gültig bis:</label>
				<input class="form-control" type="text" name="valid_to_countryPlaceholder" value="${formData['valid_to_DE']}" placeholder="01.01.2016 12:15:00">
			</div>
			
			<textarea rows="10" cols="45" name="form_content_html_countryPlaceholder" class="hidden" id="form_content_html"></textarea>
			<textarea rows="10" cols="45" name="form_content_xml_countryPlaceholder" class="hidden" id="form_content_xml">${formData['form_content_xml_DE']}</textarea>
		
			<button class="btn btn-success save-form" type="submit" name="action" value="save">
				Formular speichern
			</button>
					
		</form>
	</div>
	<div class="col-xs-9">
		<div id="fb-editor"></div>
	</div>
</div>
