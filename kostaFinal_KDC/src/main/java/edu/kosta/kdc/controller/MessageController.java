package edu.kosta.kdc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.service.MessageService;
import edu.kosta.kdc.util.KdcException;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    
    /**
     * 전체 메세지 리스트
     * */
    @RequestMapping("/messageList")
    @Transactional
    public ModelAndView messageAll(HttpSession session, HttpServletRequest request) {
        
        //접속된 ID로 메세지 리스트를 가져옴
        List<MessageDTO> list = messageService.messageAll();
        
        
        return new ModelAndView("message/messageList", "messageList", list);
        
    }
    
    /**
     * 메세지 전송(=메세지 답장)
     * */
    @RequestMapping("/insert")
    public String messageInsert(MessageDTO messageDTO) throws KdcException {
        
        //controller에서 현재 로그인된 사용자의 정보를 가져오는 코드
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //접속된 ID를 직접 가져와서 출력하므로 임의로 주소값을 변경하더라도 다른 사용자의 메세지에 접근불가
        String id = member.getMemberId();

        //접속된 member로부터 id가져오기
        messageDTO.setReceiverId(id);

        messageService.messageInsert(messageDTO);
        
        /*접속된ID로 전체 메세지 리스트를 출력하기 위한 return*/ 
        return "redirect:/message/messageList?id="+id;
        
    }
    /**
     * 메시지 전송 (관리자 Ver) - Ajax로 연동 
     * */
    @RequestMapping("/adminMessageInsert")
    @ResponseBody
    public void adminMessageInsert(MessageDTO messageDTO) throws KdcException {
        
        //controller에서 현재 로그인된 사용자의 정보를 가져오는 코드
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //현재 로그인된 아이디를 쪽지 보내는 senderId로 저장
        messageDTO.setReceiverId(member.getMemberId());
        
        messageService.messageInsert(messageDTO);
    }

    
    /**
     * 메세지 삭제
     * */
    @RequestMapping("/delete")
    public String messageDelete(HttpSession session, int messageNum) {
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String id = member.getMemberId();
        
        messageService.messageDelete(messageNum);
        
        return "redirect:/message/messageList?id="+id;
        
    }
    
    /**
     * 선택된 메세지 삭제
     * */
    @RequestMapping("/messageSelectDelete")
    @ResponseBody
    public void messageSelectDelete(@RequestParam(value = "deleteNumList[]")List<Integer> deleteNumList){

        messageService.messageSelectDelete(deleteNumList);
        
    }
    
    /**
     * 메세지 상세보기(메세지 확인 유무 포함)
     * */
    @RequestMapping("{messageNum}")
    public ModelAndView selectByMesssage(@PathVariable int messageNum) {
        
        MessageDTO messageDTO = messageService.selectByMesssage(messageNum);

        return new ModelAndView("message/messageDetail", "messageDTO", messageDTO);
        
    }
    
    /**
     * replyMessage.jsp 페이지 이동
     * */
    @RequestMapping("/messageReplyPage")
    public ModelAndView messageReplyPage(MessageDTO messageDTO) {
        
        return new ModelAndView("message/replyMessage", "replyMessage", messageDTO);
        
    }
    
    /**
     * 답장ID(serderId) 체크
     *  : 답장버튼을 클릭하면 senderId가 유효한지 체크
     * */
    @RequestMapping("/checkId")
    @ResponseBody
    public String messageCheckById(String senderId) {

        String checkId = messageService.messageCheckById(senderId);
        
        if(checkId==null) {
            return "0";
        }else {
            return checkId;
        }
        
    }

}
