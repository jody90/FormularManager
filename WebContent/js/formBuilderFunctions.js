jQuery(document).ready(function($) {

	function getFormData(callback) {
		var data = $("#form_content_xml").text();
		callback(data);
	}
	
	var formBuilder;
	
	getFormData(function(data) {
		var formRenderOpts = {
				render: true,
				formData : data,
				stickyControls: true,
				showActionButtons: false
		};
		var formBuilderEditor = $("#fb-editor");
		var form = formBuilderEditor.formBuilder(formRenderOpts);
		formBuilder = form.data('formBuilder');
	});
	

	$(".save-form").click(function(e) {

		var formData = formBuilder.formData;
		
		var formRenderOpts = {
			render: false,
			formData: formData
		};
		
		// Grab markup and escape it
		var markup = new FormRenderFn(formRenderOpts).markup;
		
		$("#form_content_html").html(markup);
		$("#form_content_xml").html(formData);
		
	});
});