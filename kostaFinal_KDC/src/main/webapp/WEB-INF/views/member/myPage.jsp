<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/lib/jquery-3.3.1.min.js"></script>
</head>
<body>
  <sec:authorize access="isAuthenticated()">
    <sec:authentication var="member" property="principal"/>
    <h1>회원정보 수정</h1>
    <form action="${pageContext.request.contextPath }/member/memberUpdate" method="post" id="updateForm">
    <!-- 토큰 전송 -->
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      ID : <input type="text" name="memberId" value="${member.memberId }" readonly="readonly"/><br/>
      PW : <input type="password" name="memberPwd"/>
      <span class="ajax">비밀번호입력</span><br/>
      
      PW Confirm : <input type="password" name="memberPwdConfirm"/>
      <span class="ajax">비밀번호 확인</span><br/>
      
      NAME : <input type="text" name="memberName" value="${member.memberName }"/>
      <span class="ajax">유저이름 입력</span><br/>
      
      NickName: <input type="text" name="memberNickName" value="${member.memberNickName }" readonly="readonly"/><br/>
      
      Birth : <input type="text" name="memberBirth" value="${member.memberBirth }"/>
      <span class="ajax">유저 생일 입력</span><br/>
      
      Phone : <input type="text" name="memberPhone" value="${member.memberPhone }"/>
      <span class="ajax">전화번호 입력</span><br/>
      
      Email : <input type="text" name="memberEmail" value="${member.memberEmail }"/>
      <span class="ajax">이메일 입력</span><br/>
      
      <input type="button" value="수정하기" class="setbutton"/>
    </form>
    
    <br/>
    
    <h1>회원탈퇴</h1>
    <form action="${pageContext.request.contextPath }/member/memberDelete" method="post">
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      ID : <input type="text" name="memberId" value="${member.memberId }" readonly="readonly"/><br/>
      <input type="button" value="탈퇴하기"/>
    </form>
  </sec:authorize>
  
  <input type="hidden" name="contextPath" value="${pageContext.request.contextPath }"/>
  <input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
  <input type="hidden" name="csrfToken" value="${_csrf.token }"/>
  <!-- 업데이트 유효성검사 -->
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/update-regex-check.js"></script>
</body>
</html>