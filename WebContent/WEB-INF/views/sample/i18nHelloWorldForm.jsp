<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h1>Spring MVC internationalization example</h1>
 
Language : <a href="?lang=ko">한국어</a>|
<a href="?lang=en">English</a>|
<a href="?lang=ja">일본어</a>
 
<h3>
    <spring:message code="labels.helloworld" text="default text" /> : <spring:message code="message.helloworld" text="default text" />
</h3>
<br> 
<br> 
<br> 
<h3>
    <spring:message code="labels.helloworld2" text="text" /> : <spring:message code="message.helloworld2" text="text" />
</h3>

Current Locale : ${pageContext.response.locale}
 
</body>
</html>