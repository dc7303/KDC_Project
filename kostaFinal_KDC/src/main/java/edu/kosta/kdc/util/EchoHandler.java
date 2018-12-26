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
	
	/*Ŭ���̾�Ʈ�� ������ ������ ����Ȯ���۾�*/
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		//Ŭ���̾�Ʈ�� ������ ���Ǹ���Ʈ�� add()�� �߰�
		
		//sessionList.add(session);
		System.out.println(session.getId()+"���� �����");
		System.out.println("ä�ù� ������ : "+session.getId());
	}
	
	/*����� ��� ����ڿ��� for������ �޽����� ����*/
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//�޽������� code�κ� ����
		String code = null;
		String pureMessage = null;
		String memberId = null;
		code = message.getPayload().split("\\\\")[0];
		memberId = message.getPayload().split("\\\\")[1];
		pureMessage = message.getPayload().split("\\\\")[2];
		
		//code�� ���� ���Ǹ���Ʈ�� ���°�� socketMap�� �߰�
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
	
	/*Ŭ���̾�Ʈ�� ������ ������ ���*/
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//���Ǹ���Ʈ���� ����
		sessionList.remove(session);
		
		System.out.println("ä�� ����: "+session.getId());
		
		
	}
}
