<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Spring 3.0 MVC and Hibernate4 Sample - Add Record</title>
</head>
<body>
	<form:form commandName="helloWorldEntity" action="/zimin/sample/addHelloWorld.do" method="post">
    	Welcome Message : <form:input path="welcomeText" name="welcomeText" id="welcomeText"></form:input><input type="submit" value="Submit"></input>
	</form:form>
</body>
</html>