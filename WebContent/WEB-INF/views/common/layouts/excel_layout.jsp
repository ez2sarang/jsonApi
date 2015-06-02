<%@ page language="java" contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"
%><%
String fileName = (String)request.getAttribute("fileName");
response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>
<tiles:insertAttribute name="body"/>
</body>
</html>