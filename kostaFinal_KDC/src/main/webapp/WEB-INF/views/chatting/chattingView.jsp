<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <style>
      #chatting-room{
        margin-top: 30px;
        width: 80%;
        height: 400px;
        background: lavender;
        overflow: auto;
      }
      #chatting-input{
        width: 80%;
        display: flex;
      }
      #message{
        flex: 1;
      }
      #sendBtn{
        width: 100px;
      }
      #start-flag{
        text-align: center;
      }
      .my-block{
        max-width: 90%;
        margin: 2px 5px;
        background: #7dc855;
        padding: 10px;
        border-radius: 15px;
        float: right; 
      }
      .my-text{
        word-break: break-all;
      }
      .my-align{
        display: flow-root;
      }
      #send-btn{
        background: #7dc855;
        border: none;
      }
      #send-btn:hover{
        background: #7DFC85;
      }
      /*상대가 보낸 메시지  */
      .other-block{
        max-width: 90%;
        margin: 2px 5px;
        float: left;
      }
      .other-align{
        display: flow-root;
      }
      .other-text{
        word-break: break-all;
      }
      .wrap-text{
        border-radius: 15px;
        background: white;
        padding: 10px;
        width: fit-content;
      }
      
    </style>
    <title>Insert title here</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script type="text/javascript">
	const jq = jQuery.noConflict();
    //웹소켓 
	var sock = new SockJS("<c:url value="/echo"/>");
    var mySession = null;
    jq(function(){
        //채팅로그 불러오기
        var list = new Array(); 
		<c:forEach items="${chatLog}" var="item">
			list.push("${item}");
		</c:forEach>
        for(var i=0;i<list.length;i++){
          createMsg(list[i]);
        }
        
     	sock.onmessage = onMessage;
    	sock.onclose = onClose;
     
    	//전송 클릭 이벤트
    	jq('#send-btn').click(function(){
    		console.log('send message....');
    		sendMessage();
    		jq('#message').val('');
    		
    	});
    	
    	//text필드에서 엔터 누를시에도 이벤트 발생
    	jq('#message').on('keypress',function(event){
    	  if(event.keyCode===13){
    	    jq('#send-btn').trigger('click');
    	    jq(this).val('');
    	  }
    	});
    	
    	//현재 로그인된 사용자가 메시지를 보내는 함수
    	function sendMessage(){
    		var code = jq('input[name=room-code]').val();
    		var memberId = jq('#member-id').val();
    		var chatFile = jq('#chat-file').val();
    		sock.send(code+'|'+memberId+'|'+chatFile+'|'+$("#message").val());
    	}
    	
    	//서버에서 보내온 메시지 출력 함수
    	function onMessage(evt){
    		var data = evt.data;
    		createMsg(data);
    	}
    	
    	function onClose(evt){
    		console.log('연결끊김');
    	}
    	
    	//메시지 엘리먼트 생성
    	function createMsg(msg){
    	  	var memberId = null;
    	  	var message = null;
    		
    		var strArray = msg.split('|');
    		
    		memberId = strArray[0];
    		message = strArray[1];
    		
    		//채팅유저가 본인인 경우||다른사람인경우
    		if(memberId === jq('#member-id').val()){
    		  var alignBlock = jq('<div>').attr('class','my-align');
    		  jq('#chatting-room').append(jq(alignBlock));
    		  var messageBlock = jq('<div>').attr('class','my-block');
    		  jq('.my-align').last().append(jq(messageBlock));
    		  var textBlock = jq('<span>').attr('class','my-text').text(message);
    		  jq('.my-block').last().append(jq(textBlock)); 
    		  
    		}else{
    		  var alignBlock = jq('<div>').attr('class','other-align');
    		  jq('#chatting-room').append(jq(alignBlock));
    		  var messageBlock = jq('<div>').attr('class','other-block');
    		  jq('.other-align').last().append(jq(messageBlock));
    		  var idBlock = jq('<span>').attr('class','other-id').text(memberId);
    		  jq('.other-block').last().append(jq(idBlock));
    		  var wrapText = jq('<div>').attr('class','wrap-text');
    		  jq('.other-block').last().append(jq(wrapText));
    		  var textBlock = jq('<span>').attr('class','other-text').text(message);
    		  jq('.wrap-text').last().append(jq(textBlock));
    		  
    		}
    		//스크롤 최하단으로 
    		jq('#chatting-room').scrollTop(jq('#chatting-room').prop('scrollHeight'));
    	}
    });
    
    </script>
  </head>
  <body>
  <sec:authentication var="member" property="principal" />
   <input id="member-id" type="hidden" value="${member.memberNickName}">
    <div id="chatting-room">
      <div id="start-flag">----채팅의 처음입니다----</div>      
    </div>
    <div id="chatting-input">
      <input id="message" name="message" type="text" />
      <input id="send-btn" type="button" value="입력" />
      <input name="room-code" type="hidden" value="${roomCode}" />
      <input id="chat-file" type="hidden" value="${chatFile}"/>
    </div>
  </body>
</html>