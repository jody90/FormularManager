$.datetimepicker.setLocale('de');

$(document).ready(function() {
	$('.datetimepicker').datetimepicker({
		format:'Y-m-d H:i',
	});
	
//	var radioGroups = [];
//	var radioGroupsValue = {};
//	
//	$("#html_form .radio-group").each(function(radioGroup) {
//		var name = $(this).attr("name");
//		if (name !== undefined && $.inArray(name, radioGroups) == -1) {
//			radioGroups.push(name);
////			console.log(name);
////		var test = $("input[name='radio-group-1479713492395']").attr("id");
////		console.log(test);
//		}
//		
////		console.log($(this).find("input").html());
////		console.log($(this).find("input").attr("value"));
////		
//		
//	})
//	for (var i = 0; i < radioGroups.length; i++) {
//		console.log(radioGroups[i]);		
//		$(radioGroups[i] + "input").each(function(data) {
//			console.log(data);
//		})
//	}
	
//	var value = $(this).find("input").attr("value");
//	console.log(radioGroups);
	
	
})
