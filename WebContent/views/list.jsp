<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>
	Liste
</h1>
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
			Erstellt am
		</th>
		<th>
			Geändert am
		</th>
		<th>
			Optionen			
		</th>
	</tr>
	<c:forEach items="${formsList}" var="item">
		<tr>
			<td>
		    	${item.getId()}
		    </td>
  			<td>
		    	${item.getFormTitle()}
		    </td>
			<td>
		    	${item.getType()}
		    </td>
   			<td>
		    	${item.getCreatedAt()}
		    </td>
   			<td>
		    	${item.getModifiedAt()}
		    </td>
    		<td>
		    	<a href="${pageContext.request.contextPath}/edit?action=edit&contry=DE&form_id=${item.getId()}">
		    		<span class="glyphicon glyphicon-edit icon-md" aria-hidden="true"></span>
		    	</a>
		    </td>
		</tr>
	</c:forEach>
</table>

