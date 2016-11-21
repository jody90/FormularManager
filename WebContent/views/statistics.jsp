<div id="form_id" data-form-id="${statistics.getFormId()}"></div>

<h1>
	${statistics.getFormId()}
</h1>
<div class="row">
	<div class="col-xs-4">
		<div id="chart_div"></div>
	</div>
	<div class="col-xs-8">
		<div id="json_form">
			${statistics.getJsonForm()}
		</div>
	</div>
</div>


<script type="text/javascript" src="js/statistics.js"></script>