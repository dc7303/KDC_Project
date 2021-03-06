<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<script type="text/javascript">

const jq = jQuery.noConflict();

//답장시, 쪽지 보낸사람(senderId) 유효성 체크
jq(function(){
  jq(document).on('click','#replyMessage',function(){
    
    var senderId = jq(this).parent().children().eq(1).val();
    
    jq.ajax({
      url:"${pageContext.request.contextPath}/message/checkId" , //서버요청주소
      type:"post" , //전송방식(get or post)
      dataType:"text", //서버가 보내주는 데이터타입(text,html,xml,json)
      data:"senderId="+senderId, //서버에게 보낼 parameter정보
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success:function(result){
        if(result === null || result === ''){
          alert("삭제된 아이디 혹은 없는 아이디 입니다.");
          self.close();
        }else{
          window.open("${pageContext.request.contextPath}/message/messageReplyPage?senderId=" + result, "pop", "left=500,top=200,width=700,height=600,history=no,location=no,resizable=no,status=no,scrollbars=no,menubar=no");
        }
      } , //성공했을때
      error:function(err){
        alert(err+" => 오류 발생")
      }  //실패했을때
    });
  });
})
</script>
</head>
<body>

<table align="center" border="0" cellpadding="5" cellspacing="2"
  width="800px" bordercolordark="white" bordercolorlight="black">

    <table align="center" cellpadding="5" cellspacing="2" width="600" border="1" style="margin: auto; width: 800px;">

      <tr>
        <td width="1220" height="20" colspan="2" bgcolor="white">
          <p align="center">
            <font color="#434343" size="3"><b> 쪽지 확인 </b></font>
          </p>
        </td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">보낸사람</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span style="font-size: 9pt;">
          <input type="text" name="senderId" value="${requestScope.messageDTO.senderId}"
              size="30" readonly="readonly"></span></b></td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">쪽지제목</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span
            style="font-size: 9pt;"> 
            <input type="text" value="${requestScope.messageDTO.messageTitle}" readonly="readonly" name="messageTitle"  size="30"></span></b></td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">쪽지내용</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span style="font-size: 9pt;">
         <textarea name="messageContents" cols="50" rows="10" readonly="readonly" >${requestScope.messageDTO.messageContents}</textarea></span></b>
         </td>
      </tr>
      <tr>
        <td width="450" height="20" colspan="2" align="center"><b><span style="font-size: 9pt;"> 
        <input type="button" value="답장" id="replyMessage" >
        <input type="hidden" name="senderId" value="${requestScope.messageDTO.senderId}">
        <input type="hidden" name="receiverId " value="${requestScope.messageDTO.receiverId }">
         </span>
         <span style="font-size: 9pt;"> 
         <input type="button" value="뒤로" id="cancelWriteMessage" onclick="location.href='${pageContext.request.contextPath}/message/messageListNoPaging'">
         </span>
         </b></td>
      </tr> 
    </table>
    
</table>
  <hr>
  <div align=right>
    <span style="font-size: 9pt;">&lt;<a
      href="${pageContext.request.contextPath}/">홈으로 이동</a>&gt;
    </span>
  </div>
  
  
  </body>
  </html>