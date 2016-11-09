<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="head" fragment="true" %>

<!DOCTYPE html>
<html lang="de">
    <head>
        <title>JSP Template Inheritance</title>
    </head>

<h1>Head</h1>
<div>
    <layout:block name="header">
        header
    </layout:block>
</div>

<h1>Contents</h1>
<div>
    <p>
    <layout:block name="contents">
        <h2>Contents will be placed under this h2</h2>
    </layout:block>
    </p>
</div>

<div class="footer">
    <hr />
    <h1>Hallo test</h1>
</div>
</html>