package edu.kosta.kdc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.ReplyBoardService;

@Controller
@RequestMapping("/reply")
public class ReplyBoardController {

    @Autowired
    private ReplyBoardService replyBoardService;
    
    /**
     * 전체 리스트 보기
     * */
    @RequestMapping(value= {"/tech","/lib","/study"})
    public String list(@RequestParam(value="classification") String classification, Model model) {
        model.addAttribute("classification",classification);
        List<ReplyBoardDTO> list = replyBoardService.selectAll(classification);
        model.addAttribute("list",list);
        
        return "replyBoard/replyBoardList";
    }
    
    /**
     * 등록 폼
     * */
    @RequestMapping("/write")
    public String writePage(@RequestParam(value="classification") String classification, Model model) {
        model.addAttribute("classification",classification);
        return "replyBoard/write";
    }
    
    /**
     * 게시글 등록하기
     * */
    @RequestMapping("/insert")
    public String techBoardInsert(@RequestParam(value="classification") String classification, ReplyBoardDTO replyBoardDTO, String hashTagName) {
        replyBoardDTO.setReplyBoardClassification(classification);
        replyBoardService.insertReply(replyBoardDTO, hashTagName);
        
        return "redirect:tech?classification="+classification;
    }
    
    /**
     * 댓글 등록하기
     * */
    @RequestMapping("/replyInsert")
    public String replyInsert(@RequestParam(value="classification") String classification, @RequestParam(value="replyBoardPk")int replyBoardPk, ReplyBoardDTO replyBoardDTO, Model model) {
        replyBoardDTO.setReplyBoardClassification(classification);
        //replyBoardDTO.setReplyBoardPk(replyBoardPk);
        replyBoardService.replyInsert(replyBoardDTO);
        
        model.addAttribute("classification",classification);
        
        return "redirect:read?replyBoardPk="+replyBoardPk;
    }
    
    
    /**
     * 상세보기
     * */
    @RequestMapping("/read")
    public String read(@RequestParam(value="replyBoardPk")int replyBoardPk, @RequestParam(value="classification") String classification,ReplyBoardDTO replyBoardDTODB, HttpServletRequest request, Model model) {
        boolean state = request.getParameter("state")== null? true : false;
        replyBoardDTODB.setReplyBoardClassification(classification);

        List<ReplyBoardDTO> replyBoardDTO = replyBoardService.selectByReplyBoardPK(replyBoardDTODB, state);

        model.addAttribute("replyBoardDTO",replyBoardDTO);
        model.addAttribute("classification",classification);
        model.addAttribute("replyBoardPk",replyBoardPk);
        return "replyBoard/read";
    }
    
    /**
     * 수정하기 폼
     * */
    @RequestMapping("/updateForm")
    public String updateForm(@RequestParam(value="replyBoardPk")int replyBoardPk, @RequestParam(value="classification") String classification, ReplyBoardDTO replyBoardDTODB, HttpServletRequest request, Model model){
        boolean state = request.getParameter("state")== null? true : false;
        replyBoardDTODB.setReplyBoardClassification(classification);
        
        List<ReplyBoardDTO> replyBoardDTO = replyBoardService.selectByReplyBoardPK(replyBoardDTODB, state);
        
        model.addAttribute("classification",classification);
        model.addAttribute("replyBoardDTO",replyBoardDTO);
        model.addAttribute("replyBoardPk",replyBoardPk);

        return "replyBoard/updateForm";
    }
    
    /**
     * 게시글 수정하기
     * */
    @RequestMapping("/replyBoardUpdate")
    public String replyBoardUpdate(@RequestParam(value="classification") String classification, @RequestParam(value="replyBoardPk")int replyBoardPk,ReplyBoardDTO replyBoardDTO, String hashTagName) {
        replyBoardDTO.setReplyBoardPk(replyBoardPk);
        replyBoardService.replyBoardUpdate(replyBoardDTO, hashTagName);
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk;
    }
    
    /**
     * 게시글 삭제하기
     * */
    @RequestMapping("/delete")
    public String replyBoardDelete(String replyBoardPk,String classification) {
        replyBoardService.replyBoardDelete(replyBoardPk);
        return "redirect:"+classification+"?classification="+classification;
    }
}
