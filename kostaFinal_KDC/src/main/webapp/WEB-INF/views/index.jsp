<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>myBatis, security 테스트</h1>
  <form action="${pageContext.request.contextPath }/insert" method="post">
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
    ID:<input type="text" name="userId" placeholder="id입력"/><br/>
    PW:<input type="password" name="userPwd" placeholder="pw입력"/><br/>
    NAME:<input type="text" name="userName" placeholder="이름"/><br/>
    <select name="auth">
      <option value="ROLE_ADMIN">ROLE_ADMIN</select><br/>
    </select>
    <input type="submit" value="가입"/>
  </form>
  
  <h3><a href="${pageContext.request.contextPath }/adminTest">admin Test</a></h3>
</body>
</html>