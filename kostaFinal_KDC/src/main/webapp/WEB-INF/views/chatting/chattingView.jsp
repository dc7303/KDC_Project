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
      /*��밡 ���� �޽���  */
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
    //������ 
	var sock = new SockJS("<c:url value="/echo"/>");
    var mySession = null;
    jq(function(){
        //ä�÷α� �ҷ�����
        var list = new Array(); 
		<c:forEach items="${chatLog}" var="item">
			list.push("${item}");
		</c:forEach>
        for(var i=0;i<list.length;i++){
          createMsg(list[i]);
        }
        
     	sock.onmessage = onMessage;
    	sock.onclose = onClose;
     
    	//���� Ŭ�� �̺�Ʈ
    	jq('#send-btn').click(function(){
    		console.log('send message....');
    		sendMessage();
    		jq('#message').val('');
    		
    	});
    	
    	//text�ʵ忡�� ���� �����ÿ��� �̺�Ʈ �߻�
    	jq('#message').on('keypress',function(event){
    	  if(event.keyCode===13){
    	    jq('#send-btn').trigger('click');
    	    jq(this).val('');
    	  }
    	});
    	
    	//���� �α��ε� ����ڰ� �޽����� ������ �Լ�
    	function sendMessage(){
    		var code = jq('input[name=room-code]').val();
    		var memberId = jq('#member-id').val();
    		var chatFile = jq('#chat-file').val();
    		sock.send(code+'|'+memberId+'|'+chatFile+'|'+$("#message").val());
    	}
    	
    	//�������� ������ �޽��� ��� �Լ�
    	function onMessage(evt){
    		var data = evt.data;
    		createMsg(data);
    	}
    	
    	function onClose(evt){
    		console.log('�������');
    	}
    	
    	//�޽��� ������Ʈ ����
    	function createMsg(msg){
    	  	var memberId = null;
    	  	var message = null;
    		
    		var strArray = msg.split('|');
    		
    		memberId = strArray[0];
    		message = strArray[1];
    		
    		//ä�������� ������ ���||�ٸ�����ΰ��
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
    		//��ũ�� ���ϴ����� 
    		jq('#chatting-room').scrollTop(jq('#chatting-room').prop('scrollHeight'));
    	}
    });
    
    </script>
  </head>
  <body>
  <sec:authentication var="member" property="principal" />
   <input id="member-id" type="hidden" value="${member.memberNickName}">
    <div id="chatting-room">
      <div id="start-flag">----ä���� ó���Դϴ�----</div>      
    </div>
    <div id="chatting-input">
      <input id="message" name="message" type="text" />
      <input id="send-btn" type="button" value="�Է�" />
      <input name="room-code" type="hidden" value="${roomCode}" />
      <input id="chat-file" type="hidden" value="${chatFile}"/>
    </div>
  </body>
</html>