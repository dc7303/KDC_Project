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



  <body>
  <center>

  	<div id="signup">

  		<form method="post" action="frontUserInfo">
  			<table cellspacing="0" align="center">
  				<caption>
  					<h3>회원가입</h3>

  				</caption>
  				<input type="hidden" name="command" value="insert">
  				<tr>
  					<td id="">ID</td>
  					<td ><input type="text" name="userId" /></td>
            <td class="ajax"> ajax 영역</td>
  				</tr>



  				<tr>
  					<td>Password</td>
  					<td><input type="password" name="userPwd" placeholder="  특수기호포함, 8자 이상 입력"/></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>

          <tr>
  					<td>Password 확인</td>
  					<td><input type="password" name="userPwd" /></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>

          <tr>
  					<td>이름</td>
  					<td><input type="text" name="userName" /></td>
  				</tr>

          <tr>
  					<td>닉네임</td>
  					<td><input type="text" name="userName" placeholder=""/></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>


  				<tr>
  					<td>생일</td>
  					<td><input type="password" name="userBirth" placeholder="  1900.01.00"/></td>
  				</tr>

  				<tr>
  					<td>전화번호</td>
  					<td><input type="text" name="userPhone" /></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>
  				<tr>
  					<td>Email</td>
  					<td><input type="text" name="userAddr" /></td>
              <td class="ajax"> ajax 영역 </td>
  				</tr>
  				<tr>
  					<td>인증번호</td>
  					<td><input type="text" name="userEmail" placeholder=" 인증번호를 입력하세요"/></td>
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
  </body>
</html>