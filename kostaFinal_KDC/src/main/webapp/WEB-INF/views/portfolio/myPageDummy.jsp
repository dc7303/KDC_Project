<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	const jq = jQuery.noConflict();
	jq(function(){
	  console.log(jq('#original-visibility').val());
	  if(jq('#original-visibility').val()==='true'){
	    jq('input[name=portFolioVisibility]').attr('checked','');
	  }
	  
	  /* ������ư Ŭ���� ������ ����, ���� ���� */
	  jq('#update-portfolio').on('click',function(){
	    if(jq('#portfolio-update-form').css("display")==='none'){
	      jq('#portfolio-update-form').css("display","block");
	      jq('#portfolio-info').css("display","none");
	    }
	  });
	  
	  //��Ʈ������ �� ������ư Ŭ�� �̺�Ʈ(a �±� ��ü�ص� ��������)
	  jq('.update-detail').on('click',function(){
	    var pk = jq(this).attr('id');
	    pk = pk.substr(7);
	    location.href='${pageContext.request.contextPath}/portfolio/selectDetail/'+pk;
	  })
	  
	});
</script>
</head>
<body>
  <h1>myPageDummy</h1>
  <c:choose>
    <c:when test="${empty portfolio}">
        <!-- ���̵�, ��ǥ�̹���, ����, �Խÿ���(üũ�ڽ�) -->
      <form
        action="${pageContext.request.contextPath }/portfolio/insertPortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data">
                  ���̵� : <input type="text" name="portFolioMemberId"/></p></br>
                  ���� : <input type="text" name="portFolioMainTitle"/></p></br> 
                  ��ǥ�̹��� : <input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility"/> - �Խÿ���  </p></br>
        <input type="submit" value="����" />
      </form>
    </c:when>
    <c:otherwise>
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
            ���ÿ���:<input type="hidden" id="original-visibility" value="${portfolio.portFolioVisibility}"/>
         <input type="checkBox" name="portFolioVisibility" disabled/></p></br>
            </br>
            <input type="button" value="�����ϱ�" id="update-portfolio"/>
     </div>
     <div id="portfolio-update-form" style="display:none;">
      <form
        action="${pageContext.request.contextPath }/portfolio/updatePortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data">
                  ���̵� : <input type="text" name="portFolioMemberId" value="${portfolio.portFolioMemberId}" readonly="true"/></p></br>
                  ���� : <input type="text" name="portFolioMainTitle" value="${portfolio.portFolioMainTitle}"/></p></br> 
         <c:if test="${not empty portfolio.portFolioMainImage}">
                  �����̹��� : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
         </c:if>         
         </br>         
                  ��ǥ�̹��� : <input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility" value="${portfolio.portFolioVisibility}"/> - ���ÿ���  </p></br>
        <input type="submit" value="�����Ϸ�" />
      </form>
     </div>
     <c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
                ��Ʈ������ �󼼰� 1�� �����ϴ� �߰����ּ���.
       </c:when>
       <c:otherwise>
         </br>
         </br>
         <hr>
         </br>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail">
         <div>
                    ������Ʈ�� : ${detail.portfolioDetailProjectName}</p>
                    �ؽ��±� :
            <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
              ${hashTag.hashTagName}
            </c:forEach>
            </p>
            </br>
                    ��Ʈ������ �̹��� : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
          </br>     
          <input type="button" value="�����ϱ�" class="update-detail" id="detail-${detail.portFolioDetailPk}"/>
          <hr>
         </div>
         </c:forEach>
       </c:otherwise>
     </c:choose>
     <br>
     <a href="${pageContext.request.contextPath }/portfolio/detailForm">��Ʈ������ �� �߰�</a>
    </c:otherwise>
  </c:choose>
  
  
</body>
</html>
