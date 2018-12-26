<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
#monkey-box{
 padding: 150px 0 0 0;
 text-align: center; 
}

#historyback a{
 text-decoration: none;
 color: gray;
}

</style>
</head>
<body>
<div id="monkey-box">
  <h1> ERROR </h1>
  <img alt="원숭이" src="${pageContext.request.contextPath }/resources/testimg/error/monkey.png">
  <h3>${exception.message}</h3>
  <h3 id="historyback"><a href="javascript:history.back();"> <- 뒤로 돌아가기 </a></h3>
</div>
    
</body>
</html>