<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="list" class="de.formularmanager.controller.ListController"></jsp:useBean>

<table class="table table-striped table-hover">
	<tr>
		<th>
			ID
		</th>
		<th>
			Titel
		</th>
		<th>
			Typ
		</th>
		<th>
			Land
		</th>
		<th class="text-right">
			Optionen			
		</th>
	</tr>
	<c:forEach items="${formsList}" var="item">
		<c:choose>
		   	<c:when test="${filter == 'active'}">
		   		<c:if test="${list.isActive(item.getFormMeta()['validFrom'], item.getFormMeta()['validTo'])}">
					<%@include file="_listContent.tpl.jsp" %>
		   		</c:if>
		   	</c:when>
		   	<c:otherwise>
				<%@include file="_listContent.tpl.jsp" %>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</table>

