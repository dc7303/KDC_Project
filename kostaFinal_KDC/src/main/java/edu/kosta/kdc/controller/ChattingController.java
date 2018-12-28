package edu.kosta.kdc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.ChattingService;

@Controller
public class ChattingController {
    
    @Autowired
    private  ChattingService service;
    
    @RequestMapping("/chatting")
    public String chatting(Model model) {
        //code 가 존재하는 코드인지 체크
   
        MemberDTO member = null;
        try {
            member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(ClassCastException e) {
            throw new KdcException("권한이 없습니다.");
        }

        //현재 클래스로 등록된 클래스의 정보를 조회
        ClassRoomInfoDTO info = service.infoSelectByMemberId(member.getMemberId());
        
        //코드에 해당하는 파일 읽어서 뿌려주기
        List<String> chatLog =new ArrayList<>();
        try{
            //파일 객체 생성
            File file = new File(info.getClassRoomInfoChatFile());
            //입력 스트림 생성
            FileReader fr = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null){
                chatLog.add(line);
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.            
            br.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
       
        
        //채팅관련 정보 request로 전달
        model.addAttribute("chatLog", chatLog);
        model.addAttribute("roomCode", info.getClassRoomCode());
        model.addAttribute("chatFile", info.getClassRoomInfoChatFile());
        return "chatting/chattingView";
    }

}
