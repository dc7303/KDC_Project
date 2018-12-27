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
    public String chatting(Model model,HttpSession session) {
        //code �� �����ϴ� �ڵ����� üũ
        /*
         * 1. code�� �ش��ϴ� classroom_infoDTO�� chatfile���� ������(������ ����ó��)
        2. �ش� chatfile�� �ִ� ������ �о�ͼ� ä��â�� �ѷ���
        3. 
        */
        MemberDTO member = null;
        try {
            member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(ClassCastException e) {
            throw new KdcException("������ �����ϴ�.");
        }

        //���� Ŭ������ ��ϵ� Ŭ������ ������ ��ȸ
        ClassRoomInfoDTO info = service.infoSelectByMemberId(member.getMemberId());
        
        //�ڵ忡 �ش��ϴ� ���� �о �ѷ��ֱ�
        String path = session.getServletContext().getRealPath("/resources/chatFile");
        List<String> chatLog =new ArrayList<>();
        try{
            //���� ��ü ����
            File file = new File(path+"/"+info.getClassRoomInfoChatFile());
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
        }catch(Exception E) {
            E.printStackTrace();
            throw new KdcException("ä�� �α׸� �о���µ� �����߽��ϴ�.");
        }
       
        //code�� request�� �����Ͽ� ����
        model.addAttribute("contextPath", path);
        //ä�÷α� ����
        model.addAttribute("chatLog", chatLog);
        model.addAttribute("roomCode", info.getClassRoomCode());
        return "chatting/chattingView";
    }

}
