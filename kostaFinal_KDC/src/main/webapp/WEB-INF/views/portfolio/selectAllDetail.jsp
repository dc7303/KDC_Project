<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
 
  //viewer ����
    var contents = jq('#detail-Description').val();
    var editor = tui.Editor.factory({
      el : document.querySelector('#viewerSection'),
      viewer : true,
      height : '500px',
      initialValue : contents
    });
  });
</script>
</head>
<body>
selectAllDetail
<div id="portfolio-info">
            ���̵�: ${portfolio.portFolioMemberId}</p>
            ����: ${portfolio.portFolioMainTitle} </p>
            ��ǥ�̹��� :
      <c:choose>      
         <c:when test="${not empty portfolio.portFolioMainImage}">
           <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
         </c:when>
         <c:otherwise>
                    �̹����� �����ϴ�.
         </c:otherwise>
      </c:choose>
      </br>
      
     </div>
<hr color="green">
</br>
<c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
                ��Ʈ������ �������� �����ϴ�.
       </c:when>
       <c:otherwise>
         </br>
         </br>
         <hr>
         </br>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail">
        <div>
          ������Ʈ�� : ${detail.portfolioDetailProjectName}
          </p>
          �ؽ��±� :
          <c:forEach items="${detail.portfolioDetailHashTagList}"
            var="hashTag">
              ${hashTag.hashTagName}
            </c:forEach>
          </p>
          </br>
          <c:choose>
            <c:when test="${empty detail.portfolioDeltailProjectImage}">
              <h5>������ �����ϴ�.</h5>
            </c:when>
            <c:otherwise>
                ��Ʈ������ �̹��� : <img
                src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
            </c:otherwise>
          </c:choose>
          </br>
          <h5>�� ���� :</h5>
          <div id="viewerSection"></div>
          <input id="detail-Description" type="hidden"
            value="${detail.portfolioDetailDescription}">
        </div>
        <hr color="red">
         </c:forEach>
       </c:otherwise>
     </c:choose>

</body>
</html>