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
  const emailCheck = 'input[name=email]';       // Email Check

  //email Check
    jq(emailCheck).on('keyup', function() {
      jq.ajax({
        url : '${pageContext.request.contextPath }/member/passwordSearch',
        type : 'post',
        beforeSend : function(xhr) {
          xhr.setRequestHeader($(csrfName).val(), $(csrfToken).val());
        },
        dataType : 'text',
        data : {
          emailCheck : $(emailCheck).val()
        },
        success : function(result) {
          $('.emailCheck').text(result);
          if ($(memberId).val() === '') {
            $('.emailCheck').val('이메일을 입력하세요');
          }
        },
        error : function(err) {
          console.log(err);
        }
      });
    });
  
});
</script>
</head>
<body>
<h1>비밀번호 찾기</h1>
  <form action="${pageContext.request.contextPath}/member/emailSend" method="post">
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
               이메일 : <input type="text" name="email" placeholder="회원가입시 등록한 이메일 작성" style="width:200px"/>
       <span class="emailCheck"></span>
       <input type="hidden" name="subject"><br>
       <input type="hidden" name="content">
    <br><br>
    <input type="submit" value="임시 비밀번호 발급받기"/>
    
  </form>

</body>
</html>
