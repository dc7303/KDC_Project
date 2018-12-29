<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DtdHTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <title>Kosta Developer Community Admin Page</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Poppins"
  />
  <style>
    body,
    h1,
    h2,
    h3,
    h4,
    h5 {
      font-family: 'Poppins', sans-serif;
    }
    body {
      font-size: 16px;
    }
    .w3-half img {
      margin-bottom: -6px;
      margin-top: 16px;
      opacity: 0.8;
      cursor: pointer;
    }
    .w3-half img:hover {
      opacity: 1;
    }
    .ajax {
      color: skyblue;
    }
    table {
      width: 100%;
    }
    th {
      background-color: #2196F3;
      color: white;
    }
    td {
      text-align: center;
      font-size: 10px;
    }
    .empty-list {
      text-align: center;
    }
    .admin-table {
      height: 550px;
    }
    /*
    <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'"
    */
  </style>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/lib/jquery-3.3.1.min.js"></script>
  <script type="text/javascript">
    (function(jq) {
      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      //전체 게시글 이용 비율 차트 렌더링
      jq.ajax({
        url: '${pageContext.request.contextPath}/boardTotalChart',
        type: 'get',
        dataType: 'json',
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
        },
        success: function(result) {
          
          const boardAll = function boardAllChart() {
            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'contents');
            data.addColumn('number', 'quantity');
            
            //addRows할 배열 변수
            var dataArr =[];
            for(let key in result) {
              dataArr.push([key, result[key]]);
            }
            //물리 데이터 담기
            data.addRows(dataArr);

            // Set chart options
            var options = {'title':'모든 게시글 이용 비율',
                           'width':500,
                           'height':400};

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
            
          };
          //콜백함수로 넘기기.
          google.charts.setOnLoadCallback(boardAll);
          
          
        },
        error: function(err) {
          console.log('err : ' + err);
        }
      });

      jq.ajax({
        url: '${pageContext.request.contextPath}/visitNumChart',
        type: 'post',
        dataType: 'json',
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
        },
        success: function(result) {

          	const visitNum = function visitNumChart(){
            
            // Create the data table.
            var columnData = new google.visualization.DataTable();
            columnData.addColumn('string', 'visitDate');
            columnData.addColumn('number', 'VisitNum');
            columnData.addColumn({type: 'string', role: 'style'});
          	
            //addRows할 배열 변수
            var dataArr =[];
          	
            $.each(result, function(index, item){
              
              if(index % 2 == 0){
                dataArr.push([item.visitDate,item.visitNum,'#ff0000']);
              }else{
                dataArr.push([item.visitDate,item.visitNum,'#2196F3']);
              }
              
            });
          	
          	//물리 데이터 담기
            columnData.addRows(dataArr);
          	
            var columnOptions = {
                title: '최근 5일 방문자 수 추이',
                width:500,
                height:400,
                bar : {
              		groupWidth : '50%'
              	},
                vAxis: {
                  title: '방문자 수'
                }
            };

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.ColumnChart(document.getElementById('columnChart_div'));
            chart.draw(columnData, columnOptions);
            
          };
          
          google.charts.setOnLoadCallback(visitNum);
          
        },
        error: function(err) {
          console.log('err : ' + err);
        }
      });

      
      /**
       *  멤버 리스트 불러오기
       */
      jq.ajax({
        url: '${pageContext.request.contextPath}/adminMemberList',
        type: 'get',
        dataType: 'json',
        data: {
          currentPage: 1,
        },
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
        },
        success: function(result) {
          var resultStr = memberPaging(result);
          jq('.admin-table').eq(0).html(resultStr);
        },
        error: function(err) {
          console.log('err : ' + err);
        }
      });
      
      /**
       * 신고 리스트 가져오기
       */
      jq.ajax({
        url: '${pageContext.request.contextPath}/adminReportList',
        type: 'get',
        dataType: 'json',
        data: {
          currentPage: 1,
        },
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
        },
        success: function(result) {
          console.log(result);
        },
        error: function(err) {
          console.log('err : ' + err);
        }
      });
      //페이지 번호 click 이벤트
      jq(document).on('click', '.page-number', function() {
        var currentPage = parseInt(jq(this).text());
        console.log(currentPage)
        jq.ajax({
          url: '${pageContext.request.contextPath}/adminMemberList',
          type: 'get',
          dataType: 'json',
          data: {
            currentPage: currentPage,
          },
          beforeSend: function(xhr) {
            xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
          },
          success: function(result) {
            var strResult = memberPaging(result);
            jq('.admin-table').eq(0).html(strResult);
          },
          error: function(err) {
            console.log('err : ' + err);
          }
        });
      });
      
      //member Paging 모듈
      function memberPaging(result) {
          var memberList = result.memberList;
          var pageDTO = result.pageDTO;
          //멤버 리스트가 존재할때
		var str =
				'<tr><th>유저id</th><th>유저이름</th><th>닉네임</th><th>생년월일</th>' + 
				'<th>휴대폰번호</th><th>이메일</th><th>가입일</th>' + 
				'<th>유저 추방</th></tr><tr class="table-tr">';
          
          if(memberList !== null) {
            //멤버 리스트 셋팅
            for(var i = 0; i < memberList.length; i++) {
              str += '<td>' + result.memberList[i].memberId + '</td>';
              str += '<td>' + result.memberList[i].memberName + '</td>';
              str += '<td>' + result.memberList[i].memberNickName + '</td>';
              str += '<td>' + result.memberList[i].memberBirth + '</td>';
              str += '<td>' + result.memberList[i].memberPhone + '</td>';
              str += '<td>' + result.memberList[i].memberEmail + '</td>';
              str += '<td>' + result.memberList[i].memberDate + '</td>';
              str += '<td><input type="button" value="삭제" id="deleteMember"></td></tr>'
            }
          }else {
            str += '<td class="empty-list" colspan="8">등록된 유저가 없습니다.</td>'
          }
          
          
          str += '<td class="page-selector" colspan="8">';
          //첫 페이지로 이동 
          if(pageDTO.firstMove) {
            str += '<a href="#" class="first-move">첫페이지로</a>';
          }
          
          //이전페이지로 이동
          if(pageDTO.backPage) {
            str += '<a href="#" class="back-page">◀</a>  ';
          }
          
          //페이지 수 셋팅
          for(var pageCount = pageDTO.startPage; pageCount <= pageDTO.endPage; pageCount++) {
            if(pageCount !== pageDTO.page){
              str += '<a href="#" class="page-number" value="' + pageCount + '">' + pageCount + '</a>  '
            }else {
              str += '<a href="#" class="current-page" value="' + pageCount + '">' + pageCount + '</a>'
            }
          }
          
          //다음 페이지
          if(pageDTO.nextPage) {
            str += '<a href="#" class="next-page">▶</a>';
          }
          
          //마지막 페이지
          if(pageDTO.lastMove) {
            str += '<a href="#" class="last-move">마지막페이지로</a>';
          }
          return str;
        }
    })(jQuery);
    
      
    </script>
  </head>
  <body>
    <!-- Sidebar/menu -->
    <nav
      class="w3-sidebar w3-blue w3-collapse w3-top w3-large w3-padding"
      style="z-index:3;width:300px;font-weight:bold;"
      id="mySidebar"
    >
      <br />
      <a
        href="javascript:void(0)"
        onclick="w3_close()"
        class="w3-button w3-red w3-hide-large w3-display-topleft"
        style="width:100%;font-size:22px"
        >Close Menu</a
      >
      <div class="w3-container">
        <h3 class="w3-padding-64"><b>KDC 관리자</b></h3>
      </div>
      <div class="w3-bar-block">
        <a
          href="#"
          onclick="w3_close()"
          class="w3-bar-item w3-button w3-hover-white"
          >Home</a
        >
        <a
          href="#showcase"
          onclick="w3_close()"
          class="w3-bar-item w3-button w3-hover-white"
          >유저 리스트</a
        >
        <a
          href="#services"
          onclick="w3_close()"
          class="w3-bar-item w3-button w3-hover-white"
          >운영현황</a
        >
        <a
          href="#designers"
          onclick="w3_close()"
          class="w3-bar-item w3-button w3-hover-white"
          >신고관리</a
        >
        <a
          href="#packages"
          onclick="w3_close()"
          class="w3-bar-item w3-button w3-hover-white"
          >쪽지관리</a
        >
        <a
          href="#contact"
          onclick="w3_close()"
          class="w3-bar-item w3-button w3-hover-white"
          >강사생성</a
        >
      </div>
    </nav>

    <!-- Top menu on small screens -->
    <header
      class="w3-container w3-top w3-hide-large w3-blue w3-xlarge w3-padding"
    >
      <a
        href="javascript:void(0)"
        class="w3-button w3-red w3-margin-right"
        onclick="w3_open()"
        >☰</a
      >
      <span>KDC 관리자</span>
    </header>

    <!-- Overlay effect when opening sidebar on small screens -->
    <div
      class="w3-overlay w3-hide-large"
      onclick="w3_close()"
      style="cursor:pointer"
      title="close side menu"
      id="myOverlay"
    ></div>

    <!-- contect -->
    <div class="w3-main" style="margin-left:340px;margin-right:40px">
      <!-- Header -->
      <div class="w3-container" style="margin-top:80px" id="showcase">
        <h1 class="w3-jumbo"><b>KDC 관리자</b></h1>
        <hr style="width:100%;border:5px solid red" class="w3-round" />
        <h1 class="w3-xxxlarge w3-text-blue"><b>유저리스트</b></h1>
      </div>

      <!-- 유저리스트 -->
      <div class="w3-row-padding">
        <table class="admin-table">
          <tr>
                <th>유저id</th>
                <th>유저이름</th>
                <th>닉네임</th>
                <th>생년월일</th>
                <th>휴대폰번호</th>
                <th>이메일</th>
                <th>가입일</th>
                <th>유저 추방</th>
            </tr>
            
        </table>
      </div>
      <div class="member-paging"></div>

      <!-- 운영현황 -->
      <div class="w3-container" id="services" style="margin-top:75px; float: left;">
        <h1 class="w3-xxxlarge w3-text-blue"><b>운영현황</b></h1>
        <div id="chart_div"></div>
      </div>

      <!-- 방문자 수 현황 -->
      <div class="w3-container" id="services" style="margin-top:75px; float: left;">
        <h1 class="w3-xxxlarge w3-text-blue"><b>방문자 수현황</b></h1>
        <div id="columnChart_div"></div>
      </div>
      
      <!-- 신고관리 -->
      <div class="w3-container" id="designers" style="margin-top:75px">
        <h1 class="w3-xxxlarge w3-text-blue"><b>신고관리</b></h1>

        <div class="w3-row-padding">
          <table class="report-table">
          <div class="optionSelect">
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
             <tr>
                  <th>번호</th>
                  <th>신고인 아이디</th>
                  <th>피신고인 아이디</th>
                  <th>신고 내용</th>
                  <th>신고한 날짜</th>
                  <th>삭제</th>
              </tr>
              
            <c:choose>
            <c:when test="${empty requestScope.reportList}">
            <tr>
                <td class="empty-list" colspan="6">
                  신고내역이 없습니다.
                </td>
            </tr>
            </c:when> 
            <c:otherwise>
               <c:forEach items="${requestScope.reportList}" var="reportList">
                      <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
                          <td>
                              
                          </td>
                          <td>
                              ${reportList.reportReporterId}
                          </td>
                          <td>
                            <a href="${pageContext.request.contextPath}/message/${messageList.messageNum}"> <!-- path variable RESTful -->
                              ${reportList.replyBoardDTO.replyBoardWriterId}
                            </a>
                          </td>
                          <td>${reportList.reportPurpose}</td>
                          <td>${reportList.reportDate}</td>
                          <td>
                              <input type="button" value="삭제" id="deleteReport" onclick="deleteReport(${reportList.reportPk})">
                          </td>
                      </tr>
                </c:forEach>
              </c:otherwise>
              </c:choose>
          </table>
        </div>

        <!-- 쪽지관리  -->
        <div class="w3-container" id="packages" style="margin-top:75px">
          <h1 class="w3-xxxlarge w3-text-blue"><b>쪽지관리</b></h1>
            <table class="message-table">
              <tr>
                <th><input type="checkbox" name="checkBoxAll" id="checkBoxAll" ></th>
                <th>보낸사람</th>
                <th>쪽지제목</th>
                <th>전송일</th>
                <th>답장</th>
                <th>삭제</th>
              </tr>
          
              <c:choose>
                <c:when test="${empty requestScope.messageList}">
                  <tr>
                    <td class="empty-list" colspan="6">
                      받은 쪽지가 없습니다.
                    </td>
                  </tr>
                </c:when>
                <c:otherwise>
                <!-- 접속된 ID로  message list 출력 -->
                  <c:forEach items="${requestScope.messageList}" var="message">
                    <tr onmouseover="this.style.background='#eaeaea'"
                      onmouseout="this.style.background='white'" id="messageTitles"> <!-- id="messageTitles" -->
                      <td>
                            <input type="checkbox" name="checkNum"  value="${message.messageNum}"  id="${message.messageNum}">
                      </td>
                      <td>${message.senderId}</td>
                      <td>
                        <a href="${pageContext.request.contextPath}/message/${message.messageNum}">
                              <!-- path variable RESTful -->
                              <!-- 읽은 메세지, 읽지않은 메세지 구분 -->
                              <c:choose>
                                <c:when test="${message.messageIsRead == 'FALSE'}">
                                  ${message.messageTitle}
                                </c:when>
                                <c:otherwise>${message.messageTitle}
                                </c:otherwise>
                              </c:choose>
                          </a>
                      </td>
                      <td>${message.messageDate}</td>
                      <td>
                          <!-- 답장 클릭시, ajax로 ID유뮤체크 후 답장 페이지로 이동  -->
                          <input type="button" value="답장" id="replyMessage">
                          <input type="hidden" name="senderId" value="${message.senderId}">
                      </td>
                      <td>
                          <input type="hidden" value="${message.messageNum}">
                          <input  type="button" value="삭제" id="deleteMessage" >
                      </td>
                    </tr>
                  </c:forEach>
                </c:otherwise>
              </c:choose>
            </table>
        </div>

      <!-- 강사생성 -->
      <div class="w3-container" id="contact" style="margin-top:75px">
        <h1 class="w3-xxxlarge w3-text-blue"><b>강사생성</b></h1>
        <form action="/action_page.php" target="_blank">
          <div class="w3-section">
            <label>ID</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="memberId" 
              placeholder="영문, 숫자로만 6~12자리 입력" 
              required
            />
            <smal class="ajax"> 아이디를 입력하세요</smal>
          </div>
          <div class="w3-section">
            <label>Password</label>
            <input
              class="w3-input w3-border"
              type="password"
              name="memberPwd"
              placeholder="숫자, 영문, 특수기호 포함 8자리 이상"
              required
            />
            <smal class="ajax"> 비밀번호입력 </smal>
          </div>
          <div class="w3-section">
            <label>Password Confirm</label>
            <input
              class="w3-input w3-border"
              type="password"
              name="memberPwdConfirm"
              required
            />
            <smal class="ajax"> 비밀번호 확인</smal>
          </div>
          <div class="w3-section">
            <label>이름</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="memberName"
              required
            />
          </div>
          <div class="w3-section">
            <label>닉네임</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="memberNickName"
              placeholder="2~8자리 입력"
              required
            />
            <smal class="ajax"> 닉네임 입력 </smal>
          </div>
                    <div class="w3-section">
            <label>생일</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="memberBirth"
              placeholder="  1900.01.00"
              required
            />
          </div>
          <div class="w3-section">
            <label>전화번호</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="memberPhone"
              placeholder="'-' 제외하고 입력"
              required
            />
            <smal class="ajax"> 전화번호 체크 </smal>
          </div>
          <div class="w3-section">
            <label>Email</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="memberEmail"
              required
            />
            <smal class="ajax"> Email 확인 </smal>
          </div>
          <div class="w3-section">
            <label>인증번호</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="authCode"
              placeholder=" 인증번호를 입력하세요"
              required
            />
          </div>
          <button
            type="submit"
            class="w3-button w3-block w3-padding-large w3-red w3-margin-bottom"
          >
           강사생성
          </button>
        </form>
      </div>

      <!-- End page content -->
    </div>



    <script>
      // Script to open and close sidebar
      function w3_open() {
        document.getElementById('mySidebar').style.display = 'block';
        document.getElementById('myOverlay').style.display = 'block';
      }

      function w3_close() {
        document.getElementById('mySidebar').style.display = 'none';
        document.getElementById('myOverlay').style.display = 'none';
      }

    </script>
  </body>
</html>
