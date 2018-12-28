<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>

<script type="text/javascript">
const jq = jQuery.noConflict();

  jq(function() {
    
    jq("input[name=email]").keyup(function() {
    	//email Check
        jq.ajax({
          url : "${pageContext.request.contextPath}/member/memberByEmailCheck",
          type : "post",
          dataType : "text",
          beforeSend: function(xhr) {
 	         xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
 	      },
          data : {
            emailCheck : jq("input[name=email]").val()
          },
          success : function(result) {
            jq(".ajax").text(result);
            if ($(memberId).val() === '') {
              jq(".ajax").text("이메일을 입력하세요");
            }
          },
          error : function(err) {
            console.log(err)
          }
        });
    });
    
    jq("input[name=emailSend]").click(function() {
       var email = jq("input[name=email]").val();
  	    jq.ajax({
  	      url : "${pageContext.request.contextPath}/member/emailSend",
  	      type : "post",   
  	      dataType : "text", 
  	      beforeSend: function(xhr) {
  	         xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
  	      },
  	      data : "email="+email,
  	      success : function(result){   //성공했을 때
  	        alert("임시비밀번호 발급이 되었습니다.");
  	        window.close();   
  	      },
  	      error : function(err){   //실패했을 때
  	         alert("이메일을 다시 입력해주세요");
  	      }
      	});
  	
  	 });
    
    
  
});
</script>
</head>
<body>
<table>
<caption>
  <h1>비밀번호 찾기</h1>
</caption>
  <tr>
    <th id="">이메일 : </th>
    <td ><input type="text" name="email" placeholder="회원가입시 등록한 이메일 작성" style="width:200px"/></td>
    <td class="ajax"> 이메일을 입력하세요</td>
  </tr>
  <tr>
  <td></td>
  </tr>
  <tr >
    <td colspan="2"><input type="button" name="emailSend" value="임시 비밀번호 발급받기" style="margin-right: 10px"/><input type="button" value="닫기" onclick="window.close()"/></td>
  </tr>
</table>
</body>
</html>
