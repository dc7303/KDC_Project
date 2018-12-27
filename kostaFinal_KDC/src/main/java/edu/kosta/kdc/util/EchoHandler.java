package edu.kosta.kdc.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
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
	    String [] arr;
		String code = null;
		String memberId = null;
		String contextPath = null;
		String pureMessage = null;
		String newLine = System.getProperty("line.separator");
		arr = message.getPayload().split("\\|");
		code = arr[0];
		memberId = arr[1];
		contextPath = arr[2];
		pureMessage = arr[3];
		
		//uri������� ������
		System.out.println("code : "+code);
        System.out.println("memberId : "+memberId);
        System.out.println("contextPath : "+contextPath);
        System.out.println("pureMessage : "+pureMessage);
		String path = contextPath+"/"+code+".txt";
		
		//���Ͽ� �ۼ�
		BufferedWriter bw = new BufferedWriter(new FileWriter(path,true));
		PrintWriter pw = new PrintWriter(bw,true);
		pw.write(memberId+"|"+pureMessage+newLine);
		pw.flush();
		pw.close();
		
		
		
		//code�� ���� ���Ǹ���Ʈ�� ���°�� socketMap�� �߰�
		if(socketMap.get(code)==null) {
		    System.out.println("���߰�!");
			socketMap.put(code, new ArrayList<WebSocketSession>());
		}
		sessionList = socketMap.get(code);
		if(!sessionList.contains(session)) {
		    System.out.println("�����߰�!");
			sessionList.add(session);
		}
		
		for(WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(memberId+"|"+pureMessage));
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
