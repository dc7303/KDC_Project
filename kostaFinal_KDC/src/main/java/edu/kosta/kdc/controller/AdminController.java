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
    
    @Autowired PageHandler pageHandler;
    
    /**
     * ������ �α���
     *
     * @return
     */
    @RequestMapping("/admin")
    public String adminLogin() {
        
        return "/admin/main/signInForm";
    }
    
    /**
     * ������ ������
     * 
     * @return
     */
    @RequestMapping("/adminPage")
    public String adminPage() {
        
        return "/admin/main/admin";
    }
    
    /**
     * ��� ����Ʈ �������� (paging)
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/adminMemberList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> adminMemberList(int currentPage) {
        
        int setPage = currentPage;       //���� ������
        int setTotalCount = memberService.memberTotalCount();     //�÷� ��
        
        //view�� ���� json map
        Map<String, Object> map = new HashMap<>();
        
        //������ ���� ���� �� DTO ���� �ޱ�
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //������ ��ȸ�� ROWNUM ���� �� select �μ��� ����.
        int firstColumnRange = pageDTO.getFirstColumnRange();
        int lastColumnRange = pageDTO.getLastColumnRange();
        
        //�������� �ش��ϴ� ���� ����Ʈ ��������
        List<MemberDTO> memberList = memberService.memberSelectAll(firstColumnRange, lastColumnRange);
        
        map.put("pageDTO", pageDTO);
        map.put("memberList", memberList);
        
        return map;
    }
    
    /**
     * �Ű� ����Ʈ ��������
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/adminReportList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> adminReportList(int currentPage) {
        int setPage = currentPage;       //���� ������
        int setTotalCount = reportService.reportSelectQuantity();     //�÷� ��
        
        //view�� ���� json map
        Map<String, Object> map = new HashMap<>();
        
        //������ ���� ���� �� DTO ���� �ޱ�
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //������ ��ȸ�� ROWNUM ���� �� select �μ��� ����.
        int firstColumnRange = pageDTO.getFirstColumnRange();
        int lastColumnRange = pageDTO.getLastColumnRange();
        
        //�Ű� ��ü��������
        List<ReportDTO> reportList = reportService.reportSelectAll(firstColumnRange, lastColumnRange);
        
        map.put("pageDTO", pageDTO);
        map.put("reportList", reportList);
        
        return map;
        
    }
    
    /**
     * �޼��� ����Ʈ ��������
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/adminMessageList")
    @ResponseBody
    public Map<String, Object> adminMessageList(int currentPage) {
        
        int setPage = currentPage;       //���� ������
        int setTotalCount = messageService.messageSelectQuntity();     //�÷� ��
        
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
        
        return map;
        
    }
    
    /**
     * ���Ȳ �Խ��� �� ��������
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
     * �湮�� �� �������� (�ֱ� 5��)
     * */
    @RequestMapping(value = "/visitNumChart", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<VisitDTO> visitNumListSelect() {
        
        //��¥ (��, ��, ��, ��, ��, ��) ����
        String str="";
        
        //��¥ (��, ��, �� �� �ִ�) ����. view�� �Ѹ� �� ��, ��, �� ���ܽ�Ű���� ����.
        String subStr="";
        
        //�湮�� �� ��������
        List<VisitDTO> visitList = adminServcie.visitNumListSelect();

        //date String�� �� �� �� ���� �����Ƿ�, ��-�� �� ������ subString�ؼ� ����.
        for(int i = 0; i < visitList.size(); i++) {
            str = visitList.get(i).getVisitDate();
            subStr = str.substring(5, 10);
            visitList.get(i).setVisitDate(subStr);
        }
        
        return visitList;
        
    }
    
    /**
     * ������ ������ - ���̵�� ���� �˻��ϱ�
     * */
    @RequestMapping("/selectMemberByUserId")
    public ModelAndView MemberSelectByUserId(String userId) {
        
        MemberDTO memberDTO = memberService.memberSelectByMemberId(userId);
        
        //Service���� ����Ÿ���� MemberDTO �� �Ǿ��ִ�. ������ admin/adminPage ������ �ݵ�� List�� ���� �־�� �Ǳ� ������ ����Ʈ�� �־��ִ� �ڵ�.
        List<MemberDTO> memberList = new ArrayList<MemberDTO>();
        if(memberDTO!=null) {
            memberList.add(memberDTO);
        }
        
        return new ModelAndView("admin/adminPage", "memberList", memberList);
        
    }
    
    /**
     * ������ ������ - ���� ����
     * */
    @RequestMapping("{memberId}")
    public String MemberDeleteByUserId(@PathVariable String memberId) {
        
        memberService.updateByIsWithDrawal(memberId);
        
        return "redirect:/admin/selectMember";
        
    }
 
    /**
     * ���� ������
     * */
    @RequestMapping(value = "/sendMessage", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public void SendMessage(MessageDTO messageDTO) {
        messageService.messageInsert(messageDTO);
    }
    
    /**
     * �޽��� �� ����
     * */
    @RequestMapping("/messageForm")
    public ModelAndView MessageForm(String senderId) {
        
        return new ModelAndView("admin/empty/messageForm", "senderId", senderId);
    }
    
    /**
     * �޽��� ����
     * */
    @RequestMapping(value="/deleteMessage")
    public String deleteMessage(int messageNum) {
        
        messageService.messageDelete(messageNum);
        
        return "redirect:/admin/messageList";
    }
    
    /**
     * ���� ���� �� ����
     * */
    @RequestMapping("/adminInsertTeacherForm")
    public ModelAndView InsertTeacherForm() {
        
        return new ModelAndView("member/signUpForm", "authTeacher", "ROLE_TEACHER");
        
    }
    
    /**
     * ������ - ���� ���̵� üũ (ajax)
     * */
    @RequestMapping(value = "/teacherCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String teacherCheck(String teacherId) {
        
        return classRoomService.teacherCheck(teacherId);
        
    }
    
    /**
     * ������ - �Ű� �������� �̵�
     * */
    @RequestMapping("/adminReportList")
    public String reportPage() {
       
        
        return "/admin/adminReportPage";
        
    }
    
    
    /**
     * ������ajax - �Ű� �������� �̵� (�Խ��� �ѹ��� �ش��ϴ� �Ű� ����)
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
     * ������ajax - �Ű� ���������� �Ű� ���� ����
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
