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
	
	/*클라이언트의 정보를 가져와 연결확인작업*/
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		//클라이언트의 세션을 세션리스트에 add()로 추가
		
		System.out.println(session.getId()+"님이 연결됨");
		System.out.println("채팅방 입장자 : "+session.getId());
	}
	
	/*연결된 모든 사용자에게 for문으로 메시지를 전달*/
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//메시지에서 추출할 정보들 초기화
	    String [] arr;
		String code = null;
		String memberId = null;
		String pureMessage = null;
		String chatFile = null;
		
		//os별 개행문자 정의
		String newLine = System.getProperty("line.separator");
		
		//jsp에서 정한 구분자로 구분하여 배열생성
		arr = message.getPayload().split("\\|");
		code = arr[0];
		memberId = arr[1];
		chatFile = arr[2];
		pureMessage = arr[3];
		
		//파일에 작성
		BufferedWriter bw = new BufferedWriter(new FileWriter(chatFile,true));
		PrintWriter pw = new PrintWriter(bw,true);
		pw.write(memberId+"|"+pureMessage+newLine);
		pw.flush();
		pw.close();
		
		
		
		//code에 대한 세션리스트가 없는경우 socketMap에 추가
		if(socketMap.get(code)==null) {
		    System.out.println("맵추가!");
			socketMap.put(code, new ArrayList<WebSocketSession>());
		}
		sessionList = socketMap.get(code);
		if(!sessionList.contains(session)) {
		    System.out.println("세션추가!");
			sessionList.add(session);
		}
		
		for(WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(memberId+"|"+pureMessage));
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
