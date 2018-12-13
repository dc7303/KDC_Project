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
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/lib/jquery-3.3.1.min.js"></script>
  </head>
  <body>
  <center>

  	<div id="signup">


  		<form method="post" action="${pageContext.request.contextPath }/member/memberInsert">
          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
  			<table cellspacing="0" align="center">
  				<caption>
  					<h3>회원가입</h3>

  				</caption>
  				<input type="hidden" name="command" value="insert">
  				<tr>
  					<td id="">ID</td>
  					<td ><input type="text" name="memberId" /></td>
            <td class="ajax"> ajax 영역</td>
  				</tr>



  				<tr>
  					<td>Password</td>
  					<td><input type="password" name="memberPwd" placeholder="  특수기호포함, 8자 이상 입력"/></td>
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
  					<td><input type="text" name="memberNickName" placeholder=""/></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>


  				<tr>
  					<td>생일</td>
  					<td><input type="text" name="memberBirth" placeholder="  1900.01.00"/></td>
  				</tr>

  				<tr>
  					<td>전화번호</td>
  					<td><input type="text" name="memberPhone" /></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>
  				<tr>
  					<td>Email</td>
  					<td><input type="text" name="memberEmail" /></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>
  				<tr>
  					<td>인증번호ex(ROLE_ADMIN)</td>
  					<td><input type="text" name="authCode" placeholder=" 인증번호를 입력하세요"/></td>
              <td class="ajax"> ajax: 인증번호가 틀렸음</td>
  				</tr>




  				<tr>
  					<td colspan="3" style="text-align: center; ">
  					<input class="setbutton" type="submit" value="가입하기" />

  					</td>
  				</tr>
  			</table>
  		</form>
  	</div>


  </center>
  
  
  <script type="text/javascript">
  	const jq = jQuery.noConflict();
  	
  	jq(function() {
  	  const memberPwd = 'input[name=memberPwd]';
  	  const memberPwdConfirm = 'input[name=memberPwdConfirm]';
  	  
  	  jq(memberPwd).on('keyup', function() {
  	    jq.ajax({
  	      url: '${pageContext.request.contextPath}/member/pwdCheck',
  	      type: 'post',
  	      beforeSend: function(xhr) {   
              xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
          },
  	      dataType: 'text',
  	      data: {
  	        memberPwd: $(memberPwd).val()
  	      },
  	      success: function(result) {
  	        $('.ajax').eq(1).text(result);
  	        if($(memberPwd).val() === ''){
  	          $('.ajax').eq(1).text('비밀번호입력');
  	        }
  	      },
  	      error: function(err) {
  	        console.log(erro);
  	      }
  	    });
  	  });
  	  
  	  
  	  jq(memberPwdConfirm).on('keyup', function() {
  	    jq.ajax({
  	      url: '${pageContext.request.contextPath}/member/pwdConfirm',
  	      type: 'post',
  	      beforeSend : function(xhr) {   
              xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
          },
  	      dataType: 'text',
  	      data: {
  	        memberPwd: $(memberPwd).val(),
  	        memberPwdConfirm: $(memberPwdConfirm).val()
  	      },
  	      success: function(result) {
  	        $('.ajax').eq(2).text(result);
  	        if($(pwdConfirm).val() === '')  {
  	          $('.ajax').eq(2).text('비밀번호 확인');
  	        }
  	      },
  	      error: function(err) {
  	        console.log("errorMsg: " +err)
  	      }
  	    });
  	  });
  	});
  </script>
  </body>
</html>