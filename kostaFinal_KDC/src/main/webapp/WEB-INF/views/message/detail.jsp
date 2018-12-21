<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>메세지 상세</h1>
<h3>
보낸사람 : ${requestScope.messageDTO.senderId} <br><br>
메세지내용 : ${requestScope.messageDTO.messageContents} <br><br>
보낸날짜 : ${requestScope.messageDTO.messageDate} <br><br>
</h3>
</body>
</html>