package edu.kosta.kdc.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    
    /**
     * 전체 메세지 리스트
     * */
    @RequestMapping("/list")
    public ModelAndView messageAll(HttpSession session) {
        
        String id = (String) session.getAttribute("userId");
        
        List<MessageDTO> list = messageService.messageAll(id);
        
        return new ModelAndView("message/list", "messageList", list);
        
    }
    
    /**
     * 메세지 전송(=메세지 답장)
     * */
    @RequestMapping("/insert")
    public String messageInsert(MessageDTO messageDTO) {

        messageService.messageInsert(messageDTO);
        
        return "redirect:/message/list";
        
    }
    
    /**
     * 메세지 삭제
     * */
    @RequestMapping("/delete")
    public String messageDelete(HttpSession session, int messageNum) {
        
        messageService.messageDelete(messageNum);
        
        return "redirect:/message/list";
        
    }
    
    /**
     * 메세지 상세보기(메세지 확인 유무 포함)
     * */
    @RequestMapping("{messageNum}")
    public ModelAndView selectByMesssage(@PathVariable int messageNum) {
        
        MessageDTO messageDTO = messageService.selectByMesssage(messageNum);
        
        return new ModelAndView("message/detail", "messageDTO", messageDTO);
        
    }
    
    /**
     * 답장ID(serderId) 체크
     *  : 답장버튼을 클릭하면 senderId가 유효한지 체크
     * */
    @RequestMapping("/checkId")
    @ResponseBody
    public String checkById(String senderId) {
        
        //존재하지 않는 아이디 테스트
        //senderId = "asdf";
        
        String checkId = messageService.checkById(senderId);
        
        if(checkId==null) {
            return "0";
        }else {
            return checkId;
        }
        
    }
    
    @RequestMapping("/replyMessage")
    public ModelAndView replyMessage(String senderId) {
        
        return new ModelAndView("message/replyMessage", "senderId", senderId);
    }

}
