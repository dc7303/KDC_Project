<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script>

const jq = jQuery.noConflict();


function boardSelect(boardNum){
  bNum = boardNum;
  jq.ajax({
    url:'${pageContext.request.contextPath}/admin/reportSelectByBoardNum' ,			// 서버 요청 주소
    type:"post" ,			// 전송 방식. get or post
    dataType:"json" ,		// 서버가 보내오는 데이터타입(text,html,xml,json)
    data:"boardNum="+boardNum ,	// parameter
    beforeSend: function(xhr) {
      xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
    },
    success:function(result){		// 성공 했을 시 함수
      	var str = "";
    	jq.each(result,function(index,item){
    	  str+="<tr>";
    	  str+="<th>"+(index+1)+"</th>";
    	  str+="<th>"+item.reportReporterId+"</th>";
    	  str+="<th>"+item.replyBoardDTO.replyBoardWriterId+"</th>";
    	  str+="<th>"+item.reportPurpose+"</th>";
    	  str+="<th>"+item.reportDate+"</th>";
    	  str+="<th>"+"<input type='button' value='삭제' onclick='deleteReport("+item.reportPk+")'>"+"</th>";
    	  str+="</tr>";
    	})
    	jq("#table tr:gt(0)").remove();
    	jq("#table").append(str);
    } ,
    error: function(err){		// 실패 했을 시 함수
    	alert(err+" => 오류 발생")
    }
  })
}

function deleteReport(reportNum){
  jq.ajax({
    url:'${pageContext.request.contextPath}/admin/deleteReport' ,			// 서버 요청 주소
    type:"post" ,			// 전송 방식. get or post
    dataType:"json" ,		// 서버가 보내오는 데이터타입(text,html,xml,json)
    data: "reportNum="+reportNum+"&&boardNum="+bNum ,	// parameter
    beforeSend: function(xhr) {
      xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
    },
    success:function(result){		// 성공 했을 시 함수
      	alert("삭제되었습니다");
      	var str="";
    	jq.each(result,function(index,item){
    	  str+="<tr>";
    	  str+="<th>"+(index+1)+"</th>";
    	  str+="<th>"+item.reportReporterId+"</th>";
    	  str+="<th>"+item.replyBoardDTO.replyBoardWriterId+"</th>";
    	  str+="<th>"+item.reportPurpose+"</th>";
    	  str+="<th>"+item.reportDate+"</th>";
    	  str+="<th>"+"<input type='button' value='삭제' onclick='deleteReport("+item.reportPk+")'>"+"</th>";
    	  str+="</tr>";
    	})
    	jq("#table tr:gt(0)").remove();
    	jq("#table").append(str);
    } ,
    error: function(err){		// 실패 했을 시 함수
    	alert(err+" => 오류 발생")
    }
  })
}

</script>
</head>
<body>
<form>
<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black" id="table">
<caption>신고함 LIST</caption>
<caption>
<div class="optionSelect" align="center">
  <select onchange="boardSelect(this.value)">
    <option value="0">게시판 선택</option>
    <option value="1">TECH 게시판</option>
    <option value="2">스터디게시판</option>
    <option value="3">QA 게시판</option>
  </select>
  <select>
    <option value="4">신고 유형</option>
    <option value="5">욕설</option>
    <option value="6">도배</option>
    <option value="7">상업적 글</option>
    <option value="8">기타</option>
  </select>
</div>
</caption>
   <tr>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">번호</span></b></font></p> <!-- modelNum, modelName, price -->
        </td>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">신고인 아이디</span></b></font></p> <!-- modelNum, modelName, price -->
        </td>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">피신고인 아이디</span></b></font></p> <!-- modelNum, modelName, price -->
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">신고 내용</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">신고한 날짜</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">삭제</span></b></font></p>
        </td>
    </tr>
    
   <c:forEach items="${requestScope.reportList}" var="reportList">
          <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  </span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${reportList.reportReporterId}</span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
               <a href="${pageContext.request.contextPath}/message/${messageList.messageNum}"> <!-- path variable RESTful -->
                 ${reportList.replyBoardDTO.replyBoardWriterId}
               </a>
               </span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${reportList.reportPurpose}</span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${reportList.reportDate}</span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  <input type="button" value="삭제" id="deleteReport" onclick="deleteReport(${reportList.reportPk})"></span></p>
              </td>
          </tr>
    </c:forEach>
</table>
</form>
</body>
</html>