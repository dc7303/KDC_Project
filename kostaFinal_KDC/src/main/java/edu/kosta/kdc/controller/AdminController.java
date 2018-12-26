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
    private ReportService reportService;
    
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
        
        return new ModelAndView("admin/adminPage", "memberList", memberDTO);
        
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
     * 강사 생성 폼 들어가기
     * */
    @RequestMapping("/adminInsertTeacherForm")
    public String InsertTeacherForm() {
        
        return "admin/adminInsert";
        
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
     * 관리자 - 신고 페이지에서 신고 내역 삭제
     * */
    @RequestMapping("/deleteReport")
    public String deleteReport(int reportNum) {
        
        int result = reportService.deleteReport(reportNum);
        
        return "/admin/adminReportPage";
    }

}
