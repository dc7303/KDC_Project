package edu.kosta.kdc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 정렬하기 select
     * */
    @RequestMapping(value= {"/dateOrderby","/likeOrderby","/viewOrderby","/replyOrderby"})
    public String SelectOrderby(String classification, String sort, Model model) {
        List<ReplyBoardDTO> list = replyBoardService.replyBoardSelectAllOrderBy(classification, sort);
        model.addAttribute("classification",classification);
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
    public String techBoardInsert(String classification, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        for(String s: hashTagName) {
            //System.out.println(s);            
        }
        
        replyBoardDTO.setReplyBoardClassification(classification);
        replyBoardService.insertReply(replyBoardDTO, hashTagName);
        
        return "redirect:tech?classification="+classification;
    }
    
    /**
     * 댓글 등록하기
     * */
    @RequestMapping("/replyInsert")
    public String replyInsert(String classification,int replyBoardPk, ReplyBoardDTO replyBoardDTO, Model model) {
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
    public String read(int replyBoardPk,String classification,ReplyBoardDTO replyBoardDTODB, HttpServletRequest request, Model model) {
        boolean state = request.getParameter("state")== null? true : false;
        replyBoardDTODB.setReplyBoardClassification(classification);

        List<ReplyBoardDTO> list = replyBoardService.selectByReplyBoardPK(replyBoardDTODB, state);

        model.addAttribute("replyBoardDTO",list);
        model.addAttribute("classification",classification);
        model.addAttribute("replyBoardPk",replyBoardPk);
        return "replyBoard/read";
    }
    
    /**
     * 수정하기 폼
     * */
    @RequestMapping("/updateForm")
    public String updateForm(int replyBoardPk, String classification, ReplyBoardDTO replyBoardDTODB, HttpServletRequest request, Model model){
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
    public String replyBoardUpdate(String classification, int replyBoardPk, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        replyBoardDTO.setReplyBoardPk(replyBoardPk);
        replyBoardService.replyBoardUpdate(replyBoardDTO, hashTagName);
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk;
    }
    
    /**
     * 댓글 수정 폼
     * */
    @RequestMapping("/replyUpdateForm")
    public String replyUpdateForm(int replyBoardReplyPk, String classification, int replyBoardPk, Model model, ReplyBoardDTO replyBoardDTODB, HttpServletRequest request) {       
        boolean state = request.getParameter("state")== null? true : false;
        
        replyBoardDTODB.setReplyBoardClassification(classification);
        
        List<ReplyBoardDTO> list = replyBoardService.selectByReplyBoardPK(replyBoardDTODB, state);
        
        model.addAttribute("replyBoardDTO",list);
        model.addAttribute("replyBoardPk", replyBoardPk);
        model.addAttribute("classification",classification);
        model.addAttribute("replyBoardReplyPk",replyBoardReplyPk);

        return "replyBoard/replyUpdateForm";
    }
    
    /**
     * 댓글 수정하기
     * */
    @RequestMapping("/replyUpdate")
    public String replyUpdate(String classification, int replyBoardReplyNo,int replyBoardPk, ReplyBoardDTO replyBoardDTO, String replyBoardContents) {
        replyBoardService.replyUpdate(replyBoardDTO);
        
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk;
    }
    
    /**
     * 게시글 삭제하기
     * */
    @RequestMapping("/delete")
    public String replyBoardDelete(int replyBoardPk,String classification) {
        replyBoardService.replyBoardDelete(replyBoardPk);
        return "redirect:"+classification+"?classification="+classification;
    }
    
    /**
     * 댓글 삭제하기
     * */
    @RequestMapping("/replyDelete")
    public String replyDelete(int replyBoardReplyPk, String classification, ReplyBoardDTO replyBoardDTO, int replyBoardPk) {
        replyBoardService.replyDelete(replyBoardReplyPk);
        return "redirect:read?replyBoardPk="+replyBoardPk+"&classification="+classification;
    }
    
    /**
     * replyBoardList 검색하기
     * */
    @RequestMapping("/replyBoardListSearch")
    public String replyBoardListSearch(@RequestParam(value="classification") String classification, String department, String boardSearch, Model model) {
        
        model.addAttribute("classification",classification);
        List<ReplyBoardDTO> list = replyBoardService.replyBoardListSearch(department, boardSearch, classification);
        model.addAttribute("list",list);
        
        return "replyBoard/replyBoardList";
    }
    
    /**
     * 게시글 좋아요하기
     * */
    @RequestMapping("/replyBoardLike")
    @ResponseBody
    public int replyBoardLike(int replyBoardPk) {
        int result = replyBoardService.replyBoardLike(replyBoardPk);
        return result;
    }
    
    /**
     * 게시글 싫어요하기
     * */
    @RequestMapping("/replyBoardDisLike")
    @ResponseBody
    public int replyBoardDisLike(int replyBoardPk) {
        int result = replyBoardService.replyBoardDisLike(replyBoardPk);
        return result;
    }
    
    /**
     * 게시글 좋아요 싫어요 취소
     * */
    @RequestMapping("/replyBoardLikeCancle")
    @ResponseBody
    public int replyBoardLikeCancle(int replyBoardPk) {
        int result = replyBoardService.replyBoardLikeCancle(replyBoardPk);
        
        return result;
    }
    
    /**
     * 해시태그 제안하기
     * */
    @RequestMapping("/hashtagSuggest")
    @ResponseBody
    public List<String> hashtagSuggest(String keyWord) {
        
        return replyBoardService.hashtagSuggest(keyWord);
    }

    /**
     * 신고창 띄우기
     * */
    @RequestMapping("/reportPopForm")
    public String reportPopForm(int replyBoardPkReport, Model model) {
        model.addAttribute("replyBoardPkReport",replyBoardPkReport);
        return "/replyBoard/reportPopForm";
    }
    
    /**
     * 신고하기
     * */
    @RequestMapping("/reportPop")
    public String reportPop(String reportContents, int replyBoardPkReport, String otherWords) {
            replyBoardService.reportPopInsert(reportContents, replyBoardPkReport, otherWords);
            
        return "replyBoard/read";
    }
}
