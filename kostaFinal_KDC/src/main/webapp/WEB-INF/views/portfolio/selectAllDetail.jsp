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

<title>Insert title here</title>
<script type="text/javascript">
  const jq = jQuery.noConflict();
  jq(function(){
 
  //viewer 세팅
  var detailLength = jq('#detailList-length').val();
  for(var i=0;i<detailLength;i++){
    var descSelector = '#detail-description-'+i;
    var viewSelector = '#viewer-section-'+i;
    console.log(descSelector+'||'+viewSelector);
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
</head>
<body>
selectAllDetail
<div id="portfolio-info">
            아이디: ${portfolio.portFolioMemberId}</p>
            제목: ${portfolio.portFolioMainTitle} </p>
            대표이미지 :
      <c:choose>      
         <c:when test="${not empty portfolio.portFolioMainImage}">
           <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
         </c:when>
         <c:otherwise>
                    이미지가 없습니다.
         </c:otherwise>
      </c:choose>
      </br>
      
     </div>
<hr color="green">
</br>
<c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
                포트폴리오 상세정보가 없습니다.
       </c:when>
       <c:otherwise>
         </br>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail" varStatus="status">
        <div>
          프로젝트명 : ${detail.portfolioDetailProjectName}
          </p>
          해쉬태그 :
          <c:forEach items="${detail.portfolioDetailHashTagList}"
            var="hashTag">
              ${hashTag.hashTagName}
            </c:forEach>
          </p>
          </br>
          <c:choose>
            <c:when test="${empty detail.portfolioDeltailProjectImage}">
              <h5>사진이 없습니다.</h5>
            </c:when>
            <c:otherwise>
                포트폴리오 이미지 : <img
                src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
            </c:otherwise>
          </c:choose>
          </br>
          <h5>상세 설명 :</h5>
          <div id="viewer-section-${status.index}"></div>
          <input id="detail-description-${status.index}" type="hidden"
            value="${detail.portfolioDetailDescription}">
        </div>
        <hr color="red">
         </c:forEach>
       </c:otherwise>
     </c:choose>
     <input id = "detailList-length" type="hidden" value = "${fn:length(portfolio.portFolioDetailList)}" />
</body>
</html>