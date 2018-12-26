<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
<!-- �ؽ��±� js -->
<script src="${pageContext.request.contextPath}/resources/js/portfolio-hashtag.js"></script>
<script>
const jq = jQuery.noConflict();

jq(function(){
  /* ������ ��ü ���� */
  var editor = new tui.Editor({
    el: document.querySelector('#editSection'),
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    height: '300px',
  });
  
  /* update�� ��� form�� action�� ���� */
  if(location.href.match(/updateDetailForm/)){
    //�������
    jq('h1').text('�� ������Ʈ ��');
    
    var url = '${pageContext.request.contextPath }/portfolio/updateDetail?${_csrf.parameterName}=${_csrf.token}';
    jq('#editor-form').attr('action',url); 
    
    //submit��ư�� ����
    jq('#editor-submit').attr('value','�����Ϸ�');
    
  	
  	//�ۼ��� �̸� �����Ұ��� ����(��ť��Ƽ���� �����ð�� ����)
  	jq('input[name=portFolioDetailMemberId]').attr('readonly',true); 
  	
  	//�����Ϳ� ���� ������ �� ����
  	var original = jq('#original-description').val();
  	editor.setValue(original);
  	
  	//�����̹��� ����
  	jq('#original-img').css('display','block');
  	
  	//PK�� hidden�� ���ܼ� ������ name����
  	jq('#original-pk').attr('name','portFolioDetailPk')
  	
  	
  	jq('input[name=hashTagName]').val();
  }
  
  jq('h1').css('visibility','visible');
  
  
  
  
  /* ������ �� submit control */
  jq('#editor-submit').on('click',function(){
    var content = editor.getValue();
  	//form ��ȿ���˻�(����, ��)
  	if(jq('input[name=portfolioDetailProjectName]').val().trim()===''){
  	  alert('������ �ʼ��Է»��� �Դϴ�.');
  	  jq('input[name=portfolioDetailProjectName]').focus();
  	  return;
  	}else if(content.trim()===''){
  	  alert('������Ʈ �󼼼����� �ʼ��Է»��� �Դϴ�.')
  	  return;
  	}
    
    var input = jq('<input>').attr('type','hidden')
    	.attr('name','portfolioDetailDescription').val(content);
    jq('#editor-form').append(jq(input));
    
    jq('#editor-form').submit();
    
  });
  
  
  
  
});

</script>
</head>
<body>
<sec:authentication var="member" property="principal" />
  <h3>${member.memberId}���� �� ��Ʈ������ �ۼ� �� �Դϴ�</h3>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data" id="editor-form">
   
  <h5>������Ʈ�� : </h5>
  <input type="text" name="portfolioDetailProjectName" value="${detail.portfolioDetailProjectName}"/></p></br>
  <div id="original-img" style="display:none;">���� �̹��� : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}"></div>
  </br> 
  <h5>�ؽ��±� : </h5>
  <span id="span">
  <!-- �̺�Ʈ �߻��� �±װ� ���⿡ �߰� -->
  </span>
  <input type="hidden" id="hashTagName" name="hashTagName"/>
  <c:choose>
    <c:when test="${empty detail.portfolioDetailHashTagList}">
      <input type="text" name="hashTagInput"/>
    </c:when>
    <c:otherwise>
      <input type="text" id = "hashTagInput" name="hashTagInput" value="<c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag" >${hashTag.hashTagName}, </c:forEach>"/>
    </c:otherwise>
  </c:choose>
  <span id="suggest" style="float:left;">
  <!-- ���þ� �ܾ� ��ºκ� --> 
  </span>
  
  <!-- �ؽ��±� ��-->
  
  <h5>������Ʈ �̹��� </h5>
  <input type="file" name="DeltailProjectImage" /></p></br>
  
  <div id="editSection"></div>
  <input type="hidden" id="original-pk"name="" value="${detail.portFolioDetailPk}">
  <input type="hidden" id="original-description" value="${detail.portfolioDetailDescription}"/>
  <input type="button" value="�ۼ��Ϸ�" id="editor-submit" />
</form>
<input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>


</body>
</html>
