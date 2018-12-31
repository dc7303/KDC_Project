package edu.kosta.kdc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.WrittenBoardListService;

@Controller
@RequestMapping("/board")
public class WrittenBoardListController {

    @Autowired
    private WrittenBoardListService writtenBoardListService;
    
    /**
     * 전체 게시글 리스트
     * */
    @RequestMapping("/boardList")
    public ModelAndView allBoardList() {
        
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String id = member.getMemberId();

        List<ReplyBoardDTO> list = writtenBoardListService.writtenBoardList(id);
        
        return new ModelAndView("board/boardList", "list", list);
            
    }
    
    /**
     * 게시글 삭제
     * */
    @RequestMapping("/deleteBoard")
    public String delelteWrittenBoard(int replyBoardPk, HttpSession session) {
        
        writtenBoardListService.delelteWrittenBoard(replyBoardPk);
        
        String userId = (String)session.getAttribute("userId");
        
        return "redirect:/board/boardList?id="+userId;
        
    }
    
}
