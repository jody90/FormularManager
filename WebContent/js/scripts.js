$.datetimepicker.setLocale('de');

$(document).ready(function() {
	console.log("ready");
	$('.datetimepicker').datetimepicker({
		format:'Y-m-d H:i',
	});
})
