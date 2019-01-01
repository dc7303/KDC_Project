<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

const jq = jQuery.noConflict();

//답장시,  메세지 제목 or 내용 입력 유효성체크
jq(function(){

  const messageTitle = 'input[name=messageTitle]';
  const messageContents = 'textarea[name=messageContents]';
  
  jq(document).on('click','#sendReplyMessage',function(){
    if(jq(messageTitle).val() === ''){
      alert("제목을 입력해주세요.");
      jq(messageTitle).focus();
      return false;
    }else if(jq(messageContents).val() === ''){
      alert("내용을 입력해주세요.");
      jq(messageContents).focus();
      return false;
    }else{
      return true;
    }
  })
})

</script>
</head>
<body>

<table align="center" border="0" cellpadding="5" cellspacing="2"
  width="100%" bordercolordark="white" bordercolorlight="black">
  <caption>메세지 답장</caption>

  <form name="writeForm" id="write" method="post" action="${pageContext.request.contextPath}/message/insert" >

    <table align="center" cellpadding="5" cellspacing="2" width="600" border="1">

      <tr>
        <td width="1220" height="20" colspan="2" bgcolor="#00cc00">
          <p align="center">
            <font color="white" size="3"><b> 메세지 작성 </b></font>
          </p>
        </td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">받는사람</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span style="font-size: 9pt;">
        
          <!-- 보낸사람(senderId)에게 답장하려면 보낸사람과 받는사람이 바뀌어야 함 --> 
          <input type="text" name="senderId" value="${requestScope.replyMessage.senderId}"
              size="30" readonly="readonly"></span></b></td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">쪽지제목</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span
            style="font-size: 9pt;"> <input type=text
              name="messageTitle" size="30"></span></b></td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">쪽지내용</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span style="font-size: 9pt;">
        <textarea name="messageContents"  cols="50" rows="10"></textarea></span></b></td>
      </tr>
      <tr>
        <td width="450" height="20" colspan="2" align="center"><b><span style="font-size: 9pt;"> 
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="전송" id="sendReplyMessage">
        <input type="button" value="취소" id="cancelWriteMessage" onclick="location.href='${pageContext.request.contextPath}/message/messageList'">
         </span></b></td>
      </tr>
    </table>
  
  </form>
</table>
  <hr>
  <div align=right>
    <span style="font-size: 9pt;">&lt;<a
      href="${pageContext.request.contextPath}/">마이페이지 홈</a>&gt;
    </span>
  </div>
  
  
  </body>
  </html>