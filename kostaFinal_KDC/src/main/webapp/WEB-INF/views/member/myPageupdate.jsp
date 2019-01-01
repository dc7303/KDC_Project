<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/logincss.css" />   
<title>Insert title here</title>
</head>
<body>
<center>




  <sec:authorize access="isAuthenticated()">
    <sec:authentication var="member" property="principal"/>
      <br/>
    
      <h2>회원정보 수정</h2>
       <p class="underline"></p>
    
    <form action="${pageContext.request.contextPath }/member/memberUpdate" method="post" id="updateForm">
    <!-- 토큰 전송 -->
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
    
    <div class="memberupdate">
     
       <table>
      <tr>
      <td>ID</td> 
      <td><input type="text" name="memberId" value="${member.memberId }" readonly="readonly"/></td> <br/>   
      </tr>
      
      <tr>
      <td>Password</td>
      <td><input type="password" name="memberPwd"/></td>
      <td><span class="ajax">비밀번호입력</span><br/></td>
      </tr>
      
      <tr>
      <td>Password 확인</td> 
      <td><input type="password" name="memberPwdConfirm"/></td>     
      
      <td><span class="ajax">비밀번호 확인</span><br/></td> 
       </tr>
      
      
      <tr>
      <td>이름</td>  
      <td><input type="text" name="memberName" value="${member.memberName }" readonly="readonly"/></td>      
       

      </tr>
      
      
      <tr>
      <td>닉네임</td> 
      <td><input type="text" name="memberNickName" value="${member.memberNickName }" /></td> 
            <td><span class="ajax">닉네임 입력</span><br/></td> 
       </tr>
      
           
       <tr>
      <td>생일</td>
      <td><input type="text" name="memberBirth" value="${member.memberBirth }"/></td>     
      
      <td><span class="ajax">유저 생일 입력</span><br/></td> 
       </tr>
       
       
      <tr>
      <td>전화번호</td> 
      <td><input type="text" name="memberPhone" value="${member.memberPhone }"/></td>      
       
      <td><span class="ajax">전화번호 입력</span><br/></td> 
      </tr>
      
      
       <tr>
      <td>Email </td>  
      <td><input type="text" name="memberEmail" value="${member.memberEmail }"/></td>    
       
      <td><span class="ajax">이메일 입력</span><br/></td> 
      </tr>
           
      </table>
     
      <input type="button" value="수정하기" class="mypage-button"/>
    </form>
    
       </div>
      <hr>

   
     
      <div class="memberdelete">
    
    <h2>회원탈퇴</h2>
     <p class="underline"></p>
    <form class="memberdelete-form" action="${pageContext.request.contextPath }/member/memberDelete" method="post">
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      ID  <input type="text" name="memberId" value="${member.memberId }" readonly="readonly"/><br/>
      <input type="button" value="탈퇴하기" class="mypage-button" />
      <p class="memberdelete-font">*회원탈퇴시 회원님의 정보가 삭제됩니다 </p>
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