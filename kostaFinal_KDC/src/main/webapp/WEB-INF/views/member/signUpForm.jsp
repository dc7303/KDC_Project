<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <style>



  table {
      margin-left: 10px;
  	width: 800px;
  }

  td, th {
  	padding: 10px;
  	font-size: 15px;
  }
  tr td input { height: 40px; width: 400px}

  .setbutton {
  		height: 50px;
  		width: 450px;
  		background-color: #00280d;
  		color: white;}

  td, .ajax {
           width:200px; height:30px;}
  </style>
  </head>
  <body>
  <center>
    
  	<div id="signup">
        

  		<form method="post" action="${pageContext.request.contextPath }/member/memberInsert" id="signUpForm">
          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
  			<table cellspacing="0" align="center">
  				<caption>
  					<h3>회원가입</h3>

  				</caption>
  				<input type="hidden" name="command" value="insert">
  				<tr>
  					<td id="">ID</td>
  					<td ><input type="text" name="memberId" placeholder="영문, 숫자로만 6~12자리 입력" /></td>
            <td class="ajax"> 아이디를 입력하세요</td>
  				</tr>



  				<tr>
  					<td>Password</td>
  					<td><input type="password" name="memberPwd" placeholder="숫자, 영문, 특수기호 포함 8자리 이상"/></td>
              <td class="ajax"> 비밀번호입력 </td>
  				</tr>

          <tr>
  					<td>Password 확인</td>
  					<td><input type="password" name="memberPwdConfirm" /></td>
              <td class="ajax"> 비밀번호확인 </td>
  				</tr>

          <tr>
  					<td>이름</td>
  					<td><input type="text" name="memberName" /></td>
  				</tr>

          <tr>
  					<td>닉네임</td>
  					<td><input type="text" name="memberNickName" placeholder="2~8자리 입력"/></td>
              <td class="ajax"> 닉네임임력 </td>
  				</tr>


  				<tr>
  					<td>생일</td>
  					<td><input type="text" name="memberBirth" placeholder="  1900.01.00"/></td>
  				</tr>

  				<tr>
  					<td>전화번호</td>
  					<td><input type="text" name="memberPhone" placeholder="'-' 제외하고 입력"/></td>
              <td class="ajax"> 전화번호체크 </td>
  				</tr>
  				<tr>
  					<td>Email</td>
  					<td><input type="text" name="memberEmail" /></td>
              <td class="ajax"> 이메일입력 </td>
  				</tr>
  				<tr>
  					<td>인증번호ex(ROLE_ADMIN)</td>
  					<td><input type="text" name="authCode" placeholder=" 인증번호를 입력하세요"/></td>
              <td class="ajax"></td>
  				</tr>




  				<tr>
  					<td colspan="3" style="text-align: center; ">
  					<input class="setbutton" type="button" value="가입하기" />

  					</td>
  				</tr>
  			</table>
  		</form>
  	</div>


  </center>
  
  <input type="hidden" name="contextPath" value="${pageContext.request.contextPath }"/>
  <input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
  <input type="hidden" name="csrfToken" value="${_csrf.token }"/>
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/memberInfoForm/signup-regex-check.js"></script>
  </body>
</html>