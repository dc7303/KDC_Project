<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/logincss.css" />   
<title>Insert title here</title>
</head>
<body>
<center>

 <sec:authorize access="isAuthenticated()">
    <sec:authentication var="member" property="principal"/>

    <div class="memberdelete">
    
    <h2>회원탈퇴</h2>
     <p class="underline"></p>
    <form action="${pageContext.request.contextPath }/member/memberDelete" method="post">
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      ID  <input type="text" name="memberId" value="${member.memberId }" readonly="readonly"/><br/>
      <input type="button" value="탈퇴하기" class="mypage-button" />
    </form>
    
    </div>

    
    
  </sec:authorize>
  
  <input type="hidden" name="contextPath" value="${pageContext.request.contextPath }"/>
  <input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
  <input type="hidden" name="csrfToken" value="${_csrf.token }"/>
  <!-- 업데이트 유효성검사 -->
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/memberInfoForm/update-regex-check.js"></script>
</center>
</body>
</html>