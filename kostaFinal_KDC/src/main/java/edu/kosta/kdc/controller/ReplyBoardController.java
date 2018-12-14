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
    public String list(@RequestParam(value="title") String title, Model model) {
        model.addAttribute("title",title);
        List<ReplyBoardDTO> list = replyBoardService.selectAll(title);
        model.addAttribute("list",list);
        
        return "replyBoard/replyBoardList";
    }
    
    /**
     * 등록 폼
     * */
    @RequestMapping("/write")
    public String writePage(@RequestParam(value="title") String title, Model model) {
        model.addAttribute("title",title);
        return "/replyBoard/write";
    }
    
    /**
     * 등록하기
     * */
    @RequestMapping("/insert")
    public String techBoardInsert(@RequestParam(value="title") String title, ReplyBoardDTO replyBoardDTO) {
        replyBoardDTO.setReplyBoardClassification(title);
        replyBoardService.insert(replyBoardDTO);
        return "redirect:tech?title="+title;
    }
    
    /**
     * 상세보기
     * */
    @RequestMapping("/read")
    public String read(@RequestParam(value="replyBoardPk")int replyBoardPk, @RequestParam(value="classification") String title,ReplyBoardDTO replyBoardDTODB, HttpServletRequest request, Model model) {
        boolean state = request.getParameter("state")== null? true : false;
        replyBoardDTODB.setReplyBoardClassification(title);

        List<ReplyBoardDTO> replyBoardDTO = replyBoardService.selectByReplyBoardPK(replyBoardDTODB, state);
        //request.setAttribute("replyBoardDTO", replyBoardDTO);
        for(ReplyBoardDTO dto : replyBoardDTO) {
        }
        model.addAttribute("replyBoardDTO",replyBoardDTO);
        return "/replyBoard/read";
    }
}
