<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>
	Liste
</h1>

<c:forEach items="${formsList}" var="item">
    ${item}<br>
</c:forEach>

