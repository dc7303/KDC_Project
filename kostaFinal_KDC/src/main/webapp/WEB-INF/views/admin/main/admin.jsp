<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
  </style>
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
        <hr style="width:3000px;border:5px solid red" class="w3-round" />
        <h1 class="w3-xxxlarge w3-text-blue"><b>유저리스트</b></h1>
      </div>

      <!-- 유저리스트 -->
      <div class="w3-row-padding">
    <caption>멤버 List</caption>
      <table>
      <tr>
            <th bgcolor="#00cc00">
                <p align="center">
                <font color="white"><b><span style="font-size:9pt;">유저id</span></b></font></p>
            </th>
            <th bgcolor="#00cc00">
                <p align="center"><font color="white"><b><span style="font-size:9pt;">유저이름</span></b></font></p>
            </th>
            <th bgcolor="#00cc00">
                <p align="center"><font color="white"><b><span style="font-size:9pt;">닉네임</span></b></font></p>
            </th>
            <th bgcolor="#00cc00">
                <p align="center"><font color="white"><b><span style="font-size:9pt;">생년월일</span></b></font></p>
            </th>
            
            <th bgcolor="#00cc00">
                <p align="center"><font color="white"><b><span style="font-size:9pt;">휴대폰번호</span></b></font></p>
            </th>
            <th bgcolor="#00cc00">
                <p align="center"><font color="white"><b><span style="font-size:9pt;">이메일</span></b></font></p>
            </th>
            <th bgcolor="#00cc00">
                <p align="center"><font color="white"><b><span style="font-size:9pt;">가입일</span></b></font></p>
            </th>
            <th bgcolor="#00cc00">
                <p align="center"><font color="white"><b><span style="font-size:9pt;">유저 추방</span></b></font></p>
            </th>
        </tr>
        
        <c:choose>
        <c:when test="${empty requestScope.memberList}">
        <tr>
            <td colspan="8">
                <p align="center"><b><span style="font-size:9pt;">등록된 유저가 없습니다.</span></b></p>
            </td>
        </tr>
        </c:when>
        
        <c:otherwise>
      <c:forEach items="${requestScope.memberList}" var="memberList">
            <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
                <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    ${memberList.memberId}</span></p>
                </td>
                <td bgcolor="">
                    <p><span style="font-size:9pt;">
              <%-- <a href="${pageContext.request.contextPath}/board/read/${memberList}"> --%>
                    ${memberList.memberName}</span></p>
                </td>
                <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    ${memberList.memberNickName}</span></p>
                </td>
                <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    ${memberList.memberBirth}</span></p>
                </td>
                 
                 <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    ${memberList.memberPhone}</span></p>
                </td>
                 <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    ${memberList.memberEmail}</span></p>
                </td>
                <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    ${memberList.memberDate}</span></p>
                </td>
                <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    <input type="button" value="삭제" id="deleteMember" onclick="location.href='${memberList.memberId}'"></span></p>
                </td>
            </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
    </table>
      </div>

      <!-- 운영현황 -->
      <div class="w3-container" id="services" style="margin-top:75px">
        <h1 class="w3-xxxlarge w3-text-blue"><b>운영현황</b></h1>
        <h1>차트</h1>
      </div>

      <!-- 신고관리 -->
      <div class="w3-container" id="designers" style="margin-top:75px">
        <h1 class="w3-xxxlarge w3-text-blue"><b>신고관리</b></h1>

        <div class="w3-row-padding">
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
              
            <c:choose>
            <c:when test="${empty requestScope.reportList}">
            <tr>
                <td colspan="8">
                    <p align="center"><b><span style="font-size:9pt;">신고내역이 없습니다.</span></b></p>
                </td>
            </tr>
            </c:when> 
            <c:otherwise>
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
              </c:otherwise>
              </c:choose>
          </table>
        </div>

        <!-- 쪽지관리  -->
        <div class="w3-container" id="packages" style="margin-top:75px">
          <h1 class="w3-xxxlarge w3-text-blue"><b>쪽지관리</b></h1>
            <table align="center" border="0" cellpadding="5" cellspacing="2"
              width="100%" bordercolordark="white" bordercolorlight="black">
          
              <caption> 쪽지함 LIST </caption>
          
              <tr>
                <td bgcolor="#00cc00">
                  <p align="center">
                    <font color="white"><b>
                      <span style="font-size: 9pt;">
                        <input type="checkbox" name="checkBoxAll" id="checkBoxAll" >
                      </span></b></font>
                  </p>
                </td>
                <td bgcolor="#00cc00">
                  <p align="center">
                    <font color="white"><b><span
                        style="font-size: 9pt;">보낸사람</span></b></font>
                  </p>
                </td>
                <td bgcolor="#00cc00">
                  <p align="center">
                    <font color="white" style="font-weight: bold;"><b><span
                        style="font-size:9pt;">쪽지제목</span></b></font>
                  </p>
                </td>
                <td bgcolor="#00cc00">
                  <p align="center">
                    <font color="white"><b><span
                        style="font-size: 9pt;">전송일</span></b></font>
                  </p>
                </td>
                <td bgcolor="#00cc00">
                  <p align="center">
                    <font color="white"><b><span
                        style="font-size: 9pt;">답장</span></b></font>
                  </p>
                </td>
                <td bgcolor="#00cc00">
                  <p align="center">
                    <font color="white"><b><span
                        style="font-size: 9pt;">삭제</span></b></font>
                  </p>
                </td>
              </tr>
          
              <c:choose>
                <c:when test="${empty requestScope.messageList}">
                  <tr>
                    <td colspan="3">
                      <p align="center">
                        <b><span style="font-size: 9pt;">받은 쪽지가 없습니다.</span></b>
                      </p>
                    </td>
                  </tr>
                </c:when>
                <c:otherwise>
                <!-- 접속된 ID로  message list 출력 -->
                  <c:forEach items="${requestScope.messageList}" var="message">
                    <tr onmouseover="this.style.background='#eaeaea'"
                      onmouseout="this.style.background='white'" id="messageTitles"> <!-- id="messageTitles" -->
                      <td bgcolor="">
                        <p align="center">
                          <span style="font-size: 9pt;">
                            <input type="checkbox" name="checkNum"  value="${message.messageNum}"  id="${message.messageNum}">
                          </span>
                        </p>
                      </td>
                      <td bgcolor="">
                        <p align="center">
                          <span style="font-size: 9pt;">
                            ${message.senderId}</span>
                        </p>
                      </td>
                      <td bgcolor="">
                        <p>
                          <a href="${pageContext.request.contextPath}/message/${message.messageNum}">
                              <!-- path variable RESTful -->
                              <!-- 읽은 메세지, 읽지않은 메세지 구분 -->
                              <c:choose>
                                <c:when test="${message.messageIsRead == 'FALSE'}">
                                  <span style="font-size:13pt; font-weight:900;">${message.messageTitle}</span>
                                </c:when>
                                <c:otherwise>
                                  <span style="font-size:9pt;">${message.messageTitle}</span>
                                </c:otherwise>
                              </c:choose>
                          </a>
                        </p>
                      </td>
                      <td bgcolor="">
                        <p align="center">
                          <span style="font-size: 9pt;">
                            ${message.messageDate}</span>
                        </p>
                      </td>
                      <td bgcolor="">
                        <p align="center">
                          <span style="font-size: 9pt;"> 
                          <!-- 답장 클릭시, ajax로 ID유뮤체크 후 답장 페이지로 이동  -->
                          <input type="button" value="답장" id="replyMessage">
                          <input type="hidden" name="senderId" value="${message.senderId}">
                          </span>
                        </p>
                      </td>
                      <td bgcolor="">
                        <p align="center">
                          <span style="font-size: 9pt;"> 
                          <input type="hidden" value="${message.messageNum}">
                          <input  type="button" value="삭제" id="deleteMessage" ></span>
                        </p>
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
