package edu.kosta.kdc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;

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
    private ClassRoomService classRoomService;
    
    @Autowired
    private ReportService reportService;
    
    /**
     * ������ ������ - ��ü ���� ����Ʈ ��������
     * */
    @RequestMapping("/selectMember")
    public ModelAndView MemberSelectAll() {
                
        List<MemberDTO> list = memberService.memberSelectAll();
        
        return new ModelAndView("admin/adminPage", "memberList", list);
        
    }
    
    /**
     * ������ ������ - ���̵�� ���� �˻��ϱ�
     * */
    @RequestMapping("/selectMemberByUserId")
    public ModelAndView MemberSelectByUserId(String userId) {
        
        MemberDTO memberDTO = memberService.memberSelectByMemberId(userId);
        
        return new ModelAndView("admin/adminPage", "memberList", memberDTO);
        
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
    public ModelAndView MessageSelectAll(String memberId) {
        
        List<MessageDTO> list = messageService.messageAll(memberId);
        
        return new ModelAndView("admin/adminMessagePage", "messageList", list);
        
    }
    
    
    /**
     * ���� ������
     * */
    @RequestMapping(value = "/sendMessage", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public void SendMessage(MessageDTO messageDTO) {
        
        int result = messageService.messageInsert(messageDTO);
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
    public String InsertTeacherForm() {
        
        return "admin/adminInsert";
        
    }
    
    
    /**
     * ������ - Ŭ���� �� ���� ������ �̵�
     * */
    @RequestMapping("/classRoomInfo")
    public String createClassRoomInfo() {
        
        return "/admin/adminClassRoomInfo";
    }
    
    /**
     * ������ - Ŭ���� �� ���� + �� ä�ù� ���� ���� (�����̸� : Ŭ���� �ڵ�.txt)
     * */
    @RequestMapping("/insertClassRoom")
    public String createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) throws Exception{

        File file = new File("C:\\Edu\\final_workspace\\kostaFinal_KDC\\src\\main\\webapp\\resources\\chatFile", classRoomInfoDTO.getClassRoomInfoChatFile());
        file.createNewFile();
        
        classRoomService.createClassRoom(classRoomInfoDTO);
        
        return "redirect:/admin/selectMember";
    }
    
    /**
     * ������ - Ŭ���� �ڵ� �̸� �ߺ� üũ (ajax)
     * */
    @RequestMapping(value = "/codeCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String codeCheck(String classRoomCode) {
        
        return classRoomService.codeCheck(classRoomCode);
        
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
    @RequestMapping("/reportSelectByBoardNum")
    @ResponseBody
    public List<ReportDTO> reportSelectByBoardNum(int boardNum, HttpServletRequest request) {
        
        String boardName=null;
        
        switch (boardNum) {
        case 1: boardName = "tech"; break;
        case 2: boardName = "study"; break;
        case 3: boardName = "lib"; break;
        }
        
        List<ReportDTO> reportList = reportService.selectAll(boardName);
        
        request.setAttribute("reportList", reportList);
        
        return reportList;
        
    }
    
    /**
     * ������ - �Ű� ���������� �Ű� ���� ����
     * */
    @RequestMapping("/deleteReport")
    public String deleteReport(int reportNum) {
        
        int result = reportService.deleteReport(reportNum);
        
        return "/admin/adminReportPage";
    }
    
    /**
     * Ǯ ī���ٷ� ���� �޼ҵ�
     * */
    @RequestMapping("/fullCalendar")
    public ModelAndView fullCalendar() {
        
        List<ClassRoomInfoDTO> list = classRoomService.getClassInfo();
        
        return new ModelAndView("/admin/fullCalendars", "classList", list);
    }
}
