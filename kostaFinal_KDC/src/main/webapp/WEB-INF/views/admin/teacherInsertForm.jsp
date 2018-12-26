<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원가입 폼</h1>
<form action="${pageContext.request.contextPath }/admin/insertTeacher" method="post" >
<table border="1">
 <tr>
   <th>강사 id</th>
   <td><input type="text" name="memberId" ></td>
 </tr>
  <tr>
   <th>강사 비밀번호</th>
   <td><input type="password" name="memberPwd" ></td>
 </tr>
  <tr>
   <th>강사 이름</th>
   <td><input type="text" name="memberName" ></td>
 </tr>
  <tr>
   <th>닉네임</th>
   <td><input type="text" name="memberNickName" ></td>
 </tr>
 <tr>
   <th>생일</th>
   <td><input type="text" name="memberBirth" ></td>
 </tr>
 <tr>
   <th>휴대폰번호</th>
   <td><input type="text" name="memberPhone" ></td>
 </tr>
 <tr>
   <th>이메일</th>
   <td><input type="text" name="memberEmail" ></td>
 </tr>
 <tr>
   <th colspan="2">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <input type="submit" value="등록하기">
     <input type="reset" value="취소하기">
   </th>
 </tr>
</table>
</form>
</body>
</html>