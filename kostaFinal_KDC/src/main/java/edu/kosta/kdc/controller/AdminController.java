package edu.kosta.kdc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.ClassRoomService;
import edu.kosta.kdc.model.service.MemberService;
import edu.kosta.kdc.model.service.MessageService;
import edu.kosta.kdc.model.service.ReportService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private ClassRoomService classRoomService;
    
    /**
     * 관리자 페이지 - 전체 유저 리스트 가져오기
     * */
    @RequestMapping("/selectMember")
    public ModelAndView MemberSelectAll() {
                
        List<MemberDTO> list = memberService.memberSelectAll();
        
        return new ModelAndView("admin/adminPage", "memberList", list);
        
    }
    
    /**
     * 관리자 페이지 - 아이디로 유저 검색하기
     * */
    @RequestMapping("/selectMemberByUserId")
    public ModelAndView MemberSelectByUserId(String userId) {
        
        MemberDTO memberDTO = memberService.memberSelectByMemberId(userId);
        
        //Service에서 리턴타입이 MemberDTO 로 되어있다. 하지만 admin/adminPage 에서는 반드시 List로 값을 주어야 되기 때문에 리스트로 넣어주는 코드.
        List<MemberDTO> memberList = new ArrayList<MemberDTO>();
        if(memberDTO!=null) {
            memberList.add(memberDTO);
        }
        
        return new ModelAndView("admin/adminPage", "memberList", memberList);
        
    }
    
    /**
     * 관리자 페이지 - 유저 삭제
     * */
    @RequestMapping("{memberId}")
    public String MemberDeleteByUserId(@PathVariable String memberId) {
        
        memberService.updateByIsWithDrawal(memberId);
        
        return "redirect:/admin/selectMember";
        
    }
 
    /**
     * 관리자 페이지 - 메시지 리스트
     * */
    @RequestMapping("/messageList")
    public ModelAndView MessageSelectAll() {
        
        String memberId="admin";
        //MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        List<MessageDTO> list = messageService.messageAll(memberId);
        
        return new ModelAndView("admin/adminMessagePage", "messageList", list);
        
    }
    
    /**
     * 쪽지 보내기
     * */
    @RequestMapping(value = "/sendMessage", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public void SendMessage(MessageDTO messageDTO) {
        messageService.messageInsert(messageDTO);
    }
    
    /**
     * 메시지 폼 띄우기
     * */
    @RequestMapping("/messageForm")
    public ModelAndView MessageForm(String senderId) {
        
        return new ModelAndView("admin/empty/messageForm", "senderId", senderId);
    }
    
    /**
     * 메시지 삭제
     * */
    @RequestMapping(value="/deleteMessage")
    public String deleteMessage(int messageNum) {
        
        messageService.messageDelete(messageNum);
        
        return "redirect:/admin/messageList";
    }
    
    /**
     * 강사 생성 폼 들어가기
     * */
    @RequestMapping("/adminInsertTeacherForm")
    public ModelAndView InsertTeacherForm() {
        
        return new ModelAndView("member/signUpForm", "authTeacher", "ROLE_TEACHER");
        
    }
    
    /**
     * 관리자 - 클래스 룸 생성 페이지 이동
     * */
    @RequestMapping("/classRoomInfo")
    public String createClassRoomInfo() {
        return "/admin/adminClassRoomInfo";
    }
    
    /**
     * 관리자 - 클래스 룸 생성 + 각 채팅방 파일 생성 (파일이름 : 클래스 코드.txt)
     * */
    @RequestMapping("/insertClassRoom")
    public String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) throws Exception{

        //클래스룸 생성하면 자동적으로 해당 code.txt text 파일 만들기
        File file = new File("C:\\Edu\\final_workspace\\kostaFinal_KDC\\src\\main\\webapp\\resources\\chatFile", classRoomInfoDTO.getClassRoomInfoChatFile());
        file.createNewFile();
        
        int result = classRoomService.createClassRoom(classRoomInfoDTO);
        
        return "redirect:/admin/selectMember";
    }
    
    /**
     * 관리자 - 클래스 코드 이름 중복 체크 (ajax)
     * */
    @RequestMapping(value = "/codeCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String codeCheck(String classRoomCode) {
        
        return classRoomService.codeCheck(classRoomCode);
        
    }
    
    /**
     * 관리자 - 강사 아이디 체크 (ajax)
     * */
    @RequestMapping(value = "/teacherCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String teacherCheck(String teacherId) {
        
        return classRoomService.teacherCheck(teacherId);
        
    }
    
    /**
     * 관리자 - 신고 페이지로 이동
     * */
    @RequestMapping("/adminReportList")
    public String reportPage() {
       
        
        return "/admin/adminReportPage";
        
    }
    
    /**
     * 관리자ajax - 신고 페이지로 이동 (게시판 넘버에 해당하는 신고 띄우기)
     * */
    @RequestMapping("/reportSelectByBoardNum")
    @ResponseBody
    public List<ReportDTO> reportSelectByBoardNum(int boardNum) {
        
        String boardName=null;
        
        switch (boardNum) {
        case 1: boardName = "tech"; break;
        case 2: boardName = "study"; break;
        case 3: boardName = "lib"; break;
        }
        
        List<ReportDTO> reportList = reportService.selectAllReport(boardName);
        return reportList;
        
    }
    
    /**
     * 관리자ajax - 신고 페이지에서 신고 내역 삭제
     * */
    @RequestMapping("/deleteReport")
    @ResponseBody
    public List<ReportDTO> deleteReport(int reportNum, int boardNum) {
        
        String boardName=null;
        
        switch (boardNum) {
        case 1: boardName = "tech"; break;
        case 2: boardName = "study"; break;
        case 3: boardName = "lib"; break;
        }
        
        List<ReportDTO> reportList = reportService.deleteReport(reportNum, boardName);
        
        return reportList;
    }

}
