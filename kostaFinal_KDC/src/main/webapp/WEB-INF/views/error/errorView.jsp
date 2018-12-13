<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
#monkey-box{
 text-align: center; 
}
</style>
</head>
<body>
<div id="monkey-box">
  <img alt="원숭이" src="${pageContext.request.contextPath }/resources/testimg/error/monkey.png">
  <h3>${exception.message}</h3>
</div>
    
</body>
</html>