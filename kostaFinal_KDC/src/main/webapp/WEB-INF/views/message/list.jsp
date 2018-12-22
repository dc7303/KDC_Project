<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

const jq = jQuery.noConflict();

//답장시, 메세지 보낸사람(senderId) 유효성 체크
jq(function(){
  jq(document).on('click','#replyMessage',function(){
    
    var senderId = jq(this).parent().children().eq(1).val();
    var receiverId = jq(this).parent().children().eq(2).val();
    
    jq.ajax({
      url:"${pageContext.request.contextPath}/message/checkId" , //서버요청주소
      type:"post" , //전송방식(get or post)
      dataType:"text", //서버가 보내주는 데이터타입(text,html,xml,json)
      data:"senderId="+senderId, //서버에게 보낼 parameter정보
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success:function(result){
        if(result=='0'){
          alert("삭제된 아이디 혹은 없는 아이디 입니다.");
          self.close();
        }else{
          location.href='${pageContext.request.contextPath}/message/messageReplyPage?senderId='+senderId+'&receiverId='+receiverId;
        }
        
      } , //성공했을때
      error:function(err){
        alert(err+" => 오류 발생")
      }  //실패했을때
    })
  })
  
  jq('input[name=deleteNumList]').on('click', function() {
    var deleteNumList = [];
    
    jq('input[name=checkNum]:checked').each(function(i) {
      deleteNumList.push(jq(this).val());
    });

	jq.ajax({
	  url: '${pageContext.request.contextPath}/message/messageSelectDelete',
	  type: 'post',
	  dataType: 'text',
	  beforeSend : function(xhr) {
      	xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
	  data: {
	    deleteNumList: deleteNumList,
	  },
	  success: function(result) {
	    location.reload();
	    console.log('성공');
	    
	  },
	  error: function(err) {
	    console.log('err : ' + err);
	  }
	});
  });
});
  


//체크박스 전체 선택 및 해제
jq(function(){
  jq('#checkBoxAll').click(function(){
    if(jq('#checkBoxAll').is(':checked')){
      jq('input[name=checkNum]').prop('checked',true);
    }else{
      jq('input[name=checkNum]').prop('checked',false);
    }
  })
})


</script>

</head>
<body>

<h5>안읽은 쪽지 : ${sessionScope.unReadCount}</h5>

  <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
  <table align="center" border="0" cellpadding="5" cellspacing="2"
    width="100%" bordercolordark="white" bordercolorlight="black">

    <caption> 쪽지함 LIST </caption>

    <tr>
      <td bgcolor="#00cc00">
        <p align="center">
          <font color="white"><b>
            <span style="font-size: 9pt;">
              <input type="checkbox" name="checkBoxAll" id="checkBoxAll" >
            </span></b></font>
        </p>
      </td>
      <td bgcolor="#00cc00">
        <p align="center">
          <font color="white"><b><span
              style="font-size: 9pt;">보낸사람</span></b></font>
        </p>
      </td>
      <td bgcolor="#00cc00">
        <p align="center">
          <font color="white" style="font-weight: bold;"><b><span
              style="font-size:9pt;">쪽지제목</span></b></font>
        </p>
      </td>
      <td bgcolor="#00cc00">
        <p align="center">
          <font color="white"><b><span
              style="font-size: 9pt;">전송일</span></b></font>
        </p>
      </td>
      <td bgcolor="#00cc00">
        <p align="center">
          <font color="white"><b><span
              style="font-size: 9pt;">답장</span></b></font>
        </p>
      </td>
      <td bgcolor="#00cc00">
        <p align="center">
          <font color="white"><b><span
              style="font-size: 9pt;">삭제</span></b></font>
        </p>
      </td>
    </tr>

    <c:choose>
      <c:when test="${empty requestScope.messageList}">
        <tr>
          <td colspan="3">
            <p align="center">
              <b><span style="font-size: 9pt;">받은 쪽지가 없습니다.</span></b>
            </p>
          </td>
        </tr>
      </c:when>
      <c:otherwise>
      <!-- 접속된 ID로  message list 출력 -->
        <c:forEach items="${requestScope.messageList}" var="message">
          <tr onmouseover="this.style.background='#eaeaea'"
            onmouseout="this.style.background='white'" id="messageTitles"> <!-- id="messageTitles" -->
            <td bgcolor="">
              <p align="center">
                <span style="font-size: 9pt;">
                  <input type="checkbox" name="checkNum"  value="${message.messageNum}"  id="${message.messageNum}">
                </span>
              </p>
            </td>
            <td bgcolor="">
              <p align="center">
                <span style="font-size: 9pt;">
                  ${message.senderId}</span>
              </p>
            </td>
            <td bgcolor="">
              <p>
                <a href="${pageContext.request.contextPath}/message/${message.messageNum}">
                    <!-- path variable RESTful -->
                    <!-- 읽은 메세지, 읽지않은 메세지 구분 -->
                    <c:choose>
                      <c:when test="${message.messageIsRead == 'FALSE'}">
                        <span style="font-size:13pt; font-weight:900;">${message.messageTitle}</span>
                      </c:when>
                      <c:otherwise>
                        <span style="font-size:9pt;">${message.messageTitle}</span>
                      </c:otherwise>
                    </c:choose>
                </a>
              </p>
            </td>
            <td bgcolor="">
              <p align="center">
                <span style="font-size: 9pt;">
                  ${message.messageDate}</span>
              </p>
            </td>
            <td bgcolor="">
              <p align="center">
                <span style="font-size: 9pt;"> 
                <!-- 답장 클릭시, ajax로 ID유뮤체크 후 답장 페이지로 이동  -->
                <input type="button" value="답장" id="replyMessage">
                <input type="hidden" name="senderId" value="${message.senderId}">
                <input type="hidden" name="receiverId " value="${message.receiverId }">
                </span>
              </p>
            </td>
            <td bgcolor="">
              <p align="center">
                <span style="font-size: 9pt;"> <input
                  type="button" value="삭제" id="deleteMessage"
                  onclick="location.href='${pageContext.request.contextPath}/message/delete?messageNum=${message.messageNum}'" ></span>
              </p>
            </td>
          </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </table>
  <input type="button" value="선택삭제" name="deleteNumList"/>
  <hr>
  <div align=right>
    <span style="font-size: 9pt;">&lt;<a
      href="${pageContext.request.contextPath}/">마이페이지 홈</a>&gt;
    </span>
  </div>

</body>
</html>


