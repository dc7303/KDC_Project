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
     * 전체 메세지 리스트(no Paging)
     * */
    @RequestMapping("/messageListNoPaging")
    @Transactional
    public ModelAndView messageLIstAllNoPaging(HttpSession session) {
        
        List<MessageDTO> list = messageService.messageLIstAllNoPaging();
        
        messageUnReadCount(session);
        
        return new ModelAndView("message/messageList", "messageList", list);
    }
    
    /**
     * 전체 메세지 리스트
     * */
    @RequestMapping("/messageList")
    @Transactional
    @ResponseBody
    public ModelAndView messageAll() {
        
        int setPage = 1;       //현재 페이지
        int setTotalCount = messageService.messageUnReadCount();     //컬럼 수
        
        //view로 보낼 json map
        Map<String, Object> map = new HashMap<>();
        
        //페이지 정보 셋팅 및 DTO 리턴 받기
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //데이터 조회할 ROWNUM 범위 를 select 인수로 전달.
        int firstColumnRange = pageDTO.getFirstColumnRange();
        int lastColumnRange = pageDTO.getLastColumnRange();
        
        //쪽지 가져오기
        List<MessageDTO> messageList = messageService.messageAll(firstColumnRange, lastColumnRange);
        
        map.put("pageDTO", pageDTO);
        map.put("messageList", messageList);
        
        return new ModelAndView();
    }
    
    /**
     * 읽지않은 전체 메세지 리스트
     * */
    @RequestMapping("/unReadMessageList")
    @ResponseBody
    public List<MessageDTO> unReadMessageList(@RequestParam(value="id")String id){

        List<MessageDTO> list = messageService.unReadMessageList(id);
        
        return list;
        
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
    @RequestMapping(value = "/adminMessageInsert", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String adminMessageInsert(MessageDTO messageDTO) throws KdcException {
        
        //controller에서 현재 로그인된 사용자의 정보를 가져오는 코드
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //현재 로그인된 아이디를 쪽지 보내는 senderId로 저장
        messageDTO.setReceiverId(member.getMemberId());
        
        messageService.messageInsert(messageDTO);
    
        return "메세지가 정상적으로 전송되었습니다.";
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
    @RequestMapping(value = "/messageSelectByMessageNum", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MessageDTO selectByMesssage(int messageNum) {
        
        return messageService.selectByMesssage(messageNum);

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
        
        if(checkId == null) {
            return "0";
        }else {
            return checkId;
        }
        
    }
   
    /**
     * 읽지 않은 메세지 카운트
     * */
    @RequestMapping("/count")
    @ResponseBody
    @Transactional
    public int messageUnReadCount(HttpSession session) {

        int unReadCount = messageService.messageUnReadCount();
        
        List<MessageDTO> list = messageService.messageLIstAllNoPaging();
        
        //세션에 안읽은 메세지 갯수를 저장
        session.setAttribute("unReadCount", unReadCount);
        session.setAttribute("messageList", list);
        
        return unReadCount;
        
    }

    @RequestMapping(value = "/messageDeleteByAdmin", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String messageDeleteByAdmin(int messageNum) {
        
        messageService.messageDelete(messageNum);
        
        return "삭제되었습니다.";
    }
}
