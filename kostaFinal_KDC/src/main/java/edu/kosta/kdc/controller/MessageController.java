package edu.kosta.kdc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.dto.PageDTO;
import edu.kosta.kdc.model.service.MessageService;
import edu.kosta.kdc.util.KdcException;
import edu.kosta.kdc.util.interfaces.PageHandler;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    
    @Autowired 
    private PageHandler pageHandler;
    
    /**
     * ��ü �޼��� ����Ʈ(no Paging)
     * */
    @RequestMapping("/messageListNoPaging")
    @Transactional
    public ModelAndView messageLIstAllNoPaging(HttpSession session) {
        
        List<MessageDTO> list = messageService.messageLIstAllNoPaging();
        
        messageUnReadCount(session);
        
        return new ModelAndView("message/messageList", "messageList", list);
    }
    
    /**
     * ��ü �޼��� ����Ʈ
     * */
    @RequestMapping("/messageList")
    @Transactional
    @ResponseBody
    public ModelAndView messageAll() {
        
        int setPage = 1;       //���� ������
        int setTotalCount = messageService.messageUnReadCount();     //�÷� ��
        
        //view�� ���� json map
        Map<String, Object> map = new HashMap<>();
        
        //������ ���� ���� �� DTO ���� �ޱ�
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //������ ��ȸ�� ROWNUM ���� �� select �μ��� ����.
        int firstColumnRange = pageDTO.getFirstColumnRange();
        int lastColumnRange = pageDTO.getLastColumnRange();
        
        //���� ��������
        List<MessageDTO> messageList = messageService.messageAll(firstColumnRange, lastColumnRange);
        
        map.put("pageDTO", pageDTO);
        map.put("messageList", messageList);
        
        return new ModelAndView();
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
     * �޽��� ���� (������ Ver) - Ajax�� ���� 
     * */
    @RequestMapping(value = "/adminMessageInsert", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String adminMessageInsert(MessageDTO messageDTO) throws KdcException {
        
        //controller���� ���� �α��ε� ������� ������ �������� �ڵ�
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //���� �α��ε� ���̵� ���� ������ senderId�� ����
        messageDTO.setReceiverId(member.getMemberId());
        
        messageService.messageInsert(messageDTO);
    
        return "�޼����� ���������� ���۵Ǿ����ϴ�.";
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
    @RequestMapping(value = "/messageSelectByMessageNum", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MessageDTO selectByMesssage(int messageNum) {
        
        return messageService.selectByMesssage(messageNum);

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
        
        if(checkId == null) {
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
    @Transactional
    public int messageUnReadCount(HttpSession session) {

        int unReadCount = messageService.messageUnReadCount();
        
        List<MessageDTO> list = messageService.messageLIstAllNoPaging();
        
        //���ǿ� ������ �޼��� ������ ����
        session.setAttribute("unReadCount", unReadCount);
        session.setAttribute("messageList", list);
        
        return unReadCount;
        
    }

    @RequestMapping(value = "/messageDeleteByAdmin", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String messageDeleteByAdmin(int messageNum) {
        
        messageService.messageDelete(messageNum);
        
        return "�����Ǿ����ϴ�.";
    }
}
