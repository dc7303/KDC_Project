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
      
      
    </style>
    <title>Insert title here</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script type="text/javascript">
	const jq = jQuery.noConflict();
    //������ 
	var sock = new SockJS("<c:url value="/echo"/>");
    var mySession = null;
    jq(function(){
     	sock.onmessage = onMessage;
    	sock.onclose = onClose;
     
    	//���� Ŭ�� �̺�Ʈ
    	jq('#send-btn').click(function(){
    		console.log('send message....');
    		sendMessage();
    	});
    	//text�ʵ忡�� ���� �����ÿ��� �̺�Ʈ �߻�
    	jq('#message').on('keypress',function(event){
    	  if(event.keyCode===13){
    	    jq('#sendBtn').trigger('click');
    	    jq(this).val('');
    	  }
    	});
    	
    	
    	function sendMessage(){
    		var code = jq('input[name=room-code]').val();
    		var memberId = jq('#member-id').val();
    		sock.send(code+'\\'+memberId+'\\'+$("#message").val());
    	}
    	
    	function onMessage(evt){
    		var data = evt.data;
    		var memberId = null;
    		var message = null;
    		
    		var strArray = data.split('|');
    		
    		memberId = strArray[0];
    		message = strArray[1];
    		
    		console.log(memberId);
    		console.log(jq('#member-id').val());
    		
    		/* if(memberId === jq('#member-id').val()){
    		  alert('in my memberId');
    		  var messageBlock = jq('<div>').attr('class','my-block');
    		  jq('#chatting-room').append(jq(messageBlock));
    		  var textBlock = jq('<span>').attr('class','my-text');
    		  jq('.other-block').last().append(jq(textBlock));
    		}else{
    		  var messageBlock = jq('<div>').attr('class','other-block');
    		  jq('#chatting-room').append(jq(messageBlock));
    		  var idBlock = jq('<span>').attr('class','other-id').text(memberId);
    		  jq('.other-block').last().append(jq(idBlock));
    		  var textBlock = jq('<span>').attr('class','other-text');
    		  jq('.other-block').last().append(jq(textBlock));
    		}
    		jq('#chatting-room').append(jq(messageBlock)); */
    		
    		
    		
    		/* console.log('�޽����� ������� : ' + sessionid);
    		console.log('����޽��� : ' + message);
    		 */
    	}
    	
    	function onClose(evt){
    		console.log('�������');
    	}
    });
    
    </script>
  </head>
  <body>
  <sec:authentication var="member" property="principal" />
   <input id="member-id" type="hidden" value="${member.memberId}">
    <div id="chatting-room">
      <div id="start-flag">----ä���� ó���Դϴ�----</div>      
    </div>
    <div id="chatting-input">
      <input id="message" name="message" type="text" />
      <input id="send-btn" type="button" value="�Է�" />
      <input name="room-code" type="hidden" value="${roomCode}" />
     
    </div>

  </body>
</html>