<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
  const jq = jQuery.noConflict();
  jq(function() {
    //�����ϱ� ��ư �̺�Ʈ
    jq('input[value=�����ϱ�]').on('click', function() {
      location.href='${pageContext.request.contextPath}/portfolio/updateDetailForm/${detail.portFolioDetailPk}';
      
    });
    //�����ϱ� ��ư �̺�Ʈ
    jq('input[value=�����ϱ�]').on('click', function() {
      var flag = confirm('���� �����Ͻðڽ��ϱ�');
      if(flag===true){
	    location.href='${pageContext.request.contextPath}/portfolio/deleteDetailForm/${detail.portFolioDetailPk}';
      }
    });
  });
</script>
</head>

<body>
  <h1>��Ʈ������ ���� �󼼺��� �������Դϴ�.</h1>

  ������Ʈ��: ${detail.portfolioDetailProjectName}
  </br> �ۼ��� : ${detail.portfolioDetailDate}
  </br> �ؽ��±� :
  <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
          ${hashTag.hashTagName}
        </c:forEach>
  </br> ��Ʈ������ �̹���:
  <img
    src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}" />
  </br> ��Ʈ������ ���� :
  <p>${detail.portfolioDetailDescription}</p>
  </br>
  <input type="button" value="�����ϱ�">
  <input type="button" value="�����ϱ�">
</body>
</html>