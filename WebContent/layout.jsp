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
	<link href="${pageContext.request.contextPath}/css/jquery.datetimepicker.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/helperClasses.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/responsiveTables.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/formBuilder.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/public.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/chart.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/settings.css" rel="stylesheet">
	
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/form-builder.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/form-render.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/formBuilderFunctions.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
	<script src="${pageContext.request.contextPath}/js/settings.js"></script>

</head>
<body>
    <div class="container-fluid">
		<div class="pageheader row margin-bottom-md text-center page_header">
            <div class="col-xs-6 col-sm-3 header-logo">
	            <a href="${pageContext.request.contextPath}/index">
	                <img src="images/sortimo-logo.png" title="sortimo" alt="sortimo logo" class="img-responsive">
	            </a>
            </div>
            <div class="hidden-xs col-sm-6 header-text">
                <h1>
                    ${pageTitle}
                </h1>
            </div>
            <div class="col-xs-6 col-sm-3 text-right">
     			<c:if test="${not empty firstname}">
					<div class="padding-top-xs inlineblock">
						Hallo ${firstname}
					</div>
				
					<a class="logout_link btn btn-warning" href="${pageContext.request.contextPath}/login?action=logout" title="logout">
						Logout
					</a>
		   		</c:if>
            </div>
        </div>
		<jsp:include page="views/${view}.jsp"></jsp:include>
	</div>
</body>
</html>