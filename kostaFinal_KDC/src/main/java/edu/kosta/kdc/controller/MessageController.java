package edu.kosta.kdc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @ResponseBody
    public ModelAndView messageAll() {
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String id = member.getMemberId();

        //���ӵ� ID�� �޼��� ����Ʈ�� ������
        List<MessageDTO> list = messageService.messageAll(id);
        
        /*//���� ���� �޼��� count�ϴ� �޼ҵ� ȣ��
        messageUnReadCount(session, member.getMemberId());*/
        
        return new ModelAndView("message/messageList", "messageList", list);
        
    }
    
    /**
     * �������� ��ü �޼��� ����Ʈ
     * */
    @RequestMapping("/unReadMessageList")
    @ResponseBody
    public List<MessageDTO> unReadMessageList(@RequestParam(value="id")String id){

        List<MessageDTO> list = messageService.unReadMessageList(id);
        
        return list;
        
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
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String id = member.getMemberId();

        messageService.messageSelectDelete(deleteNumList);

    }
    
    /**
     * �޼��� �󼼺���(�޼��� Ȯ�� ���� ����)
     * */
    @RequestMapping("{messageNum}")
    public ModelAndView selectByMesssage(@PathVariable int messageNum, HttpSession session) {
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String id = member.getMemberId();
        
        MessageDTO messageDTO = messageService.selectByMesssage(messageNum);
        
        //���� ���� �޼��� count�ϴ� �޼ҵ� ȣ��
        messageUnReadCount(session, member.getMemberId());

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
    
    /**
     * ���� ���� �޼��� ī��Ʈ
     * */
    @RequestMapping("/count")
    @ResponseBody
    public int messageUnReadCount(HttpSession session,  @RequestParam(value="id")String id) {

        int unReadCount = messageService.messageUnReadCount(id);
        
        List<MessageDTO> list =  messageService.messageAll(id);
        
        
        
        //���ǿ� ������ �޼��� ������ ����
        session.setAttribute("unReadCount", unReadCount);
        session.setAttribute("messageList", list);
        
        return unReadCount;
        
    }
    
    

}
