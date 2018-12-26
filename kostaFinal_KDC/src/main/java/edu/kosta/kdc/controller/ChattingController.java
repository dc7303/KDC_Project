package edu.kosta.kdc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.ChattingService;
import edu.kosta.kdc.model.service.PortfolioService;

@Controller
public class ChattingController {
    
    @Autowired
    private  ChattingService service;
    
    @RequestMapping("/chatting")
    public String chatting(Model model) {
        //code �� �����ϴ� �ڵ����� üũ
        /*
         * 1. code�� �ش��ϴ� classroom_infoDTO�� chatfile���� ������(������ ����ó��)
        2. �ش� chatfile�� �ִ� ������ �о�ͼ� ä��â�� �ѷ���
        3. 
        */
        /*MemberDTO member = null;
        try {
            member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(ClassCastException e) {
            throw new KdcException("������ �����ϴ�.");
        }*/

        //���� Ŭ������ ��ϵ� Ŭ������ ������ ��ȸ
        /*ClassRoomInfoDTO info = service.infoSelectByMemberId(member.getMemberId());*/
        
        
        //code�� request�� �����Ͽ� ����
        model.addAttribute("roomCode", "123");
        return "chatting/chattingView";
    }

}
