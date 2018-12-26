<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <table>
          <tr>
            <th>유저아이디</th>
            <th>유저이름</th>
            <th>생년월일</th>
            <th>가입일</th>
            <th>탈퇴여부</th>
          </tr>
          <tr>
            <td>dc7303</td>
            <td>최동철</td>
            <td>910507</td>
            <td>1991.07.07</td>
            <th>탈퇴</th>
          </tr>
          <tr>
            <td>dc7303</td>
            <td>최동철</td>
            <td>910507</td>
            <td>1991.07.07</td>
            <th>탈퇴</th>
          </tr>
          <tr>
            <td>dc7303</td>
            <td>최동철</td>
            <td>910507</td>
            <td>1991.07.07</td>
            <th>탈퇴</th>
          </tr>
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
          <table>
            <tr>
              <th>유저아이디</th>
              <th>내용</th>
              <th>사유</th>
              <th>신고일</th>
              <th>읽음여부</th>
            </tr>
            <tr>
              <td>dc7303</td>
              <td>욕했어요</td>
              <td>욕설 및</td>
              <td>1991.07.07</td>
              <th>탈퇴</th>
            </tr>
            <tr>
              <td>dc7303</td>
              <td>최동철</td>
              <td>910507</td>
              <td>1991.07.07</td>
              <th>탈퇴</th>
            </tr>
            <tr>
              <td>dc7303</td>
              <td>최동철</td>
              <td>910507</td>
              <td>1991.07.07</td>
              <th>탈퇴</th>
            </tr>
          </table>
        </div>

        <!-- 쪽지관리  -->
        <div class="w3-container" id="packages" style="margin-top:75px">
          <h1 class="w3-xxxlarge w3-text-blue"><b>쪽지관리</b></h1>

          <p>
            Some text our prices. Lorem ipsum consectetur adipiscing elit, sed
            do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
            enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
            ut aliquip ex ea commodo consequat. Duis aute irure
          </p>
        </div>

      <!-- 강사생성 -->
      <div class="w3-container" id="contact" style="margin-top:75px">
        <h1 class="w3-xxxlarge w3-text-blue"><b>강사생성</b></h1>

        <p>
          Do you want us to style your home? Fill out the form and fill me in
          with the details :) We love meeting new people!
        </p>
        <form action="/action_page.php" target="_blank">
          <div class="w3-section">
            <label>Name</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="Name"
              required
            />
          </div>
          <div class="w3-section">
            <label>Email</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="Email"
              required
            />
          </div>
          <div class="w3-section">
            <label>Message</label>
            <input
              class="w3-input w3-border"
              type="text"
              name="Message"
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
