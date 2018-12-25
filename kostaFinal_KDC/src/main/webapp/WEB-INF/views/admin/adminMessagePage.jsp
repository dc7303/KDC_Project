<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

	const jq = jQuery.noConflict();
	
	jq(function(){
	  //slide toggle event
	  jq(".kk").on("click",function(){
	    var stateNum = jq(this).attr('name');
	    jq(".kkk").not($("opener" + stateNum)).slideUp("slow");
	    jq("#opener" + stateNum).slideToggle("slow")
	  });
	  
	  //답장
	  jq(document).on('click', '#replyMessage', function() {
		  let value = jq(this).parent().children().eq(0).val();
		  window.open("messageForm?senderId="+value,"a","width=400, height=300, left=100, top=50");
      });
	  
	  //삭제
	  jq(document).on('click', '#deleteMessage', function() {
	    let messageNum = jq(this).parent().children().eq(0).val();
	    location.href = '${pageContext.request.contextPath}/admin/deleteMessage?messageNum=' + messageNum;

	  });
	})
	
</script>

<style> 
th>div {
  padding: 50px;
  text-align: center;
  display: none;
}

body {
    height: auto;
}
</style>

<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption>쪽지함 LIST</caption>
   
   <tr>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">보낸사람</span></b></font></p> <!-- modelNum, modelName, price -->
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">쪽지제목</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">전송일</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">답장</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">삭제</span></b></font></p>
        </td>
    </tr>
    <c:choose>
    <c:when test="${empty requestScope.messageList}">
   <tr>
        <td colspan="3">
            <p align="center"><b><span style="font-size:9pt;">받은 쪽지가 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
    <c:forEach items="${requestScope.messageList}" var="messageList" varStatus="state">
     
          <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${messageList.senderId}</span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
               <a href="#" class="kk" name="${state.count}">
                  ${messageList.messageTitle}
               </a>
               </span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${messageList.messageDate}</span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  <input type="hidden" name="senderId" value="${messageList.senderId}"/>
                  <input type="button" value="답장" id="replyMessage"></span></p>
              </td>
                  <%-- location.href='${pageContext.request.contextPath }/admin/reply/${messageList.senderId}' --%>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  <input type="hidden" name="messageNum" value="${messageList.messageNum}"/>
                  <input type="button" value="삭제" id="deleteMessage"></span></p>
              </td>
          </tr>
        
      <tr>
        <th colspan="5">
          <div id="opener${state.count}" class="kkk">${messageList.messageContents }</div>
        </th>
      </tr>
      
    </c:forEach>
   </c:otherwise>
    </c:choose>
</table>
<hr>
<%-- <div align=right>
<span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/message/insert">글쓰기</a>&gt;</span></div>
<span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/">홈으로</a>&gt;</span></div> --%>
