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
  <sec:authorize access="isAuthenticated()">
	<sec:authentication var="member" property="principal" />
    <h2>
      ${member.memberId } 어서오십쇼.
    </h2>
    <a href="${pageContext.request.contextPath }/member/myPage">마이페이지</a><br/>
    <form action="${pageContext.request.contextPath}/member/logout" method="post">
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      <input type="submit" value="로그아웃"/>
    </form>
  </sec:authorize>
  <h1>Index입니다</h1>
  
<a href="${pageContext.request.contextPath}/message/list?id=${sessionScope.userId}" name="messageList">전체메세지 출력</a> <br>
<a href="${pageContext.request.contextPath}/board/boardList?id=${sessionScope.userId}">전체게시물 출력</a> <br>
<a href="${pageContext.request.contextPath}/classRoom/classList?id=heejung">heejung클래스 전체 출력</a> <br>
<a href="${pageContext.request.contextPath}/classRoom/classCreate">클래스 생성</a> <br>
  
  <a href="admin/selectMember">관리자 페이지로</a>
</body>
</html>