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
    
    //������ �α���
    @RequestMapping("/admin")
    public String adminLogin() {
        
        return "/admin/main/signInForm";
    }
    
    @RequestMapping(value = "/adminMemberList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> adminMemberList(@RequestParam(value = "currentPage") String currentPage) {
        /*
        int page = Integer.parseInt(currentPage);       //���� ������
        int countList = 5;      //���������� ��µ� �Խù� ��
        int countPage = 10;      //�� ȭ�鿡 ��µ� ������ ��
        
        int totalCount = memberService.memberTotalCount();     //�÷� ��
        
        int totalPage = totalCount / countList;     //��ü ������
        
        boolean firstMove = false;      //ù �������� �̵��ϴ� at ���� ����
        boolean backPage = false;       //���� ��������
        boolean nextPage = false;       //���� ��������
        boolean lastMove = false;       //������ ��������
        
        //�÷��� 255���� ��� ��µ� �Խù� �� �� % ������ ���� 5���� ���� �÷��� �߻���.
        //�̸� �ذ��ϱ����� % �����Ͽ� 0���� Ŭ ��� ������ ���� +1 ���ش�.
        if(totalCount % countList > 0) {
            totalPage++;
        }
        
        //���� �������� �� ���������� ũ�ٸ� ���� �������� ������ ���������� ������.
        if(totalPage < page) {
            page = totalPage;
        }
        
        //������ ���� 
        int startPage = ((page - 1) / 10) * 10 + 1;
        
        //������ ������
        int endPage = startPage + countPage -1;
        
        //������ �������� totalPage���� ũ�� �ȵǱ� ������ end�������� Ŭ ��� �����.
        if(endPage > totalPage) {
            endPage = totalPage;
        }
        
        //startPage�� 1�� �ƴ� ��� ó������ �̵��ϴ� at�� �߰��� �� �ְ� ���ش�.
        if(startPage > 1 || page > 1) {
            firstMove = true;
        }
        
        //�������� 1�� �ƴ� ��� ������������ �̵����ִ� at�� �߰��ϰ� ���ش�.
        if(page > 1) {
            backPage = true;
        }

        //<����> ����
        if(page < totalPage) {
            nextPage = true;
        }
        
        //������ ������ ����
        if(endPage < totalPage) {
            lastMove = true;
        }
        */
        int setPage = Integer.parseInt(currentPage);       //���� ������
        int setTotalCount = memberService.memberTotalCount();     //�÷� ��
        
        //view�� ���� json map
        Map<String, Object> map = new HashMap<>();
        
        PageDTO pageDTO = pageHandler.pageInfoSet(setPage, 10, 10, setTotalCount);
        
        //��ü ���� ����Ʈ ��������
        List<MemberDTO> memberList = memberService.memberSelectAll();
        
        map.put("pageDTO", pageDTO);
        map.put("memberList", memberList);
        
        return map;
    }
    
    
    //������ ������
    @RequestMapping("/adminPage")
    public ModelAndView adminPage() {
        /*
        //��ü ���� ����Ʈ ��������
        List<MemberDTO> memberList = memberService.memberSelectAll();
        */
        //�Ű� ��ü��������
        List<ReportDTO> reportList = reportService.reportSelectAll();
        
        //���� ��������
        List<MessageDTO> messageList = messageService.messageAll();
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("reportList", reportList);
        mv.addObject("messageList", messageList);
        
        mv.setViewName("/admin/main/admin");
        
        return mv;
    }
    
    
    /**
     * ������ ������ - ��ü ���� ����Ʈ ��������
     * */
    @RequestMapping("/selectMember")
    public ModelAndView MemberSelectAll() {
                
        List<MemberDTO> list = memberService.memberSelectAll();
        
        return new ModelAndView("admin/adminPage", "memberList", list);    
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
     * ������ ������ - �޽��� ����Ʈ
     * */
    @RequestMapping("/messageList")
    public ModelAndView MessageSelectAll() {
        
        List<MessageDTO> list = messageService.messageAll();
        
        return new ModelAndView("admin/adminMessagePage", "messageList", list);
        
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
