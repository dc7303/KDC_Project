<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <style>
    h1 {
      font-size: 50px;
    }

    h1 b {
      color: #333;
    }

    div {
      margin: 20px;
    }
      
    .id .pw .button{
      margin: 10px;
    }

    .id input{
      height: 35px; 
      width: 220px;
    }
    
    .pw input{
      height: 35px; 
      width: 220px;
    }

    .button input{
      height:40px;
      width: 220px;
      background-color: #00280d;
      color: white;
    }

    </style>
    </head>
    <body>
    	<h1><b>KDC</b></h1>

        <form action="${pageContext.request.contextPath }/j_spring_security_check" method="post">
          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
          <div class="id">
            <input type="text" name="memberId" placeholder="  아이디"/>
          </div>
          <div class="pw">
            <input type="password" name="memberPwd" placeholder="  비밀번호">
          </div>
          <div class="button">
            <input type="submit" value="SignIn">
          </div>
        </form>
    </body>
</html>