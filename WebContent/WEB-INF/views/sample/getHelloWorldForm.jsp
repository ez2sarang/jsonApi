<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Spring 3.0 MVC and Hibernate4 Sample - Select One Record with PK</title>
</head>
<body>
    <form action="/zimin/sample/getHelloWorld.do" method="post">
    	Get Specific Hello World : ID <input type="text" name="id" id="id"></input><input type="submit" value="Submit"></input>
    </form>
</body>
</html>