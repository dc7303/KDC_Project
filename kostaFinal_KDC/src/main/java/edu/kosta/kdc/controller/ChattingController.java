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
        //code �� �����ϴ� �ڵ����� üũ
   
        MemberDTO member = null;
        try {
            member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(ClassCastException e) {
            throw new KdcException("������ �����ϴ�.");
        }

        //���� Ŭ������ ��ϵ� Ŭ������ ������ ��ȸ
        ClassRoomInfoDTO info = service.infoSelectByMemberId(member.getMemberId());
        
        //�ڵ忡 �ش��ϴ� ���� �о �ѷ��ֱ�
        List<String> chatLog =new ArrayList<>();
        try{
            //���� ��ü ����
            File file = new File(info.getClassRoomInfoChatFile());
            //�Է� ��Ʈ�� ����
            FileReader fr = new FileReader(file);
            //�Է� ���� ����
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null){
                chatLog.add(line);
            }
            //.readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.            
            br.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
       
        
        //ä�ð��� ���� request�� ����
        model.addAttribute("chatLog", chatLog);
        model.addAttribute("roomCode", info.getClassRoomCode());
        model.addAttribute("chatFile", info.getClassRoomInfoChatFile());
        return "chatting/chattingView";
    }

}
