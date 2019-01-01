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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/portfolio.css" />
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
<center>
<sec:authentication var="member" property="principal" />
  <h2 class="notice-title">�� ��Ʈ������ �ۼ�</h2>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data" id="editor-form">
   
   <hr class="hr-border">
   
   <label class="label-portfolio">������Ʈ��</label></p></br>
  <input type="text" name="portfolioDetailProjectName" value="${detail.portfolioDetailProjectName}"/></p></br>
  <div id="original-img" style="display:none;">���� �̹��� : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}"></div>
  <p>*������Ʈ���� �Է��ϼ���<br>
     ex) Ŀ�´�Ƽ ����Ʈ ������Ʈ</p>
  
  <hr class="hr-border">
  
  <label class="label-portfolio">�ؽ��±�</label></p></br>
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
  <span id="suggest">
  <!-- ���þ� �ܾ� ��ºκ� --> 
  </span></p></br>
  <p>#OOO �����Է��� �����̽� </p>
  
  <!-- �ؽ��±� ��-->
  <hr class="hr-border">
  
  <label class="label-portfolio">������Ʈ �̹���</label>
  <input type="file" name="DeltailProjectImage" /></p></br>
  <p>*������Ʈ �̹��� ������ �������ּ���. </p>
  <hr class="hr-border">
  
  
  <label class="label-portfolio">������Ʈ ����</label>
  <div id="editSection"></div>
  <input type="hidden" id="original-pk"name="" value="${detail.portFolioDetailPk}">
  <input type="hidden" id="original-description" value="${detail.portfolioDetailDescription}"/>
  <br/><br/><br/>
  <input type="button" value="�ۼ��Ϸ�" id="editor-submit" />
  <br/><br/><br/>
  
  
</form>
<input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>

</center>
</body>
</html>
