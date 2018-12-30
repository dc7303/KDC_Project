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
    a {
      text-decoration: none;
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
      border-collapse:collapse;
    }
    th {
      background-color: #2196F3;
      color: white;
    }
    td {
      text-align: center;
      font-size: 13px;
      padding: none;
    }
    .empty-list {
      text-align: center;
    }
    .page-selector {
      border: none;
      background-color: white;
    }
    .page-selector span {
      font-size: 15px;
      padding-left: 8px;
      padding-right: 8px;
      cursor: pointer;
    }
    .page-selector span:hover {
      color: orange;
    }
    .current-page {
      color: #FF0000;
      text-weight: 1200;
    }
    
    .member-search, .report-search, .message-search {
      padding-left: 32%;
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
          xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
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

      /**
       *  방문자 수 차트
       */
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
          var resultStr = reportPaging(result);
          jq('.report-table').eq(0).html(resultStr);
          
        },
        error: function(err) {
          console.log('err : ' + err);
        }
      }); 
      
      /**
       * 메세지 리스트 가져오기
       */
      jq.ajax({
        url: '${pageContext.request.contextPath}/adminMessageList',
        type: 'get',
        dataType: 'json',
        data: {
          currentPage: 1,
        },
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
        },
        success: function(result) {
          var resultStr = messagePaging(result);
          jq('.message-table').eq(0).html(resultStr);
        },
        error: function(err) {
          console.log('err : ' + err);
        }
      });
      
      
      
      //member 페이지 번호 click 이벤트
      jq(document).on('click', '.member-page-at', function(event) {
        var url = '${pageContext.request.contextPath}/adminMemberList';
        
        var keyword = jq('#memberPageKeyword').val();
        var word = jq('#memberPageWord').val();
        console.log(keyword,word);
        //keyword가 전체검색이 아니라면
        if(keyword !== 'all') {
          url = '${pageContext.request.contextPath}/selectMemberByKeyword?keyword=' + keyword + '&word=' + word;
        }
 
        var currentPage = jq(this)[0].title;
        jq.ajax({
          url: url,
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
      
    //report 페이지 번호 click 이벤트
      jq(document).on('click', '.report-page-at', function(event) {
        var currentPage = jq(this)[0].title;
        jq.ajax({
          url: '${pageContext.request.contextPath}/adminReportList',
          type: 'get',
          dataType: 'json',
          data: {
            currentPage: currentPage,
          },
          beforeSend: function(xhr) {
            xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
          },
          success: function(result) {
            var strResult = reportPaging(result);
            jq('.report-table').eq(0).html(strResult);
          },
          error: function(err) {
            console.log('err : ' + err);
          }
        });
      });
    
    //message 페이지 번호 click 이벤트
      jq(document).on('click', '.message-page-at', function(event) {
        var currentPage = jq(this)[0].title;
        jq.ajax({
          url: '${pageContext.request.contextPath}/adminMessageList',
          type: 'get',
          dataType: 'json',
          data: {
            currentPage: currentPage,
          },
          beforeSend: function(xhr) {
            xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
          },
          success: function(result) {
            var strResult = messagePaging(result);
            jq('.message-table').eq(0).html(strResult);
          },
          error: function(err) {
            console.log('err : ' + err);
          }
        });
      });
      
      
      
      /**
       *  멤버 리스트 렌더링 모듈
       */
      function memberPaging(result) {
          var memberList = result.memberList;
          var pageDTO = result.pageDTO;
          var keyword = result.keyword;		//검색 키워드
          var word = result.word;			//검색 내용
          //멤버 리스트가 존재할때
		var str =
				'<tr><th>유저id</th><th>유저이름</th><th>닉네임</th><th>생년월일</th>' + 
				'<th>휴대폰번호</th><th>이메일</th><th>가입일</th><th>탈퇴여부</th>' + 
				'<th>유저 추방</th></tr>';
          
          if(memberList.length !== 0) {
            //멤버 리스트 셋팅
            for(var i = 0; i < memberList.length; i++) {
              //탈퇴 여부에 따른 버튼 및 메세지 셋팅
              var memberIsWithdrawal = memberList[i].memberIsWithdrawal;
              var withdrawalBtn = '';
              if(memberIsWithdrawal) {
                memberIsWithdrawal = '<td style="color:red">탈퇴</td>';
                withdrawalBtn = '<td><input type="button" value="복구" class="secessionMember" value="' + memberList[i].memberId + '"></td></tr>';
              }else {
                memberIsWithdrawal = '<td style="color:blue">정상</td>';
                withdrawalBtn = '<td><input type="button" value="삭제" class="secessionMember" value="' + memberList[i].memberId + '"></td></tr>';
              }
              str += '<tr class="table-tr w3-hover-amber"><td>' + memberList[i].memberId + '</td>';
              str += '<td>' + memberList[i].memberName + '</td>';
              str += '<td>' + memberList[i].memberNickName + '</td>';
              str += '<td>' + memberList[i].memberBirth + '</td>';
              str += '<td>' + memberList[i].memberPhone + '</td>';
              str += '<td>' + memberList[i].memberEmail + '</td>';
              str += '<td>' + memberList[i].memberDate + '</td>';
              str += memberIsWithdrawal;
              str += withdrawalBtn;
            }
          }else {
            str += '<td class="empty-list" colspan="8">등록된 유저가 없습니다.</td>'
          }
          
          
          str += '<td class="page-selector" colspan="8">';
          //첫 페이지로 이동 
          if(pageDTO.firstMove) {
            str += '<span class="member-page-at" title="' + 1 + '">첫페이지로</span>';
          }
          
          //이전페이지로 이동
          if(pageDTO.backPage) {
            str += '<span class="member-page-at" title="' + (pageDTO.page - 1) + '">◀</span>';
          }
          
          //페이지 수 셋팅
          for(var pageCount = pageDTO.startPage; pageCount <= pageDTO.endPage; pageCount++) {
            if(pageCount !== pageDTO.page){
              str += '<span class="member-page-at" title="' + pageCount + '">' + pageCount + '</span>'
            }else {
              str += '<span class="current-page" title="' + pageCount + '">' + pageCount + '</span>'
            }
          }
          
          //다음 페이지
          if(pageDTO.nextPage) {
            str += '<span class="member-page-at" title="' + (pageDTO.page + 1) + '">▶</span>';
          }
          
          //마지막 페이지
          if(pageDTO.lastMove) {
            str += '<span class="member-page-at" title="' + pageDTO.endPage + '">마지막페이지로</span>';
          }
          
          //키워드 셋팅(키워드 조회시 페이징 처리 렌더링시 사용됨)
          if(keyword !== null) {
            str += '<input type="hidden" id="memberPageKeyword" value="' + keyword + '"/>';
          }else {
            str += '<input type="hidden" id="memberPageKeyword" value="all"/>';
          }
          
          if(word !== null) {
            str += '<input type="hidden" id="memberPageWord" value="' + word + '"/>';
          }else {
            str += '<input type="hidden" id="memberPageWord" value="all"/>';
          }
          
          
          return str;
        }
      
      
      
      /**
       *  신고 리스트 렌더링 모듈
       */
      function reportPaging(result) {
          var reportList = result.reportList;
          var pageDTO = result.pageDTO;
          
          //report 리스트가 존재할때
          
		var str = '<tr><th>신고인</th><th>피신고인자</th><th>게시판</th>' + 
		'<th>신고 내용</th><th>신고한 날짜</th><th>삭제</th></tr>';
          
          if(reportList.length !== 0) {
            //report 리스트 셋팅
            for(var i = 0; i < reportList.length; i++) {
              //게시판 종류셋팅 
              var boardFind = reportList[i].replyBoardDTO.replyBoardClassification;
              if(boardFind === 'tech') {
                boardFind = '테크Q&A';
              }else if(boardFind === 'lib') {
                boardFind = '기술공유';
              }else if(boardFind === 'study') {
                boardFind = '스터디'
              }
              
              str += '<tr class="table-tr w3-hover-amber"><td>' + reportList[i].reportReporterId + '</td>';
              str += '<td>' + reportList[i].replyBoardDTO.replyBoardWriterId + '</td>';
              str += '<td>' + boardFind + '</td>';
              str += '<td>' + reportList[i].reportPurpose + '</td>';
              str += '<td>' + reportList[i].reportDate + '</td>';
              str += '<td><input type="button" value="삭제" id="deleteReport" onclick="deleteReport(' + reportList[i].reportPk + ')"></td></tr>'
            }
          }else {
            str += '<td class="empty-list" colspan="5">등록된 신고가 없습니다.</td>'
          }
          
          
          str += '<td class="page-selector" colspan="8">';
          //첫 페이지로 이동 
          if(pageDTO.firstMove) {
            str += '<span class="report-page-at" title="' + 1 + '">첫페이지로</span>';
          }
          
          //이전페이지로 이동
          if(pageDTO.backPage) {
            str += '<span class="report-page-at" title="' + (pageDTO.page - 1) +  '">◀</span>  ';
          }
          
          //페이지 수 셋팅
          for(var pageCount = pageDTO.startPage; pageCount <= pageDTO.endPage; pageCount++) {
            if(pageCount !== pageDTO.page){
              str += '<span class="report-page-at" title="' + pageCount + '">' + pageCount + '</span>'
            }else {
              str += '<span class="current-page" title="' + pageCount + '">' + pageCount + '</span>'
            }
          }
          
          //다음 페이지
          if(pageDTO.nextPage) {
            str += '<span class="report-page-at" title="' + (pageDTO.page + 1) + '">▶</span>';
          }
          
          //마지막 페이지
          if(pageDTO.lastMove) {
            str += '<span class="report-page-at" title="' + pageDTO.endPage + '">마지막페이지로</span>';
          }
          return str;
        }
      
      
      
      
      /**
       *  메세지 리스트 렌더링 모듈
       */
      function messagePaging(result) {
          var messageList = result.messageList;
          var pageDTO = result.pageDTO;
          //메세지 리스트가 존재할때
		var str = '<tr><th><input type="checkbox" name="checkBoxAll" id="checkBoxAll"></th><th>보낸사람</th>' + 
					'<th>쪽지제목</th><th>전송일</th><th>답장</th><th>삭제</th></tr>';
          
          if(messageList.length !== 0) {
            //메세지 리스트 셋팅
            for(var i = 0; i < messageList.length; i++) {
              str += '<tr class="table-tr w3-hover-amber"><td><input type="checkbox"/></td>';
              str += '<td>' + messageList[i].senderId + '</td>';
              str += '<td><a href="${pageContext.request.contextPath}/message/' + messageList[i].messageNum + '">'
              				+ messageList[i].messageTitle + '</a></td>';
              str += '<td>' + messageList[i].messageDate + '</td>';
              str += '<td><input type="button" value="답장" id="replyMessage">' +
              			'<input type="hidden" name="senderId" value="' + messageList[i].senderId + '"></td>';
              str += '<td><input type="hidden" value="' + messageList.messageNum + '">' + 
              		'<input type="button" value="삭제" id="deleteMessage"/></td></tr>';
            }
          }else {
            str += '<td class="empty-list" colspan="8">쪽지가 없습니다.</td>';
          }
          
          str += '<td class="page-selector" colspan="8">';
          //첫 페이지로 이동 
          if(pageDTO.firstMove) {
            str += '<span class="message-page-at" title="' + 1 + '">첫페이지로</span>';
          }
          
          //이전페이지로 이동
          if(pageDTO.backPage) {
            str += '<span class="message-page-at" title="' + (pageDTO.page - 1) + '">◀</span>  ';
          }
          
          //페이지 수 셋팅
          for(var pageCount = pageDTO.startPage; pageCount <= pageDTO.endPage; pageCount++) {
            if(pageCount !== pageDTO.page){
              str += '<span class="message-page-at" title="' + pageCount + '">' + pageCount + '</span>'
            }else {
              str += '<span class="current-page" title="' + pageCount + '">' + pageCount + '</span>'
            }
          }
          
          //다음 페이지
          if(pageDTO.nextPage) {
            str += '<span class="message-page-at" title="' + (pageDTO.page + 1) + '">▶</span>';
          }
          
          //마지막 페이지
          if(pageDTO.lastMove) {
            str += '<span class="message-page-at" title="' + pageDTO.endPage + '">마지막페이지로</span>';
          }
          return str;
        }
      
      
      /**
       * 멤버 키워드 검색
       */
       jq(document).on('click', '#memberSearchBtn', function() {
         var keyword = jq('select[name=memberSearchKeyword]').val();
         var word = jq('input[name=memberSearchInput]').val();
         //키워드가 없을 때 alert
         if(keyword === 'none') {
           alert('검색하실 키워드를 선택해주세요.');
         //내용이 없을 때 alert
         }else if(word === null || word === '') {
           alert('검색하실 내용을 입력해주세요.')
         }else {
           jq.ajax({
             url: '${pageContext.request.contextPath}/selectMemberByKeyword',
             type: 'get',
             dataType: 'json',
             data: {
               currentPage: 1,
               keyword: keyword,
               word: word,
             },
             beforeSend: function(xhr) {
               xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
             },
             success: function(result) {
               var resultStr = memberPaging(result);
               jq('input[name=memberSearchInput]').val('');
               jq('.admin-table').eq(0).html(resultStr);
             },
             error: function(err) {
               console.log('err : ' + err);
             }
           });
         }
       });
      
      /**
       * 멤버 추방 or 복구
       */
       jq(document).on('click', '.secessionMember', function() {
         var memberId = jq(this).parent().parent().children().eq(0).text();
         var memberStatus = jq(this).parent().parent().children().eq(7)[0];
         
         //추방 또는 복구 선택
         var isWithDrawal = true;	//복구 삭제 플래그
         var confirm = false;		//confirm 메소드 변수
         
         if(jq(this).val() === '복구') {
           confirm = window.confirm('정말 복구하시겠습니까?');
           isWithDrawal = false;
         }else if(jq(this).val() === '삭제') {
           confirm = window.confirm('정말 추방하시겠습니까?');
           isWithDrawal = true;
         }
         console.log(confirm);
         if(confirm){
           jq.ajax({
             url: '${pageContext.request.contextPath}/member/memberDelete',
             type: 'get',
             dataType: 'json',
             data: {
     			memberId: memberId,
     			isWithDrawal: isWithDrawal,
             },
             beforeSend: function(xhr) {
               xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}' );
             },
             success: function(result) {
               alert(result);
               if(isWithDrawal) {
                 memberStatus.html('<td style="color:red">탈퇴</td>');
               }else {
                 memberStatus.html('<td style="color:blue">복구</td>'); 
               }
             },
             error: function(err) {
               console.log('err : ' + err);
             }
           });
         }
         
       });
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
        <table id="admin-table" class="admin-table w3-table w3-centered">
          <tr>
            <th>유저id</th>
            <th>유저이름</th>
            <th>닉네임</th>
            <th>생년월일</th>
            <th>휴대폰번호</th>
            <th>이메일</th>
            <th>가입일</th>
            <th>가입상태</th>
            <th>유저 추방</th>
          </tr>
        </table>
        <div class="member-search">
          <select name="memberSearchKeyword">
            <option value="none">키워드선택</option>
            <option value="memberId">유저ID</option>
            <option value="memberName">유저이름</option>
            <option value="memberNickName">유저닉네임</option>
            <option value="memberEmail">유저이메일</option>
          </select>
          <input type="text" name="memberSearchInput"/>
          <input type="button" id="memberSearchBtn" value="검색"/>
        </div>
      </div>


      <!-- 운영현황 -->
      <div class="w3-container" id="services" style="margin-top:75px; float: left;">
        <h1 class="w3-xxxlarge w3-text-blue"><b>운영현황</b></h1>
        <div id="chart_div"></div>  
      </div>

      <!-- 방문자 수 현황 -->
      <div class="w3-container" style="margin-top:75px; float: left ;">
        <h1 class="w3-xxxlarge w3-text-blue"><b>방문자 수 현황</b></h1>
        <div id="columnChart_div"></div>
      </div>
      
      <!-- 신고관리 -->
      <div class="w3-container" id="designers" style="margin-top:75px">
        <h1 class="w3-xxxlarge w3-text-blue"><b>신고관리</b></h1>

        <div class="w3-row-padding">
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
          <table class="report-table w3-table w3-centered">
             <tr>
                  <th>신고인</th>
                  <th>피신고인</th>
                  <th>게시판</th>
                  <th>신고 내용</th> 
                  <th>신고한 날짜</th>
                  <th>삭제</th>
              </tr>
          </table>
            <div class="report-search">
            <select name="reportSearchKeyword">
              <option value="none">키워드선택</option>
              <option value="memberId">유저ID</option>
              <option value="memberName">유저이름</option>
              <option value="memberNickName">유저닉네임</option>
              <option value="memberEmail">유저이메일</option>
            </select>
            <input type="text" name="reportSearchInput"/>
            <input type="button" id="reportSearchBtn" value="검색"/>
          </div>
        </div>
      

        <!-- 쪽지관리  -->
        <div class="w3-container" id="packages" style="margin-top:75px">
          <h1 class="w3-xxxlarge w3-text-blue"><b>쪽지관리</b></h1>
            <table class="message-table w3-table w3-centered">
              <tr>
                <th><input type="checkbox" name="checkBoxAll" id="checkBoxAll" ></th>
                <th>보낸사람</th>
                <th>쪽지제목</th>
                <th>전송일</th>
                <th>답장</th>
                <th>삭제</th>
              </tr>
            </table>
            <div class="message-search">
            <select name="messageSearchKeyword">
              <option value="none">키워드선택</option>
              <option value="memberId">유저ID</option>
              <option value="memberName">유저이름</option>
              <option value="memberNickName">유저닉네임</option>
              <option value="memberEmail">유저이메일</option>
            </select>
            <input type="text" name="messageSearchInput"/>
            <input type="button" id="messageSearchBtn" value="검색"/>
          </div>
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


    <input type="hidden" name="contextPath" value="${pageContext.request.contextPath }"/>
    <input typp="hidden" name="csrfName" value="${_csrf.headerName }"/>
    <input type="hidden" name="csrfToken" value="${_csrf.token }"/>
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
