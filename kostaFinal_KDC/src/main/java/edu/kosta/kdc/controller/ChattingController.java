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
        //code 가 존재하는 코드인지 체크
        /*
         * 1. code에 해당하는 classroom_infoDTO의 chatfile명을 가져옴(없으면 예외처리)
        2. 해당 chatfile에 있는 내용을 읽어와서 채팅창에 뿌려줌
        3. 
        */
        /*MemberDTO member = null;
        try {
            member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(ClassCastException e) {
            throw new KdcException("권한이 없습니다.");
        }*/

        //현재 클래스로 등록된 클래스의 정보를 조회
        /*ClassRoomInfoDTO info = service.infoSelectByMemberId(member.getMemberId());*/
        
        
        //code를 request에 저장하여 전달
        model.addAttribute("roomCode", "123");
        return "chatting/chattingView";
    }

}
