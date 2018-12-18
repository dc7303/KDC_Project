<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="${pageContext.request.contextPath }/resources/lib/bower_components/jquery/dist/jquery.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/tui-code-snippet/dist/tui-code-snippet.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/markdown-it/dist/markdown-it.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/to-mark/dist/to-mark.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/codemirror/lib/codemirror.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/highlightjs/highlight.pack.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/squire-rte/build/squire-raw.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/tui-editor/dist/tui-editor-Editor.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/codemirror/lib/codemirror.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/highlightjs/styles/github.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/tui-editor/dist/tui-editor.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/tui-editor/dist/tui-editor-contents.css">
<title>Insert title here</title>
<script>
const jq = jQuery.noConflict();

jq(function(){
  /* update�� ��� form�� action�� ���� */
  if(location.href.match(/updateDetailForm/)){
    //�������
    jq('h1').text('�� ������Ʈ ��');
    
    var url = '${pageContext.request.contextPath }/portfolio/updateDetail/'+
    ${detail.portFolioDetailPk}+'?${_csrf.parameterName}=${_csrf.token}';
    jq('#editor-form').attr('action',url); 
    
    //submit��ư�� ����
    jq('#editor-submit').attr('value','�����Ϸ�');
    
    //�ش� �Խù� ������ư ����
    var deleteBtn = jq('<input>').attr('type','button')
  	.attr('value','�����ϱ�').on('click',function(){
  	  location.href = '${pageContext.request.contextPath}/portfolio/deleteDetail/'+${detail.portFolioDetailPk};
  	});
  	jq('#editor-form').append(jq(deleteBtn));
  }
  
  /* ������ ��ü ���� */
  var editor = new tui.Editor({
    el: document.querySelector('#editSection'),
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    height: '300px',
  });
  
  /* ������ �� submit control */
  jq('#editor-submit').on('click',function(){
    var content = editor.getValue();
    
    var input = jq('<input>').attr('type','hidden')
    	.attr('name','portfolioDetailDescription').val(content);
    jq('#editor-form').append(jq(input));
    
    jq('#editor-form').submit();
    
  }); 
  
});

</script>
</head>
<body>
<h1>�� ��Ʈ������ �ۼ� �� �Դϴ�</h1>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data" id="editor-form">
  �ۼ��� ���̵� : <input type="text" name="portFolioDetailMemberId"/></p></br>
  ������Ʈ�� : <input type="text" name="portfolioDetailProjectName"/></p></br>
  ������Ʈ �̹��� <input type="file" name="DeltailProjectImage" /></p></br> 
  �ؽ��±� : <input type="text" name="hashTagName"/></p></br>
  <div id="editSection"></div>
  <input type="button" value="�ۼ��Ϸ�" id="editor-submit" />
</form>

</body>
</html>