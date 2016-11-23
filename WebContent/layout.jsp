<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="de">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Formular Manager</title>
	
	<link href="${pageContext.request.contextPath}/css/form-builder.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/form-render.css" rel="stylesheet">
	<link href="css/jquery.datetimepicker.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/helperClasses.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	<link href="css/menu.css" rel="stylesheet">
	<link href="css/responsiveTables.css" rel="stylesheet">
	<link href="css/formBuilder.css" rel="stylesheet">
	<link href="css/list.css" rel="stylesheet">
	<link href="css/public.css" rel="stylesheet">
	<link href="css/chart.css" rel="stylesheet">
	
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/form-builder.min.js"></script>
	<script src="js/form-render.min.js"></script>
	<script src="js/formBuilderFunctions.js"></script>
	<script src="js/jquery.datetimepicker.full.min.js"></script>
	<script src="js/scripts.js"></script>

</head>
<body>
    <div class="container-fluid">
		<div class="pageheader row margin-bottom-md text-center page_header">
            <div class="col-xs-1 header-logo">
	            <a href="${pageContext.request.contextPath}/index">
	                <img src="images/sortimo-logo.png" title="sortimo" alt="sortimo logo" class="img-responsive">
	            </a>
            </div>
            <div class="col-xs-9 header-text">
                <h1>
                    ${pageTitle}
                </h1>
            </div>
            <div class="col-xs-2 text-right">
     			<c:if test="${not empty firstname}">
					Hallo ${firstname}<br>
				
					<a class="logout_link btn btn-default" href="${pageContext.request.contextPath}/login?action=logout" title="logout">
						Logout
					</a>
		   		</c:if>
            </div>
        </div>
		<jsp:include page="views/${view}.jsp"></jsp:include>
	</div>
</body>
</html>