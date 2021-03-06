<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/logincss.css" />    
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/jquery-ui-admin/jquery-ui.css">
<style>
  /* css 충돌로 picker month-selector와 year-selector font 컬러 없어지는 이슈해결 */
  .ui-datepicker-month, .ui-datepicker-year {
    color: black;
  }
</style>
<title></title>

<head>
  </head>
  <body>
  <center>
    
  	<div id="signup">
        

  		<form method="post" action="${pageContext.request.contextPath }/member/memberInsert" id="signUpForm">
          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
  			
  				
  					<h1 class="sign-title">회원가입</h1>
                    <p class="underline"></p>

  				<table cellspacing="0" align="center">
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
  					<td class="datepicker-lib"><input type="text" id="datepicker" autocomplete="off" name="memberBirth" placeholder="  1900.01.00"/></td>
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
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/lib/jquery-ui-admin/jquery-ui.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/memberInfoForm/signup-regex-check.js"></script>
  <script type="text/javascript">
    
    jq(function() {
      if('${requestScope.authTeacher}' != null){
        jq('input[name=authCode]').val('${requestScope.authTeacher}');
      }
      
      //생일 입력 datepicker
      jq( function() {
        jq( "#datepicker" ).datepicker({
          dateFormat: 'yy.mm.dd',
          changeMonth: true,
          changeYear: true,
          yearRange: '-100:+0',
          monthNamesShort: [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" ],
          dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ]
        });
      });
    });
  </script>
  </body>
</html>