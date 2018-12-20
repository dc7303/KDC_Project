<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
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
         <hr>
         </br>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail">
         <div>
                    프로젝트명 : ${detail.portfolioDetailProjectName}</p>
                    해쉬태그 :
            <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
              ${hashTag.hashTagName}
            </c:forEach>
            </p>
            </br>
            <c:choose>
              <c:when test="${empty detail.portfolioDeltailProjectImage}">
                <h5>사진이 없습니다.</h5>
              </c:when>
              <c:otherwise>
                포트폴리오 이미지 : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
              </c:otherwise>
            </c:choose>
          </br>  
          <h5>상세 설명 : </h5>
          <h5>
            ${detail.portfolioDetailDescription}
          </h5>
         </div>
         <hr color="red">
         </c:forEach>
       </c:otherwise>
     </c:choose>

</body>
</html>