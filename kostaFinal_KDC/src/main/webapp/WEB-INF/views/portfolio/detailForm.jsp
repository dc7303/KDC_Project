<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
     //�ش� �Խù� ������ư ����
    var deleteBtn = jq('<input>').attr('type','button').attr('value','�����ϱ�').on('click',function(){
  	  location.href = '${pageContext.request.contextPath}/portfolio/deleteDetail/'+'${detail.portFolioDetailPk}';
  	});
  	jq('#editor-form').append(jq(deleteBtn));
  	
  	//�ۼ��� �̸� �����Ұ��� ����(��ť��Ƽ���� �����ð�� ����)
  	jq('input[name=portFolioDetailMemberId]').attr('readonly',true); 
  	
  	//�����Ϳ� ���� ������ �� ����
  	var original = jq('#original-description').val();
  	editor.setValue(original);
  	
  	//�����̹��� ����
  	jq('#original-img').css('display','block');
  	
  	//PK�� hidden�� ���ܼ� ������ name����
  	jq('#original-pk').attr('name','portFolioDetailPk')
  	
  	//HASH�±װ� ��������
  	var hashTags = '${detail.portfolioDetailHashTagList}'
  	console.log('hashTags = '+hashTags);
  	 for(let hashTag of hashTags){
  	  console.log(hashTag.hashTagName);
  	} 
  	
  	jq('input[name=hashTagName]').val();
  }
  
  jq('h1').css('visibility','visible');
  
  
  
  
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
<h1 style="visibility:hidden;">�� ��Ʈ������ �ۼ� �� �Դϴ�</h1>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data" id="editor-form">
  �ۼ��� ���̵� : <input type="text" name="portFolioDetailMemberId" value="${detail.portFolioDetailMemberId}"></p></br>
  
  ������Ʈ�� : <input type="text" name="portfolioDetailProjectName" value="${detail.portfolioDetailProjectName}"/></p></br>
 <div id="original-img" style="display:none;">���� �̹��� : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}"></div>
 </br> 
 
 <c:choose>
    <c:when test="${empty detail.portfolioDetailHashTagList}">
      �ؽ��±� : <input type="text" name="hashTagName"/></p></br>
    </c:when>
    <c:otherwise>
    �ؽ��±� : <input type="text" name="hashTagName" value="<c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag" >${hashTag.hashTagName}, </c:forEach>"/>
       </p></br>
    </c:otherwise>
  </c:choose>
  
  ������Ʈ �̹��� <input type="file" name="DeltailProjectImage" /></p></br>
  
  
  <div id="editSection"></div>
  <input type="hidden" id="original-pk"name="" value="${detail.portFolioDetailPk}">
  <input type="hidden" id="original-description" value="${detail.portfolioDetailDescription}"/>
  <input type="button" value="�ۼ��Ϸ�" id="editor-submit" />
</form>

</body>
</html>