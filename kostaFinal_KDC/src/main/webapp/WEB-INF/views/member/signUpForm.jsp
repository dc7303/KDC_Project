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
  	  
  	  //submit 
  	  jq('.setbutton').on('click', function() {
  	    
  	    var idRegex = /^[a-zA-Z0-9_]{5,12}$/;								//아이디 유효성 정규식
  		var pwdRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;		//비밀번호 유효성 정규식
  		var nickNameRegex = /^[a-zA-Z0-9가-힣]{1,8}$/;						//닉네임 유효성 정규식
  		var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/; //이메일 유효성 정규식
  		var phoneRegex = /^[0-9]*$/;										//핸드폰 번호 유효성 정규식
  		
  		
  		//아이디 유효성검사
  	    if(!idRegex.test(jq(memberId).val())) {
  	      alert("ID를 확인해주세요.");
  	      jq(memberId).focus();
  	      return;
  	    
  	    //아이디 중복여부검사
  	    }else if($('.ajax').eq(0).text() === '이미 존재하는 아이디입니다.') {
  	      alert("이미 존재하는 아이디입니다.");
  	      jq(memberId).focus();
  	      return;
  	      
  	    //비밀번호 유효성 검사
  	    }else if(!pwdRegex.test(jq(memberPwd).val())) {
  	      alert("비밀번호를 확인해주세요.");
  	      jq(memberPwd).val('');
  	      jq(memberPwd).focus();
  	      return;
  	    
  	    //비밀번호 확인 검사
  	    }else if($(memberPwd).val() !== $(memberPwdConfirm).val()) {
  	      alert("비밀번호가 일치하지 않습니다.");
  	      jq(memberPwdConfirm).val('');
  	      jq(memberPwdConfirm).focus();
  	      return;
  	      
  	    //이름검사
  	    }else if($('input[name=memberName]').val() === '') {
  	      alert("이름을 입력해주세요.");
  	      jq('input[name=memberName]').focus();
  	      return;
  	      
  	    //닉네임검사
  	    }else if(!nickNameRegex.test(jq(memberNickName).val())) {
  	      alert('사용할 수 없는 닉네임입니다.');
  	      jq(memberNickName).focus();
  	      return;
  	      
  	    //닉네임 중복검사  
  	    }else if($('.ajax').eq(3).text() === '이미 사용중인 닉네임입니다.'){
  	      alert('이미 사용중인 닉네임입니다.')
  	      
  	    //생일검사
  	    }else if(jq('input[name=memberBirth]').val() === '') {
  	      alert('생일을 입력해주세요.');
  	      jq('input[name=memberBirth]').focus();
  	      return;
  	    
  	    //전화번호 검사
  	    }else if(!phoneRegex.test(jq(memberPhone).val()) || jq(memberPhone).val() === ''){
  	      alert('전화번호를 확인해주세요.');
  	      jq(memberPhone).focus();
  	      return;
  	      
  	    //이메일검사
  	    }else if(!emailRegex.test(jq(memberEmail).val())){
  	      alert('이메일을 확인해주세요.');
  	      jq(memberEmail).focus();
  	      return;
  	      
  	    }
		
  		jq('#signUpForm').submit();
  		
  	  });
  	});
  </script>
  </body>
</html>