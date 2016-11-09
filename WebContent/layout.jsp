<!DOCTYPE html>
<html lang="de">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Formular Manager</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/helperClasses.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/stylse.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	<link href="css/menu.css" rel="stylesheet">
	<link href="css/responsiveTables.css" rel="stylesheet">

</head>
<body>
    <div class="container-fluid">
		<div class="pageheader row margin-bottom-md text-center header">
            <div class="col-xs-1 header-logo">
	            <a href="${pageContext.request.contextPath}/index">
	                Sortimo Logo
	            </a>
            </div>
            <div class="col-xs-11 header-text">
                <h1>
                    ${pageTitle}
                </h1>
            </div>
        </div>
		<jsp:include page="views/${view}.jsp"></jsp:include>
	</div>
</body>
</html>