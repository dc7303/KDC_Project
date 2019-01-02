<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

  <head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />

    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" /></noscript>

  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-code-snippet/dist/tui-code-snippet.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/markdown-it/dist/markdown-it.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/to-mark/dist/to-mark.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/codemirror/lib/codemirror.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/highlightjs/highlight.pack.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/squire-rte/build/squire-raw.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-editor/dist/tui-editor-Editor.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/codemirror/lib/codemirror.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/highlightjs/styles/github.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-editor/dist/tui-editor.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-editor/dist/tui-editor-contents.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main2.js"></script>
<style type="text/css">
.replyUpdateImg{
  height: 50px;
  width: 50px;
}
</style>
<script>  
const jq = jQuery.noConflict();
jq(function() {
	jq('input[value=등록]').click(function() {
	  if(jq('input[name=replyContents]').val().trim()===''){
	    alert('댓글을 입력해주세요');
	    jq('input[name=replyContents]').focus();
	    return;
	  }
	jq('#replyWriteForm').submit();
  });
	
	
    //viewer 세팅
    var contents = jq('#detail-description').val();
	var editor = tui.Editor.factory({
      el: document.querySelector('#viewer-section'),
      viewer: true,
      height: '500px',
      initialValue: contents
	});
	
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
          data : "replyBoardPk=${requestScope.replyBoardPk}&memberId=${requestScope.memberId}",
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
          data : "replyBoardPk=${requestScope.replyBoardPk}&memberId=${requestScope.memberId}",   //서버에게 보낼 parameter정보
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
          data : "replyBoardPk=${requestScope.replyBoardPk}&memberId=${requestScope.memberId}",   //서버에게 보낼 parameter정보
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
          data : "replyBoardPk=${requestScope.replyBoardPk}&memberId=${requestScope.memberId}",   //서버에게 보낼 parameter정보
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
<c:choose>
    <c:when test="${requestScope.classification eq 'tech'}">
    <h2 class="notice-title">Tech Q&A</h2>
    <p class="underline-board"></p>
    </c:when>
    <c:when test="${requestScope.classification eq 'lib'}">
    <h2 class="notice-title">Tech 공유 게시판</h2>
    <p class="underline-board"></p>
    </c:when> 
    <c:when test="${requestScope.classification eq 'study'}">
    <h2 class="notice-title">스터디모집</h2>
    <p class="underline-board"></p>
    </c:when> 
  </c:choose>
  
<table>
       <thead>
          <tr class="titel-color">
            <td>글쓴이</td>
            <td colspan="4">글제목</td>
            <td>등록날짜</td>
            <sec:authorize access="isAuthenticated()">
              <td colspan="2">좋아요</td>
            </sec:authorize>
              <td>조회수</td>
            <sec:authorize access="isAuthenticated()">
              <td>신고</td>
            </sec:authorize>
          </tr>
        </thead>

<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
    <tr>
      <td>
        
        <a id="mentionNickName${state.count}" style="cursor: pointer;">${replyBoardDTO.member.memberNickName}</a>
        <script>
              jq("#mentionNickName${state.count}").click(function() {
                window.open("${pageContext.request.contextPath }/message/messageReplyPage?senderId=${replyBoardDTO.replyBoardWriterId}", "pop", "left=500,top=200,width=900,height=700,history=no,location=no,resizable=no,status=no,scrollbars=no,menubar=no")
              });
        </script>
        
        <!-- <span>${replyBoardDTO.member.memberNickName}</span> -->
      </td>
      
      <td colspan="4">
      <span>${replyBoardDTO.replyBoardTitle}</span>
      </td>
      
      <td style="width: 130px">
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>
      
      <!-- 여기부터 -->
      <sec:authorize access="isAuthenticated()">
        <td>
          <c:choose>
            <c:when test="${replyBoardDTO.updown.isUp==true}">
            <div class="replyBoardLike"><img src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/thumbs_up_black.png" style="cursor: pointer;" id="thumbs_up" onclick="window.location.reload()"></div><br/>
            <div><img src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/thumbs_down.png"></div>
            </c:when>
            <c:when test="${replyBoardDTO.updown.isUp==false}">
            <div><img src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/thumbs_up.png"></div><br/>
            <div class="replyBoardDisLike"><img src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/thumbs_down_black.png" style="cursor: pointer;" id="thumbs_down" onclick="window.location.reload()"></div>
            </c:when>      
            <c:otherwise>
            <div class="replyBoardLike"><img src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/thumbs_up.png" style="cursor: pointer;" id="thumbs_up" onclick="window.location.reload()"></div><br/>
            <div class="replyBoardDisLike"><img src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/thumbs_down.png" style="cursor: pointer;" id="thumbs_down" onclick="window.location.reload()"></div>     
            </c:otherwise>
          </c:choose>
        </td>
        <td>
          <span>${replyBoardDTO.likeNum}</span>
        </td>
      </sec:authorize>
      <!-- 여기까지 -->
      
      <td>
      <span>${replyBoardDTO.replyBoardViews}</span>
      </td>
      
      <sec:authorize access="isAuthenticated()">
        <td>
        <span><input type="button" value="신고"/></span>
        <script>
        		jq("input[value=신고]").click(function() {
                window.open("${pageContext.request.contextPath}/reply/reportPopForm?replyBoardPkReport=${replyBoardDTO.replyBoardPk}&memberId=${requestScope.memberId}", "pop", "left=500,top=200,width=600,height=400,history=no,location=no,resizable=no,status=no,scrollbars=no,menubar=no")
            });
        </script>
        </td>
      </sec:authorize>      
    </tr>
    
    <tr>
      <td class="tech-content" colspan="10" style=" text-align: left;" valign= top>
        <div id = "viewer-section"></div>
        <input id ="detail-description" type="hidden" value="${replyBoardDTO.replyBoardContents}">
      </td>
    </tr>
    <tr>
      <td colspan="10">
      <span>${replyBoardDTO.hashTag.hashTagName}</span>
      </td>
      
    </tr>
    
    <c:if test="${replyBoardDTO.replyBoardWriterId eq requestScope.memberId}">
      <tr>
        <td colspan="10" align="center" valign="middle">
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
    </c:if>
</c:when>
</c:choose>
</c:forEach>

    <tr class="titel-color">
    <td>멘션</td>
    <td colspan="4">댓글내용</td>
    <td>댓글작성일</td>
    <sec:authorize access="isAuthenticated()">
      <td colspan="2">좋아요</td>
    </sec:authorize>  
    <td>댓글작성자</td>
    <sec:authorize access="isAuthenticated()">
      <td>신고</td>
    </sec:authorize>
    </tr>
        
<c:set var="replyBoardSize" value="${requestScope.replyBoardDTO.size()}"/>

  <c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">
  <c:if test="${replyBoardSize <=1 }">
        <tr>
          <td colspan="10">댓글이 없습니다.</td>
        </tr>
      </c:if>
  <c:choose>
  <c:when test="${replyBoardDTO.replyBoardReplyNo>0}">
      <tr>
        <td style="width: 80px">
          <a id="mentionNickName${state.count}" style="cursor: pointer;">${replyBoardDTO.mentionNickName}</a>
        <script>
              jq("#mentionNickName${state.count}").click(function() {
                window.open("${pageContext.request.contextPath }/message/messageReplyPage?senderId=${replyBoardDTO.replyBoardMention}", "pop", "left=500,top=200,width=900,height=700,history=no,location=no,resizable=no,status=no,scrollbars=no,menubar=no")
              });
        </script>

        <%-- <input type="hidden" value="${replyBoardDTO.replyBoardMention }"/> --%>
        </td>
        <td colspan="4">
         <span id="replyBoardContents" style="float: left; padding-right:10px">${replyBoardDTO.replyBoardContents }</span>
        <c:if test="${replyBoardDTO.replyBoardWriterId eq requestScope.memberId}">
          <form id="replyUpdateForm" method="post" action="${pageContext.request.contextPath}/reply/replyUpdateForm" style="float: left; padding-right: 10px">
              <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
              <input type="hidden" name="state" value="true"/>
              <input type="hidden" name="replyBoardPk" value="${replyBoardDTO.replyBoardReplyNo}"/>
              <input type="hidden" name="classification" value="${requestScope.classification}"/>
              <input type="hidden" name="replyBoardReplyPk" value="${replyBoardDTO.replyBoardPk}"/>      
              <input type="image" src="${pageContext.request.contextPath}/resources/testimg/replyBoard/reply_Update.png" style="height:20px;">
          </form>
          
          <form id="replyDeleteForm" method="post" action="${pageContext.request.contextPath}/reply/replyDelete" style="float: left; padding-right: 10px">
              <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
              <sec:authorize access="isAuthenticated()">
                <sec:authentication var="member" property="principal" />
                <input type="hidden" name="memberId" value="${member.memberId}">
              </sec:authorize>
              <input type="hidden" name="state" value="true"/>
              <input type="hidden" name="replyBoardPk" value="${requestScope.replyBoardPk}"/>
              <input type="hidden" name="classification" value="${requestScope.classification}">
              <input type="hidden" name="replyBoardReplyPk" value="${replyBoardDTO.replyBoardPk}">            
              <input type="image" src="${pageContext.request.contextPath}/resources/testimg/replyBoard/reply_Delete.png" style="height:20px;">
          </form>
        </c:if>
        </td>   
        <td>
        <span>${replyBoardDTO.replyBoardDate}</span>
        </td>
  
        <!-- 여기부터 -->
        <sec:authorize access="isAuthenticated()">
          <td style="width: 100px">
          <c:choose>
          <c:when test="${replyBoardDTO.updown.isUp==true}">
            <input type="image" src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/reply_thumbs_up_black.png" name="replyBlack${state.count}" id="reply_thumbs_up_black" onclick="window.location.reload()"><br/>
            <input type="image" src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/reply_thumbs_down.png">
          </c:when>
          <c:when test="${replyBoardDTO.updown.isUp==false}">
            <input type="image" src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/reply_thumbs_up.png"><br/>
            <input type="image" src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/reply_thumbs_down_black.png" name="replyBlack${state.count}"id="reply_thumbs_down_black" onclick="window.location.reload()">
          </c:when>      
          <c:otherwise>
            <input type="image" src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/reply_thumbs_up.png" name="replyLike${state.count}" id="reply_thumbs_up" onclick="window.location.reload()"><br/>
            <input type="image" src="${pageContext.request.contextPath}/resources/assets/img/replyBoardLike/reply_thumbs_down.png" name="replyDisLike${state.count}"id="reply_thumbs_down" onclick="window.location.reload()">
          </c:otherwise>
          </c:choose>
          </td>
          <td style="width: 1px">
          <span>${replyBoardDTO.likeNum}</span>
          </td>
        </sec:authorize>
        
  <script>  /* 댓글 좋아요, 싫어요 */
    jq(function() {
      jq("input[name=replyLike${state.count}]").click(function() {
        jq.ajax({
            url : "${pageContext.request.contextPath}/reply/replyBoardLike",  
            type : "post",   
            dataType : "text",  
            beforeSend: function(xhr) {
               xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
            },
            data : "replyBoardPk=${replyBoardDTO.replyBoardPk}&memberId=${requestScope.memberId}",  
            success : function(result){   
               //alert(result) 
            },
            error : function(err){   
               alert(err+" => 오류 발생");
            }
         });
        });
      
      jq("input[name=replyDisLike${state.count}]").click(function() {
        jq.ajax({
          url : "${pageContext.request.contextPath}/reply/replyBoardDisLike",   
          type : "post",   
          dataType : "text",  
          beforeSend: function(xhr) {
             xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          data : "replyBoardPk=${replyBoardDTO.replyBoardPk}&memberId=${requestScope.memberId}",   
          success : function(result){  
             //alert(result)   
          },
          error : function(err){   
             alert(err+" => 오류 발생");
          }
       });
      });
        
      jq("input[name=replyBlack${state.count}]").click(function() {  
        jq.ajax({
            url : "${pageContext.request.contextPath}/reply/replyBoardLikeCancle",  
            type : "post",   
            dataType : "text",   
            beforeSend: function(xhr) {
               xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
            },
            data : "replyBoardPk=${replyBoardDTO.replyBoardPk}&memberId=${requestScope.memberId}",  
            success : function(result){  
               //alert(result)   
            },
            error : function(err){  
               alert(err+" => 오류 발생");
            }
           
         });
          
        });
    });
    
  </script>
        
        <!-- 여기까지 -->
        <td>
        <span>${replyBoardDTO.member.memberNickName}</span>
        
        </td>
        <sec:authorize access="isAuthenticated()">
          <td style="width: 90px">
            <span>
              <input type="button" name="댓글신고${state.count}" value="댓글신고">
            </span>
            <script>
              jq("input[name=댓글신고${state.count}]").click(function() {
                window.open("${pageContext.request.contextPath}/reply/reportPopForm?replyBoardPkReport=${replyBoardDTO.replyBoardPk}&memberId=${requestScope.memberId}", "pop", "left=500,top=200,width=600,height=400,history=no,location=no,resizable=no,status=no,scrollbars=no,menubar=no")
              });
            </script>
          </td>
        </sec:authorize>
      </tr>
  </c:when>
  </c:choose>
  </c:forEach>
<!-- 댓글멘션부분 수정 시작 -->
<sec:authorize access="isAuthenticated()">
  <c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO">
  <c:choose>
  <c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
     <form name="replyWriteForm" method="post" id="replyWriteForm" action="${pageContext.request.contextPath}/reply/replyInsert?classification=${requestScope.classification}&replyBoardPk=${requestScope.replyBoardPk}">
     <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
     <sec:authorize access="isAuthenticated()">
        <sec:authentication var="member" property="principal" />
        <input type="hidden" name="memberId" value="${member.memberId}">
      </sec:authorize>
        <tr>
          <td style="width: 130px"><div id="mentionButton"></div>
              <input type="hidden" id="mentionNickName" name="mentionNickName"/>
              <input type="text" placeholder="@닉네임, 형태로입력" name="mentionInput" autocomplete="off">
              <div id="suggest"></div>
          </td>
          <td colspan="7"><input type="text" placeholder="댓글내용입력(50자 이내 입력)" name="replyContents" maxlength="100"></td>
          <td>
            <sec:authorize access="isAuthenticated()">
              <sec:authentication var="member" property="principal" />
              ${member.memberNickName}
            </sec:authorize>
          </td>
          <td><input type=button value="등록" ></td>
        </tr>
        
      </form>
  </c:when>
  </c:choose>
  </c:forEach>
</sec:authorize>
    
</table>

<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}&pageNo=1">리스트로 돌아가기</a>&gt;</span></div>
<input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>
<script>
            jq(document).on('click', "#mentionNickName", function() {
              var senderId = jq(this).parent().children().eq(0).val();
              window.open("${pageContext.request.contextPath}/message/messageReplyPage?senderId="+senderId, "pop", "left=500,top=200,width=700,height=600,history=no,location=no,resizable=no,status=no,scrollbars=no,menubar=no");
              
            });
</script>
</body>