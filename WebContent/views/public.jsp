<div class="content">
	<div class="row margin-bottom-lg">
		<div class="col-xs-12 text-center">
			<h1>
				${formData['form_title_DE']}
			</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12 text-center">
			<form method="post" action="PublicController" class="public-form">
				<input type="hidden" name="form_id" value="${formId}">
				
				${formData['form_content_html_DE']}

				<br>
				<button class="btn btn-success margin-top-md" type="submit" name="action" value="save">
					Abschicken
				</button>
			</form>
		</div>
	</div>

</div>