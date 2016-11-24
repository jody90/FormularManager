$(document).ready(function() {

	var templateBckup;
	
	$('#editUserModal').on('show.bs.modal', function (e) {
		var toggledButton = $(e.relatedTarget);
		var username = toggledButton.attr("data-username");
		var body = $(this).find(".modal-body");
		var template = $(body).html();
		templateBackup = template;

		$.ajax({
	        type: "POST",
	        url: "settings",
	        data: {
	            action: "getEditUserData",
	            editUser: username
	        },
	        success: function(data) {
	        	var obj = JSON.parse(data);
	        	var userData = obj[0];
	        	var roles = obj[1];
	        	var rights = obj[2];
	        	
	        	var orderedRights = [];
	        	
	        	for (var i = 0; i < rights.length; i++) {
	        		orderedRights[rights[i].id] = rights[i];
	        	}
	        	
	        	var orderedRoles = [];
	        	
	        	for (var i = 0; i < roles.length; i++) {
	        		orderedRoles[roles[i].id] = roles[i];
	        	}
	        	
	        	var userRights = userData.rights != undefined ? JSON.parse(userData.rights) : [];
	        	var userRoles = userData.roles != undefined ? JSON.parse(userData.roles) : [];
	        		      
	        	// Daten fuer moegliche Rollen aufbereiten
	        	var roleListItemTemplate = '<li data-id="####roleId####" class="list-group-item">####roleName#### <span data-type="role" data-action="add" class="action icon_right glyphicon glyphicon-plus" aria-hidden="true"></span></li>'
	        	var rolesListHtml = "";
	        		
	        	for (var i = 0; i < roles.length; i++) {
	        		if (userRoles.indexOf(roles[i].id) == -1) {
	        			var listItem = roleListItemTemplate.replace("####roleName####", roles[i].name);
	        			listItem = listItem.replace("####roleId####", roles[i].id);
	        			rolesListHtml += listItem;
	        		}
	        	}
	        	
	        	// Daten fuer moegliche Rechte aufbereiten	        	
	        	var rightListItemTemplate = '<li data-id="####rightId####" class="list-group-item">####rightName#### <span data-type="right" data-action="add" class="action icon_right glyphicon glyphicon-plus" aria-hidden="true"></span></li>'
	        	var rightsListHtml = "";
	        		
	        	for (var i = 0; i < rights.length; i++) {
	        		if (userRights.indexOf(rights[i].id) == -1) {
	        			var listItem = rightListItemTemplate.replace("####rightName####", rights[i].name);
	        			listItem = listItem.replace("####rightId####", rights[i].id);
	        			rightsListHtml += listItem;
	        		}
	        	}

	        	// Daten fuer Rollen aufbereiten	        	
	        	var userRoleListItemTemplate = '<li data-id="####roleId####" class="list-group-item">####roleName#### <span data-type="role" data-action="remove" class="action icon_right glyphicon glyphicon-minus" aria-hidden="true"></span></li>'
	        	var userRolesListHtml = "";
	        		
	        	for (var i = 0; i < userRoles.length; i++) {
        			var listItem = userRoleListItemTemplate.replace("####roleName####", orderedRoles[userRoles[i]].name);
        			listItem = listItem.replace("####roleId####", orderedRoles[userRoles[i]].id);
        			userRolesListHtml += listItem;
	        	}

	        	// Daten fuer Rechte aufbereiten	        	
	        	var userRightListItemTemplate = '<li data-id="####rightId####" class="list-group-item">####rightName#### <span data-type="right" data-action="remove" class="action icon_right glyphicon glyphicon-minus" aria-hidden="true"></span></li>'
	        	var userRightsListHtml = "";
	        		
	        	for (var i = 0; i < userRights.length; i++) {
        			var listItem = userRightListItemTemplate.replace("####rightName####", orderedRights[userRights[i]].name);
        			listItem = listItem.replace("####rightId####", orderedRights[userRights[i]].id);
        			userRightsListHtml += listItem;
	        	}
	        	
	        	template = template.replace("####username####", userData.username);
	        	template = template.replace("####firstname####", userData.firstname);
	        	template = template.replace("####lastname####", userData.lastname);
	        	template = template.replace("####email####", userData.email);
	        	template = template.replace("####possibleRoles####", rolesListHtml);
	        	template = template.replace("####possibleRights####", rightsListHtml);
	        	template = template.replace("####currentRoles####", userRolesListHtml);
	        	template = template.replace("####currentRights####", userRightsListHtml);
	        	
	        	$(body).html(template);
	        	
	        	$(".list-group").on("click", ".action", function() {
	        		var action = $(this).attr("data-action");
	        		var type = $(this).attr("data-type");
	        		var id  = $(this).parent("li").attr("data-id");
	        		var listElement = $(this).parent("li").clone();
	        		var currentList = $(this).parent().parent().attr("id");
	        		
	        		$(this).parent("li").remove();
	        		
	        		var configInvert = {
        				"possibleRoles" : "currentRoles",
        				"currentRoles" : "possibleRoles",
        				"possibleRights" : "currentRights",
        				"currentRights" : "possibleRights"
	        		};
	        		
	        		var actionInvert = action == "add" ? "remove" : "add";
	        		var icon = action == "add" ? "glyphicon-plus" : "glyphicon-minus"
	        		var iconInvert = action == "add" ? "glyphicon-minus" : "glyphicon-plus";
	        		var boxInvert = configInvert[currentList];
	        		
	        		$(listElement).find("span").attr("data-action", actionInvert);
	        		$(listElement).find("span").removeClass(icon);
	        		$(listElement).find("span").addClass(iconInvert);
	        		
	        		$("#" + boxInvert).append(listElement);
	        	})
	        }
	    })
	})
	
	$('#editUserModal').on('hide.bs.modal', function (e) {
		var body = $(this).find(".modal-body");
    	$(body).html(templateBackup);
	})
	
	$("#submitUserChanges").on("click", function() {
		var roles = [];
		var rights = [];
		
		$("#currentRoles li").each(function(key, value) {
			var roleId = $(value).attr("data-id"); 
			roles.push(roleId);
		})
		
		$("#currentRights li").each(function(key, value) {
			var rightId = $(value).attr("data-id"); 
			rights.push(rightId);
		})
		
		$("#newRights").val(JSON.stringify(rights));
		$("#newRoles").val(JSON.stringify(roles));
		
		$("#editUserForm").submit();
		$('#editUserModal').modal('hide');
	})
	
})