<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript">


</script>
</head>
<body>
<h1>test</h1>


<a href="${pageContext.request.contextPath}/message/list?id=${sessionScope.userId}" name="messageList">전체메세지 출력</a> <br>
<a href="${pageContext.request.contextPath}/board/boardList?id=${sessionScope.userId}">전체게시물 출력</a> <br>
<a href="${pageContext.request.contextPath}/classRoom/classList?id=heejung">heejung클래스 전체 출력</a> <br>
<a href="${pageContext.request.contextPath}/classRoom/classCreate">클래스 생성</a> <br>


</body>
</html>