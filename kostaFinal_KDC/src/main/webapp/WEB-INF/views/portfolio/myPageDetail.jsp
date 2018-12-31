<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/portfolio.css" />

<script>
  const jq = jQuery.noConflict();
  jq(function() {
    //viewer ����
    var contents = jq('#detail-description').val();
	var editor = tui.Editor.factory({
      el: document.querySelector('#viewer-section'),
      viewer: true,
      height: '500px',
      initialValue: contents
	});
    
    //�����ϱ� ��ư �̺�Ʈ
    jq('input[value=�����ϱ�]').on('click', function() {
      location.href='${pageContext.request.contextPath}/portfolio/updateDetailForm/${detail.portFolioDetailPk}';
      
    });
    //�����ϱ� ��ư �̺�Ʈ
    jq('input[value=�����ϱ�]').on('click', function() {
      var flag = confirm('���� �����Ͻðڽ��ϱ�');
      if(flag===true){
	    location.href='${pageContext.request.contextPath}/portfolio/deleteDetail/${detail.portFolioDetailPk}';
      }
    });
  });
</script>
</head>

<body>
<center>




  <h3 class="head-portfolio">${detail.portFolioDetailMemberId}��Ʈ������ ��������</h3>
  
  <a href='${pageContext.request.contextPath}/portfolio/myPage'>�������</a> <br> 
  
  
  
  <label class="label-portfolio">������Ʈ��</label>
   <h2>${detail.portfolioDetailProjectName}</h2><p/><br/>  
  </br> 
   <hr class="hr-border">
  
  <label class="label-portfolio">�ۼ���</label>
   <h2>${detail.portfolioDetailDate}</h2><p/><br/>  
  </br> 
   <hr class="hr-border">
   
   <label class="label-portfolio">�ؽ���ũ</label>
  <h2>
    <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
      ${hashTag.hashTagName}
    </c:forEach>
  </h2>
  </br>
  <hr class="hr-border">
  
  
  <label class="label-portfolio">��Ʈ������ �̹���</label><br/>
  <img
    src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}" />
  </br> 
    <hr class="hr-border">
  
  <label class="label-portfolio">��Ʈ������ ����</label>
  <h2>
  <div id = "viewer-section"></div>
  <input id ="detail-description" type="hidden" value="${detail.portfolioDetailDescription}">
  </h2>
  </br>
  
  
  <input class="update-portfolio" type="button" value="�����ϱ�">
  <input class="update-portfolio" type="button" value="�����ϱ�">
  <br/><br/><br/>
  
</center> 
</body>
</html>
