package edu.kosta.kdc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.MessageDTO;
import edu.kosta.kdc.model.dto.PageDTO;
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.dto.VisitDTO;
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
    
    @Autowired 
    private PageHandler pageHandler;
    
    /**
     * 관리자 로그인
     *
     * @return
     */
    @RequestMapping("/admin")
    public String adminLogin() {
        
        return "/admin/main/signInForm";
    }
    
    /**
     * 관리자 페이지
     * 
     * @return
     */
    @RequestMapping("/adminPage")
    public String adminPage() {
        
        return "/admin/main/admin";
    }
    
    /**
     * 멤버 리스트 가져오기 (paging)
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/adminMemberList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> adminMemberList(int currentPage) {
        
        int setPage = currentPage;       //현재 페이지
        int setTotalCount = memberService.memberTotalCount();     //컬럼 수
        
        //view로 보낼 json map
        Map<String, Object> map = new HashMap<>();
        
        //페이지 정보 셋팅 및 DTO 리턴 받기
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //데이터 조회할 ROWNUM 범위 를 select 인수로 전달.
        int firstColumnRange = pageDTO.getFirstColumnRange();
        int lastColumnRange = pageDTO.getLastColumnRange();
        
        //페이지에 해당하는 유저 리스트 가져오기
        List<MemberDTO> memberList = memberService.memberSelectAll(firstColumnRange, lastColumnRange);
        
        map.put("pageDTO", pageDTO);
        map.put("memberList", memberList);
        map.put("keyword", null);
        map.put("word", null);
        
        return map;
    }
    
    /**
     * 신고 리스트 가져오기
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/adminReportList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> adminReportList(int currentPage) {
        int setPage = currentPage;       //현재 페이지
        int setTotalCount = reportService.reportSelectQuantity();     //컬럼 수
        
        //view로 보낼 json map
        Map<String, Object> map = new HashMap<>();
        
        //페이지 정보 셋팅 및 DTO 리턴 받기
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //데이터 조회할 ROWNUM 범위 를 select 인수로 전달.
        int firstColumnRange = pageDTO.getFirstColumnRange();
        int lastColumnRange = pageDTO.getLastColumnRange();
        
        //신고 전체가져오기
        List<ReportDTO> reportList = reportService.reportSelectAll(firstColumnRange, lastColumnRange);
        
        map.put("pageDTO", pageDTO);
        map.put("reportList", reportList);
        
        return map;
        
    }
    
    /**
     * 메세지 리스트 가져오기
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/adminMessageList")
    @ResponseBody
    public Map<String, Object> adminMessageList(int currentPage) {
        
        int setPage = currentPage;       //현재 페이지
        int setTotalCount = messageService.messageSelectQuntity();     //컬럼 수
        
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
        
        return map;
        
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
     * 방문자 수 가져오기 (최근 5일)
     * */
    @RequestMapping(value = "/visitNumChart", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<VisitDTO> visitNumListSelect() {
        
        //날짜 (연, 월, 일, 시, 분, 초) 변수
        String str="";
        
        //날짜 (연, 월, 일 만 있는) 변수. view에 뿌릴 때 시, 분, 초 제외시키려고 만듦.
        String subStr="";
        
        //방문자 수 가져오기
        List<VisitDTO> visitList = adminServcie.visitNumListSelect();

        //date String이 시 분 초 까지 나오므로, 월-일 만 나오게 subString해서 저장.
        for(int i = 0; i < visitList.size(); i++) {
            str = visitList.get(i).getVisitDate();
            subStr = str.substring(5, 10);
            visitList.get(i).setVisitDate(subStr);
        }
        
        return visitList;
        
    }
    
    /**
     * 관리자 페이지 - 키워드로 가져오기
     * */
    @RequestMapping(value = "/selectMemberByKeyword", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> MemberSelectByUserId(String keyword, String word, int currentPage) {
        
        int setPage = currentPage;       //현재 페이지
        int setTotalCount = memberService.memberSelectByKewordQuntity(keyword, word);     //컬럼 수
        
        //view로 보낼 json map
        Map<String, Object> map = new HashMap<>();
        
        //페이지 정보 셋팅 및 DTO 리턴 받기
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //데이터 조회할 ROWNUM 범위 를 select 인수로 전달.
        int firstColumnRange = pageDTO.getFirstColumnRange();
        int lastColumnRange = pageDTO.getLastColumnRange();
        
        
        List<MemberDTO> memberList = memberService.memberSelectByKeyword(keyword, word, firstColumnRange, lastColumnRange);
        
        map.put("pageDTO", pageDTO);
        map.put("memberList", memberList);
        map.put("keyword", keyword);
        map.put("word", word);
                
        return map;
        
    }
 
    /**
     * 쪽지 보내기
     * */
    @RequestMapping(value = "/sendMessage", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String SendMessage(MessageDTO messageDTO) {
        
        messageService.messageInsert(messageDTO);
        
        return "메세지가 전송되었습니다.";
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
     * 관리자 - 강사 아이디 체크 (ajax)
     * */
    @RequestMapping(value = "/teacherCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String teacherCheck(String teacherId) {
        
        return classRoomService.teacherCheck(teacherId);
        
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
    @RequestMapping(value = "/deleteReport", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String deleteReport(int reportNum) {
        
        reportService.deleteReport(reportNum);
        
        return "처리완료되었습니다.";
    }
    


}
