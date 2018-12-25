<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/board.css" />
    
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" /></noscript>

<SCRIPT language=javascript>
const jq = jQuery.noConflict();

jq(function(){
  
      jq("input[value=수정하기]").click(function(){
         
         jq("#requestForm").attr("action", "${pageContext.request.contextPath}/reply/updateForm");
         jq("#requestForm").submit();
      })
      
      
      jq("input[value=삭제하기]").click(function(){
         var yesOrNo = confirm("정말 삭제 하시겠습니까?");
         if(yesOrNo){
            jq("#requestForm").attr("action", "${pageContext.request.contextPath}/reply/delete");
            jq("#requestForm").submit();
         }
      });
      

    jq(".replyBoardLike").click(function() {
      var img1 = document.getElementById('thumbs_up');
      if (img1.src.indexOf('_black') == -1) { //_black이라는 단어가 존재하지 않으면 thumbs_up.png을 보여줌
        img1.src = img1.src.replace('.png', '_black.png');
      
        jq.ajax({
          url : "${pageContext.request.contextPath}/reply/replyBoardLike",   //서버 요청 주소
          type : "post",   //전송방식(get, post)
          dataType : "text",   //서버가 보내주는 데이터 타입(text, html, xml, json)
          beforeSend: function(xhr) {
             xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          data : "replyBoardPk=${requestScope.replyBoardPk}",   //서버에게 보낼 parameter정보
          success : function(result){   //성공했을 때
             //alert(result)   
          },
          error : function(err){   //실패했을 때
             alert(err+" => 오류 발생");
          }

       });
        
      } else {//_black이라는 단어가 존재하면
        jq.ajax({
          url : "${pageContext.request.contextPath}/reply/replyBoardLikeCancle",   //서버 요청 주소
          type : "post",   //전송방식(get, post)
          dataType : "text",   //서버가 보내주는 데이터 타입(text, html, xml, json)
          beforeSend: function(xhr) {
             xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          data : "replyBoardPk=${requestScope.replyBoardPk}",   //서버에게 보낼 parameter정보
          success : function(result){   //성공했을 때
             //alert(result)   
          },
          error : function(err){   //실패했을 때
             alert(err+" => 오류 발생");
          }
         
       });
        
        img1.src = img1.src.replace('_black.png', '.png');
        
      }
    });

    jq(".replyBoardDisLike").click(function() {
      var img1 = document.getElementById('thumbs_down');
      if (img1.src.indexOf('_black') == -1) { //_black이라는 단어가 존재하지 않으면 thumbs_down.png을 보여줌
        img1.src = img1.src.replace('.png', '_black.png');
        jq.ajax({
          url : "${pageContext.request.contextPath}/reply/replyBoardDisLike",   //서버 요청 주소
          type : "post",   //전송방식(get, post)
          dataType : "text",   //서버가 보내주는 데이터 타입(text, html, xml, json)
          beforeSend: function(xhr) {
             xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          data : "replyBoardPk=${requestScope.replyBoardPk}",   //서버에게 보낼 parameter정보
          success : function(result){   //성공했을 때
             //alert(result)   
          },
          error : function(err){   //실패했을 때
             alert(err+" => 오류 발생");
          }
         
       });
        
      } else {
        jq.ajax({
          url : "${pageContext.request.contextPath}/reply/replyBoardLikeCancle",   //서버 요청 주소
          type : "post",   //전송방식(get, post)
          dataType : "text",   //서버가 보내주는 데이터 타입(text, html, xml, json)
          beforeSend: function(xhr) {
             xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          data : "replyBoardPk=${requestScope.replyBoardPk}",   //서버에게 보낼 parameter정보
          success : function(result){   //성공했을 때
             //alert(result)   
          },
          error : function(err){   //실패했을 때
             alert(err+" => 오류 발생");
          }
         
       });
        
        img1.src = img1.src.replace('_black.png', '.png');
      }
    });

  });
</script>
  </head>

<body>
<table>

       <thead>
          <tr class="titel-color">
            <td colspan="6">글제목</td>
            <td>글쓴이</td>
            <td>등록날짜</td>
            <td>좋아요</td>
            <td>조회수</td>
          </tr>
        </thead>

<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
    <tr>
      <td colspan="6">
      <span>${replyBoardDTO.replyBoardTitle}</span>
      </td>
      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>

      <!-- 여기부터 -->
      
      
      <td>
      <c:choose>
      <c:when test="${replyBoardDTO.updown.isUp==true}">
      <div class="replyBoardLike"><img src="${pageContext.request.contextPath}/resources/assets/img/thumbs_up_black.png" id="thumbs_up" onclick="window.location.reload()"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/thumbs_down.png"></div>
      </c:when>
      <c:when test="${replyBoardDTO.updown.isUp==false}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/thumbs_up.png"></div><br/>
      <div class="replyBoardDisLike"><img src="${pageContext.request.contextPath}/resources/assets/img/thumbs_down_black.png" id="thumbs_down" onclick="window.location.reload()"></div>
      </c:when>      
      <c:otherwise>
      <div class="replyBoardLike"><img src="${pageContext.request.contextPath}/resources/assets/img/thumbs_up.png" id="thumbs_up" onclick="window.location.reload()"></div><br/>
      <div class="replyBoardDisLike"><img src="${pageContext.request.contextPath}/resources/assets/img/thumbs_down.png" id="thumbs_down" onclick="window.location.reload()"></div>     
      </c:otherwise>
      </c:choose>
      </td>
      
      <!-- 여기까지 -->

      <td>
      <span>${replyBoardDTO.replyBoardViews}</span>
      </td>
    </tr>
    <tr>
      <td class="tech-content" colspan="10">
      <span>${replyBoardDTO.replyBoardContents}</span>
      </td>
    </tr>
    <tr>
   
      <td colspan="10">
      <span>${replyBoardDTO.hashTag.hashTagName}</span>
      </td>
    </tr>

</c:when>
</c:choose>
</c:forEach>

    <tr class="titel-color">
    <td>댓글번호</td>
    <td>멘션</td>
    <td colspan="4">댓글내용</td>
    <td>댓글작성자</td>
    <td>수정</td>
    <td>삭제</td>
    <td>신고</td>
    </tr>
        
<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardPk==replyBoardReplyPk}">
<form id="replyUpdate" method="post" action="${pageContext.request.contextPath}/reply/replyUpdate">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      <td>
      <span>${state.count-1}</span>
      </td>
      <td>
      <span><input type="text" value="${replyBoardDTO.mentionNickName}"></span>
      </td>
      <td colspan="4">
      <span><input type="text" name="replyBoardContents" value="${replyBoardDTO.replyBoardContents}"></span>
      </td>      


      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      
      <td> <!-- 여기에다가 수정된 댓글내용 저장되도록 하면됨 -->
        <input type="hidden" name="replyBoardPk" value="${requestScope.replyBoardPk}"/>
        <input type="hidden" name="classification" value="${requestScope.classification}"/>
        <input type="hidden" name="replyBoardReplyNo" value="${replyBoardDTO.replyBoardPk}"/>
        <input type="submit" value="저장">
      </td>
</form>

      <td><span> <!-- 현재 수정중인 댓글이 삭제가 되고싶은데 잘안됨 ㅜㅜ -->
          <form id="replyUpdateForm" method="post" action="${pageContext.request.contextPath}/reply/replyDelete">
            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
            <input type="hidden" name="state" value="true"/>
            <input type="hidden" name="replyBoardPk" value="${requestScope.replyBoardPk}"/>
            <input type="hidden" name="classification" value="${requestScope.classification}"/>
            <input type="hidden" name="replyBoardReplyPk" value="${replyBoardDTO.replyBoardPk}"/>            
            <input type="submit" value="삭제">            
          </form>
        </span>
      </td>     
      
      <td>
      <input type="button" value="신고">
      </td>

</c:when>
<c:when test="${replyBoardDTO.replyBoardReplyNo>0}">
    <tr>
      <td>
      <span>${state.count-1}</span>
      </td>
      <td>
      <span id="mentionNickName">${replyBoardDTO.mentionNickName}</span>
      </td>
      <td>
      <span id="replyBoardContents">${replyBoardDTO.replyBoardContents}</span>
      </td>      
      <td>
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>
      <td>
      <span>${replyBoardDTO.updown.isUp}</span>
      </td>
      
      <!-- 여기부터 -->
      
      <td>
      <c:choose>
      <c:when test="${replyBoardDTO.updown.isUp==true}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_down.png"></div>
      </c:when>
      <c:when test="${replyBoardDTO.updown.isUp==false}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_down.png"></div>
      </c:when>      
      <c:otherwise>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_down.png"></div>     
      </c:otherwise>
      </c:choose>
      </td>
      
      <!-- 여기까지 -->      
      
      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      
      <td><span>             
        <form id="replyUpdateForm" method="post" action="${pageContext.request.contextPath}/reply/replyUpdateForm">
          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
          <input type="hidden" name="state" value="true"/>
          <input type="hidden" name="replyBoardPk" value="${replyBoardDTO.replyBoardReplyNo}"/>
          <input type="hidden" name="classification" value="${requestScope.classification}"/>
          <input type="hidden" name="replyBoardReplyPk" value="${replyBoardDTO.replyBoardPk}"/>      
          <input type="submit" value="수정">
        </form>
      </span>
      </td>
      
        <td><span>
          <form id="replyDeleteForm" method="post" action="${pageContext.request.contextPath}/reply/replyDelete">
            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
            <input type="hidden" name="state" value="true"/>
            <input type="hidden" name="replyBoardPk" value="${requestScope.replyBoardPk}"/>
            <input type="hidden" name="classification" value="${requestScope.classification}"/>
            <input type="hidden" name="replyBoardReplyPk" value="${replyBoardDTO.replyBoardPk}"/>            
            <input type="submit" value="삭제">            
          </form>
        </span>
        </td>
      
      <td>
        <span><input type="submit" value="신고"></span>
      </td>
    </tr>
    
</c:when>
</c:choose>
</c:forEach>

<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
   <form name="replyWriteForm" method="post" action="${pageContext.request.contextPath}/reply/replyInsert?classification=${requestScope.classification}&replyBoardPk=${requestScope.replyBoardPk}">
   <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
     
      <tr>
        <td>${replyBoardDTO.replyNum+1}</td>
        <td><input type=text value="@" name="replyBoardMention" style="width:150px;"></td>
        <td colspan="6"><input type=text placeholder="댓글내용입력" name="replyBoardContents"></td>
        <td>본인닉네임표출</td>
        <td><input type=submit value="등록" ></td>
      </tr>
      
    </form>
</c:when>
</c:choose>
</c:forEach>

    <tr>
      <td colspan="8" height="20" colspan="4" align="center" valign="middle">
      <!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다. -->
      <form name="requestForm" method=post  id="requestForm">
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      
<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">
<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
      <input type=hidden name="replyBoardPk" value="${replyBoardDTO.replyBoardPk}">
</c:when>
</c:choose>
</c:forEach>

        <input type=hidden name="state" value="true">
        <input type=hidden name="classification" value="${requestScope.classification}">
        <input type=button value="수정하기" >
        <input type=button value="삭제하기" >
      </form>
      </td>
    </tr>
</table>

<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>


</body>