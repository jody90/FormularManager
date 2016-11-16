<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
			Ge�ndert am
		</th>
		<th>
			Optionen			
		</th>
	</tr>
	<c:forEach items="${formsList}" var="item">
		<tr>
			<td class="align-middle">
		    	${item.getId()}
		    </td>
  			<td class="align-middle">
		    	${item.getFormTitle()}
		    </td>
			<td class="align-middle">
		    	${item.getType()}
		    </td>
   			<td class="align-middle">
		    	${item.getCreatedAt()}
		    </td>
   			<td class="align-middle">
		    	${item.getModifiedAt()}
		    </td>
    		<td class="align-middle">
		    	<a class="list-option-link" href="${pageContext.request.contextPath}/edit?action=edit&contry=DE&form_id=${item.getId()}">
		    		<span class="glyphicon glyphicon-edit symbol-mad" aria-hidden="true"></span>
		    	</a>
  			    <a class="list-option-link" href="${pageContext.request.contextPath}/edit?action=delete&contry=DE&form_id=${item.getId()}">
		    		<span class="glyphicon glyphicon-trash symbol-mad" aria-hidden="true"></span>
		    	</a>
		    </td>
		</tr>
	</c:forEach>
</table>

