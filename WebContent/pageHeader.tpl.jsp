<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="de.formularmanager.global.DetectPageBean"%>

<jsp:useBean id="DetectPageBean" class="de.formularmanager.global.DetectPageBean" />
<jsp:setProperty name="DetectPageBean" property="filename" value="jody" />

<h1>
	Page-Header
	${DetectPageBean.filename}
	${Request}
</h1>