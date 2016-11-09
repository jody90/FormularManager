<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:directive.include file="head.tpl.jsp" />
</head>
<body>

	<form action="EditController" method="post">

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

</body>
</html>