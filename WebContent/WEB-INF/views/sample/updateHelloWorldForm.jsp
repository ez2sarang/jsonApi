<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Spring 3.0 MVC and Hibernate4 Sample - Update Record</title>
</head>
<body>
	<form:form commandName="helloWorldEntity" action="/zimin/sample/updateHelloWorld.do" method="post">
    	ID : <form:input path="id" name="id" id="id"></form:input>
    	Welcome Message : <form:input path="welcomeText" name="welcomeText" id="welcomeText"></form:input>
    	<br><input type="submit" value="Submit"></input>
	</form:form>
</body>
</html>