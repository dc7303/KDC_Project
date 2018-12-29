<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/logincss.css" />    
<title></title>
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
    <script>
    const jq = jQuery.noConflict();
    jq(function() {
      jq("input[value=비밀번호찾기]").click(function() {
        window.open("${pageContext.request.contextPath}/member/passwordSearch", "pop", "left=500,top=200,width=600,height=300,history=no,location=no,resizable=no,status=no,scrollbars=no,menubar=no");
      });  
    });
    
    </script>
    </head>
    <body>
    <center>
    	<h1 class="login-title"><b>KDC</b></h1>

        <form action="${pageContext.request.contextPath }/j_spring_security_check" method="post">
          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
          <div class="idpassword">
            <input type="text" name="memberId" placeholder="  아이디"/>  
            </div>
           <div class="idpassword">    
            <input type="password" name="memberPwd" placeholder="  비밀번호">
          </div>
          <div class="signin-button">
            <input type="submit" value="SignIn">
          </div>
          <div class="signin-button">
            <input type="button" value="비밀번호찾기">
          </div>
        </form>
        
    </body>
</html>