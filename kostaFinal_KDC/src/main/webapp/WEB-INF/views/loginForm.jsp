<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:if test="${not empty requestScope.errorMessage}">
    	<span style="color:red">${requestScope.errorMessage}</span>
    </c:if>
  <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
    ID:<input type="text" name="userId"/><br/>
    PW:<input type="password" name="userPwd"/><br/>
    <input type="submit" value="login"/>
  </form>
</body>
</html>