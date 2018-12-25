<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<HEAD>

</HEAD>
<h1>${requestScope.noticeBoardPk}상세보기</h1>

<form name="update" method="post" action="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${requestScope.noticeBoardPk}" >
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

 
  <table align="center" border="1" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
  
         <thead>
            <tr>
              <th colspan="2">글제목</th>
             
              <th>등록날짜</th>
              <th>내용</th>
              <th>조회수</th>
              <th>다운로드</th>
              
            </tr>
          </thead>
  
   
        <tr>
          <td colspan="2">
          <span>${NoticeBoardDTO.noticeBoardTitle}</span>
          </td>
          <td>
          <span>${NoticeBoardDTO.noticeBoardDate}</span>
          </td>
          <td>
          <span>${NoticeBoardDTO.noticeBoardContents}</span>
          </td>
          <td>
          <span>${NoticeBoardDTO.noticeBoardViews}</span>
          </td> 
          
          <c:if test="${NoticeBoardDTO.noticeBoardAttachment!=null}">
          <td>
       
                <a href='${pageContext.request.contextPath}/notice/downLoad?attachment=${NoticeBoardDTO.noticeBoardAttachment}'>
             ${NoticeBoardDTO.noticeBoardAttachment}
         </a>
   
          </td> 
       </c:if>   
       
        </tr>
     
    
    
  
      
      <div class="update-button">
      <a href="updateForm?noticeBoardPk=${NoticeBoardDTO.noticeBoardPk}" class="button">수정하기</a>
     </div> 
     
      <div class="delete-button">
      <a href="delete?noticeBoardPk=${NoticeBoardDTO.noticeBoardPk}" class="button">삭제하기</a>
     </div> 
     
   
  </table>
</form>
<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/notice/list?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>
