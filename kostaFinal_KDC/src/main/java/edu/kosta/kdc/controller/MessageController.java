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
     * ��ü �޼��� ����Ʈ
     * */
    @RequestMapping("/messageList")
    @Transactional
    public ModelAndView messageAll(HttpSession session, HttpServletRequest request) {
        
        //���ӵ� ID�� �޼��� ����Ʈ�� ������
        List<MessageDTO> list = messageService.messageAll();
        
        
        return new ModelAndView("message/messageList", "messageList", list);
        
    }
    
    /**
     * �޼��� ����(=�޼��� ����)
     * */
    @RequestMapping("/insert")
    public String messageInsert(MessageDTO messageDTO) throws KdcException {
        
        //controller���� ���� �α��ε� ������� ������ �������� �ڵ�
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //���ӵ� ID�� ���� �����ͼ� ����ϹǷ� ���Ƿ� �ּҰ��� �����ϴ��� �ٸ� ������� �޼����� ���ٺҰ�
        String id = member.getMemberId();

        //���ӵ� member�κ��� id��������
        messageDTO.setReceiverId(id);

        messageService.messageInsert(messageDTO);
        
        /*���ӵ�ID�� ��ü �޼��� ����Ʈ�� ����ϱ� ���� return*/ 
        return "redirect:/message/messageList?id="+id;
        
    }
    /**
     * �޽��� ���� (������ Ver) - Ajax�� ���� 
     * */
    @RequestMapping("/adminMessageInsert")
    @ResponseBody
    public void adminMessageInsert(MessageDTO messageDTO) throws KdcException {
        
        //controller���� ���� �α��ε� ������� ������ �������� �ڵ�
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //���� �α��ε� ���̵� ���� ������ senderId�� ����
        messageDTO.setReceiverId(member.getMemberId());
        
        messageService.messageInsert(messageDTO);
    }

    
    /**
     * �޼��� ����
     * */
    @RequestMapping("/delete")
    public String messageDelete(HttpSession session, int messageNum) {
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String id = member.getMemberId();
        
        messageService.messageDelete(messageNum);
        
        return "redirect:/message/messageList?id="+id;
        
    }
    
    /**
     * ���õ� �޼��� ����
     * */
    @RequestMapping("/messageSelectDelete")
    @ResponseBody
    public void messageSelectDelete(@RequestParam(value = "deleteNumList[]")List<Integer> deleteNumList){

        messageService.messageSelectDelete(deleteNumList);
        
    }
    
    /**
     * �޼��� �󼼺���(�޼��� Ȯ�� ���� ����)
     * */
    @RequestMapping("{messageNum}")
    public ModelAndView selectByMesssage(@PathVariable int messageNum) {
        
        MessageDTO messageDTO = messageService.selectByMesssage(messageNum);

        return new ModelAndView("message/messageDetail", "messageDTO", messageDTO);
        
    }
    
    /**
     * replyMessage.jsp ������ �̵�
     * */
    @RequestMapping("/messageReplyPage")
    public ModelAndView messageReplyPage(MessageDTO messageDTO) {
        
        return new ModelAndView("message/replyMessage", "replyMessage", messageDTO);
        
    }
    
    /**
     * ����ID(serderId) üũ
     *  : �����ư�� Ŭ���ϸ� senderId�� ��ȿ���� üũ
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
