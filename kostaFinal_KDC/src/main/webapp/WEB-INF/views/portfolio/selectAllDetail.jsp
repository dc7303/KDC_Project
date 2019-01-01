<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="${pageContext.request.contextPath }/resources/lib/tui-editor/jquery/dist/jquery.js"></script>
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
    
  });
</script>
<style>
  .nickname-card{
    width: 300px;
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
    right: 5px;
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
  }
  .text-box > div{
    margin: 10px;
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
               <span>${detail.portfolioDetailProjectName}</span>
             </div>
             <div>
               <span>해쉬태그</span>
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