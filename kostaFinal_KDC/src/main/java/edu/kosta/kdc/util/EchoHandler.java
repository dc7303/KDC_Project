package edu.kosta.kdc.util;

import java.util.ArrayList;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.sun.media.jfxmedia.logging.Logger;

public class EchoHandler extends TextWebSocketHandler {

	
	private Map<String, List<WebSocketSession>> socketMap = new HashMap<>();
	private List<WebSocketSession> sessionList = null;
	
	/*클라이언트의 정보를 가져와 연결확인작업*/
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		//클라이언트의 세션을 세션리스트에 add()로 추가
		
		//sessionList.add(session);
		System.out.println(session.getId()+"님이 연결됨");
		System.out.println("채팅방 입장자 : "+session.getId());
	}
	
	/*연결된 모든 사용자에게 for문으로 메시지를 전달*/
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//메시지에서 code부분 추출
		String code = null;
		String pureMessage = null;
		String memberId = null;
		code = message.getPayload().split("\\\\")[0];
		memberId = message.getPayload().split("\\\\")[1];
		pureMessage = message.getPayload().split("\\\\")[2];
		
		//code에 대한 세션리스트가 없는경우 socketMap에 추가
		if(socketMap.get(code)==null) {
			socketMap.put(code, new ArrayList<WebSocketSession>());
		}
		sessionList = socketMap.get(code);
		if(!sessionList.contains(session)) {
			sessionList.add(session);
		}
		
		for(WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(memberId+" | "+pureMessage));
		}
	}
	
	/*클라이언트와 연결이 끊어진 경우*/
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//세션리스트에서 제거
		sessionList.remove(session);
		
		System.out.println("채팅 퇴장: "+session.getId());
		
		
	}
}
