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
  					<input class="setbutton" type="submit" value="가입하기" />

  					</td>
  				</tr>
  			</table>
  		</form>
  	</div>


  </center>
  
  
  <script type="text/javascript">
  	const jq = jQuery.noConflict();
  	
  	//ID유효성검사
  	jq(function() {
  	  const contextPath = '${pageContext.request.contextPath}';
  	  const csrfName = '${_csrf.headerName}';
  	  let csrfToken = '${_csrf.token}';
  	  
      const memberId = 'input[name=memberId]';				//아이디 Selector
      const memberPwd = 'input[name=memberPwd]';				//비밀번호 Selector
  	  const memberPwdConfirm = 'input[name=memberPwdConfirm]';//비밀번호확인 Selector
  	  const memberNickName = 'input[name=memberNickName]';	//닉네임 Selector
  	  const memberPhone = 'input[name=memberPhone]';		//전화번호 Selector
  	  const memberEmail = 'input[name=memberEmail]';		//Email Selector
  	  const authCode = 'input[name=authCode]';				//권한코드
  	  
  	  jq(memberId).on('keyup', function() {
  	    jq.ajax({
  	      url: '${pageContext.request.contextPath}/member/idCheck',
  	      type: 'post',
  	      beforeSend: function(xhr) {
  	        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
  	      },
  	      dataType: 'text',
  	      data: {
  	        memberId: $(memberId).val()
  	      },
  	      success: function(result) {
  	        $('.ajax').eq(0).text(result);
  	        if($(memberId).val() === '') {
  	          $('.ajax').eq(0).text('아이디를 입력하세요.');
  	        }
  	      },
  	      error: function(err) {
  	        console.log(result);
  	      }
  	    });
  	  });
  	  
  	  //패스워드 유효성체크
  	  jq(memberPwd).on('keyup', function() {
  	    jq.ajax({
  	      url: '${pageContext.request.contextPath}/member/pwdCheck',
  	      type: 'post',
  	      beforeSend: function(xhr) {   
              xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
  	      dataType: 'text',
  	      data: {
  	        memberPwd: $(memberPwd).val()
  	      },
  	      success: function(result) {
  	        $('.ajax').eq(1).text(result);
  	        if($(memberPwd).val() === '') {
  	          $('.ajax').eq(1).text('비밀번호입력');
  	          if($(memberPwdConfirm).val() === ''){
  	            $('.ajax').eq(2).text('비밀번호확인');
  	          }
  	        }else if($(memberPwd).val() !== $(memberPwdConfirm).val()) {
  	          $('.ajax').eq(2).text('비밀번호가 일치하지 않습니다.');
  	        }
  	      },
  	      error: function(err) {
  	        console.log(erro);
  	      }
  	    });
  	  });
  	  
  	  //패스워드 확인 매칭
  	  jq(memberPwdConfirm).on('keyup', function() {
  	    jq.ajax({
  	      url: '${pageContext.request.contextPath}/member/pwdConfirm',
  	      type: 'post',
  	      beforeSend : function(xhr) {   
              xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
  	      dataType: 'text',
  	      data: {
  	        memberPwd: $(memberPwd).val(),
  	        memberPwdConfirm: $(memberPwdConfirm).val()
  	      },
  	      success: function(result) {
  	        $('.ajax').eq(2).text(result);
  	        if($(memberPwdConfirm).val() === '' && $(memberPwd).val() === '')  {
  	          $('.ajax').eq(2).text('비밀번호 확인');
  	        }
  	      },
  	      error: function(err) {
  	        console.log("errorMsg: " +err)
  	      }
  	    });
  	  });
  	  
  	  //닉네임 체크
  	  jq(memberNickName).on('keyup', function() {
		jq.ajax({
  	      url: '${pageContext.request.contextPath}/member/nickNameCheck',
  	      type: 'post',
  	      beforeSend: function(xhr) {
  	        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
  	      },
  	      dataType: 'text',
  	      data: {
  	        memberNickName: $(memberNickName).val(),
  	      },
  	      success: function(result) {
  	        $('.ajax').eq(3).text(result);
  	        if($(memberNickName).val() === '') {
  	          $('.ajax').eq(3).text('닉네임입력');
  	        } 
  	      },
  	      error: function(err) {
  	        console.log(err);
  	      }
		});
  	  });
  	  
  	  //전화번호 체크
  	  jq(memberPhone).on('keyup', function() {
  		jq.ajax({
  		  url: '${pageContext.request.contextPath}/member/phoneCheck',
  		  type: 'post',
  		  beforeSend: function(xhr) {
  		    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
  		  },
  		  dataType: 'text',
  		  data: {
  		    memberPhone: $(memberPhone).val(),
  		  },
  		  success: function(result) {
  		    $('.ajax').eq(4).text(result);
  		    if($(memberPhone).val() === '') {
  		      $('.ajax').eq(4).text('전화번호체크');
  		    }
  		  },
  		  error: function(err) {
  		    console.log(result);
  		  }
  		});
  	  });
  	  
  	  //이메일 체크
  	  jq(memberEmail).on('keyup', function() {
		jq.ajax({
	      url: '${pageContext.request.contextPath}/member/emailCheck',
	      type: 'post',
	      beforeSend: function(xhr) {
	        xhr.setRequestHeader(csrfName, csrfToken);
	      },
	      dataType: 'text',
	      data: {
	        memberEmail: $(memberEmail).val(),
	      },
	      success: function(result) {
	        $('.ajax').eq(5).text(result);
	        if($(memberEmail).val() === '') {
	          $('.ajax').eq(5).text('이메일입력');
	        }
	      },
	      error: function(err) {
	        console.log(err);
	      }
		});
  	  });
  	  
  	  //권한코드 체크
  	  jq(authCode).on('keyup', function() {
  	    jq.ajax({
  	      url: '${pageContext.request.contextPath}/member/authCheck',
  	      type: 'post',
  	      beforeSend: function(xhr) {
  	        xhr.setRequestHeader(csrfName, csrfToken);
  	      },
  	      dataType: 'text',
  	      data: {
  	        authName: $(authCode).val(),
  	      },
  	      success: function(result) {
  			jq('.ajax').eq(6).text(result);
  			if(jq(authCode).val() === '') {
  			  jq('.ajax').eq(6).text('');
  			}
  	      },
  	      error: function(err) {
  	        console.log(err);
  	      }
  	    });
  	  });
  	});
  </script>
  </body>
</html>