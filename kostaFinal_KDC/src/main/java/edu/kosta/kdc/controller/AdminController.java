package edu.kosta.kdc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.dto.PageDTO;
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.AdminService;
import edu.kosta.kdc.model.service.ClassRoomService;
import edu.kosta.kdc.model.service.MemberService;
import edu.kosta.kdc.model.service.MessageService;
import edu.kosta.kdc.model.service.ReportService;
import edu.kosta.kdc.util.interfaces.PageHandler;

@Controller
public class AdminController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private ClassRoomService classRoomService;
    
    @Autowired
    private AdminService adminServcie;
    
    @Autowired PageHandler pageHandler;
    
    //관리자 로그인
    @RequestMapping("/admin")
    public String adminLogin() {
        
        return "/admin/main/signInForm";
    }
    
    @RequestMapping(value = "/adminMemberList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> adminMemberList(@RequestParam(value = "currentPage") String currentPage) {
        /*
        int page = Integer.parseInt(currentPage);       //현재 페이지
        int countList = 5;      //한페이지에 출력될 게시물 수
        int countPage = 10;      //한 화면에 출력될 페이지 수
        
        int totalCount = memberService.memberTotalCount();     //컬럼 수
        
        int totalPage = totalCount / countList;     //전체 페이지
        
        boolean firstMove = false;      //첫 페이지로 이동하는 at 생성 여부
        boolean backPage = false;       //이전 페이지로
        boolean nextPage = false;       //다음 페이지로
        boolean lastMove = false;       //마지막 페이지로
        
        //컬럼이 255개일 경우 출력될 게시물 수 와 % 연산자 사용시 5개의 남는 컬럼이 발생함.
        //이를 해결하기위해 % 연산하여 0보다 클 경우 페이지 수를 +1 해준다.
        if(totalCount % countList > 0) {
            totalPage++;
        }
        
        //현재 페이지가 총 페이지보다 크다면 현재 페이지를 마지막 최종페이지 값으로.
        if(totalPage < page) {
            page = totalPage;
        }
        
        //페이지 시작 
        int startPage = ((page - 1) / 10) * 10 + 1;
        
        //마지막 페이지
        int endPage = startPage + countPage -1;
        
        //마지막 페이지가 totalPage보다 크면 안되기 때문에 end페이지가 클 경우 맞춘다.
        if(endPage > totalPage) {
            endPage = totalPage;
        }
        
        //startPage가 1이 아닐 경우 처음으로 이동하는 at을 추가할 수 있게 해준다.
        if(startPage > 1 || page > 1) {
            firstMove = true;
        }
        
        //페이지가 1이 아닌 경우 이전페이지로 이동해주는 at을 추가하게 해준다.
        if(page > 1) {
            backPage = true;
        }

        //<다음> 셋팅
        if(page < totalPage) {
            nextPage = true;
        }
        
        //마지막 페이지 셋팅
        if(endPage < totalPage) {
            lastMove = true;
        }
        */
        int setPage = Integer.parseInt(currentPage);       //현재 페이지
        int setTotalCount = memberService.memberTotalCount();     //컬럼 수
        
        //view로 보낼 json map
        Map<String, Object> map = new HashMap<>();
        
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //전체 유저 리스트 가져오기
        List<MemberDTO> memberList = memberService.memberSelectAll();
        
        map.put("pageDTO", pageDTO);
        map.put("memberList", memberList);
        
        return map;
    }
    
    
    //관리자 페이지
    @RequestMapping("/adminPage")
    public ModelAndView adminPage() {
        /*
        //전체 유저 리스트 가져오기
        List<MemberDTO> memberList = memberService.memberSelectAll();
        */
        //신고 전체가져오기
        List<ReportDTO> reportList = reportService.reportSelectAll();
        
        //쪽지 가져오기
        List<MessageDTO> messageList = messageService.messageAll();
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("reportList", reportList);
        mv.addObject("messageList", messageList);
        
        mv.setViewName("/admin/main/admin");
        
        return mv;
    }
    
    
    /**
     * 관리자 페이지 - 전체 유저 리스트 가져오기
     * */
    @RequestMapping("/selectMember")
    public ModelAndView MemberSelectAll() {
                
        List<MemberDTO> list = memberService.memberSelectAll();
        
        return new ModelAndView("admin/adminPage", "memberList", list);    
    }
    
    /**
     * 운영현황 게시판 수 가져오기
     * 
     * @return
     */
    @RequestMapping(value = "/boardTotalChart", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Integer> boardTotalChart() {
        
        Map<String, Integer> map = adminServcie.boardQuantityByClassification();
        
        return map;
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
        
        List<MessageDTO> list = messageService.messageAll();
        
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
    @RequestMapping(value = "/reportSelectByBoardNum", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public List<ReportDTO> reportSelectByBoardNum(int boardNum) {
        
        String boardName=null;
        
        switch (boardNum) {
        case 1: boardName = "tech"; break;
        case 2: boardName = "study"; break;
        case 3: boardName = "lib"; break;
        }
        
        List<ReportDTO> reportList = reportService.reportSelectByBoardName(boardName);
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
