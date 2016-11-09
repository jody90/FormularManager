<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<html>
<head>
	<template:head>
	    <jsp:attribute name="head"></jsp:attribute>
	</template:head>
</head>
<body>
	<template:header>
	    <jsp:attribute name="header"></jsp:attribute>
	</template:header>
	
	<h1>
		Content Bereich
	</h1>
	
	<button class="btn btn-success">
		Hallo
	</button>


	<template:footer>
	    <jsp:attribute name="footer"></jsp:attribute>
	</template:footer>
</body>

</html>