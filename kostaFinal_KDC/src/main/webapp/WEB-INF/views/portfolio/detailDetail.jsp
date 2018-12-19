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
    //수정하기 버튼 이벤트
    jq('input[value=수정하기]').on('click', function() {
      location.href='${pageContext.request.contextPath}/portfolio/updateDetailForm/${detail.portFolioDetailPk}';
      
    });
    //삭제하기 버튼 이벤트
    jq('input[value=삭제하기]').on('click', function() {
      var flag = confirm('정말 삭제하시겠습니까');
      if(flag===true){
	    location.href='${pageContext.request.contextPath}/portfolio/deleteDetailForm/${detail.portFolioDetailPk}';
      }
    });
  });
</script>
</head>

<body>
  <h1>포트폴리오 상세의 상세보기 페이지입니다.</h1>

  프로젝트명: ${detail.portfolioDetailProjectName}
  </br> 작성일 : ${detail.portfolioDetailDate}
  </br> 해쉬태그 :
  <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
          ${hashTag.hashTagName}
        </c:forEach>
  </br> 포트폴리오 이미지:
  <img
    src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}" />
  </br> 포트폴리오 설명 :
  <p>${detail.portfolioDetailDescription}</p>
  </br>
  <input type="button" value="삭제하기">
  <input type="button" value="수정하기">
</body>
</html>