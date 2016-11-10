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
			Typ
		</th>
		<th>
			Erstellt am
		</th>
		<th>
			Geändert am
		</th>
	</tr>
	<c:forEach items="${formsList}" var="item">
		<tr>
			<td>
		    	${item.getId()}
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
		</tr>
	</c:forEach>
</table>

