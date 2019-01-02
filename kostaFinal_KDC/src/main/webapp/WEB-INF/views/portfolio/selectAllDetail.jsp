<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/portfolio.css" />
<title>Insert title here</title>
<script type="text/javascript">
  const jq = jQuery.noConflict();
  jq(function(){
  //viewer 세팅
  var detailLength = jq('#detailList-length').val();
  for(var i=0;i<detailLength;i++){
    var descSelector = '#detail-description-'+i;
    var viewSelector = '#viewer-section-'+i;
    var contents = jq(descSelector).val();
    var editor = tui.Editor.factory({
      el : document.querySelector(viewSelector),
      viewer : true,
      height : '500px',
      initialValue : contents
    });
  }
  
//답장시, 쪽지 보낸사람(senderId) 유효성 체크 및 메세지 팝업창
  jq(document).on('click','.add-portfolio button',function(){
    
    var senderId = jq('input[name=portFolioMemberId]').val();
    
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
  
});
</script>
<style>
  .nickname-card{
    min-width:300px;
    max-width: 600px;
    width: 30%;
    background-color: #03293c;
    position: relative;
    height: 140px;
    
  }
  .card-logo{
    color: #ffb03a;
    top: 5px;
    font-size: 25px;
    position: absolute;
    padding: 0px 10px;
    text-align: center;
  }
  .comment{
    color: #eeeeee;
  }
  .card-nickname{
    position: absolute;
    bottom: 5px;
    right: 10px;
    color: white;
    font-size: 50px;
  }
  .portfolio-detail{
    display: flex;
  }
  .image-box{
    flex: 1;
  }
  .image-box img{
    width:100%;
  }
  .text-box{
    flex: 1;
  }
  .detail-box{
    border: gray solid 2px;
    margin: 10px;
    max-height: fit-content;
  }
  .text-box > div{
    margin: 10px;
  }
  .no-img{
    width: 100%;
    height: 100%;
    text-align: center;
  }
  .no-img h3{
    margin:0px;
    padding-top: 15%;
  }
  .send-btn{
    text-align:right;
  }
  .tag-box{
    border-bottom: #ffb03a solid 5px;
  }
  .detail-box h3{
    font-weight: bold;
    margin: 20px;
  }
  .tag-box span{
    margin-left: 10px;
  }
</style>
</head>
<body>
<h2 class="notice-title">PORTFOLIO</h2>
<p class="underline"></p>

<div class="nickname-card">
  <div class="card-logo"><span class="comment">//</span>KDC</div>
  <div class="card-nickname">
    <span>${portfolio.portFolioMemberNickName}</span>
  </div>
</div>
<div class="send-btn">
  <a class="add-portfolio button" href="${pageContext.request.contextPath }/message/messageReplyPage?senderId=${portfolio.portFolioMemberId}">쪽지보내기</a>
  <input type="hidden" name="portFolioMemberId" value="${portfolio.portFolioMemberId}">
</div>

<c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
                포트폴리오 상세정보가 없습니다.
       </c:when>
       <c:otherwise>
         </br>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail" varStatus="status">
         <div class="portfolio-detail">
           <div class="image-box detail-box">
             <c:choose>
               <c:when test="${empty detail.portfolioDeltailProjectImage}">
               <div class="no-img">
                 <h5>사진이 없습니다.</h5>
               </div>
               </c:when>
               <c:otherwise>
               <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}"></td>
               </c:otherwise>
             </c:choose>
           </div>
           <div class="text-box detail-box">
             <div>
               <span>프로젝트명</span>
               <h3>${detail.portfolioDetailProjectName}</h3>
             </div>
             <div class="tag-box">
               <p>해쉬태그</p>
               <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
                 <span>${hashTag.hashTagName}</span>
               </c:forEach>
             </div>
             <div>
               <div id="viewer-section-${status.index}"></div>
               <input id="detail-description-${status.index}" type="hidden" value="${detail.portfolioDetailDescription}">
             </div>
           </div>
         
         </div> 
         
        <hr color="red">
         </c:forEach>
       </c:otherwise>
 </c:choose>
     <input id = "detailList-length" type="hidden" value = "${fn:length(portfolio.portFolioDetailList)}" />
</body>
</html>