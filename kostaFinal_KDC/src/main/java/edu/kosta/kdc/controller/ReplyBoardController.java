package edu.kosta.kdc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.dto.ReportDTO;
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
    public String list(String classification, Model model, int pageNo) {
        
        model.addAttribute("classification",classification);
        
        int listSize = replyBoardService.boardQuantityByClassification(classification);
        List<ReplyBoardDTO> list = replyBoardService.selectAll(classification, pageNo);
        model.addAttribute("list",list);
        model.addAttribute("listSize",listSize);
        model.addAttribute("sort", "REPLY_BOARD_PK");
        model.addAttribute("pageNo",pageNo);
        return "replyBoard/replyBoardList";
    }

    
    /**
     * 정렬하기 select
     * */
    @RequestMapping(value= {"/dateOrderby","/likeOrderby","/viewOrderby","/replyOrderby", "/pkOrderby", "/orderBy"})
    public String SelectOrderby(String classification, String sort, Model model, int pageNo) {
 
        int listSize = replyBoardService.boardQuantityByClassification(classification);
        List<ReplyBoardDTO> list = replyBoardService.replyBoardSelectAllOrderBy(classification, sort, pageNo);
        model.addAttribute("classification",classification);
        model.addAttribute("list",list);
        model.addAttribute("listSize",listSize);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNo",pageNo);
        
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
    public String techBoardInsert(String classification,String memberId, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        replyBoardDTO.setReplyBoardWriterId(memberId);
        replyBoardDTO.setReplyBoardClassification(classification);
        replyBoardService.insertReply(replyBoardDTO, hashTagName);
        
        return "redirect:tech?classification="+classification+"&pageNo=1";
    }
    
    /**
     * 댓글 등록하기
     * */
    @RequestMapping("/replyInsert")
    public String replyInsert(String classification,int replyBoardPk,String memberId, String mentionNickName, String replyContents, ReplyBoardDTO replyBoardDTO, Model model) {
        replyBoardDTO.setMentionNickName(mentionNickName);
        replyBoardDTO.setReplyBoardWriterId(memberId);
        replyBoardDTO.setReplyBoardClassification(classification);
        replyBoardDTO.setReplyBoardContents(replyContents);

        replyBoardService.replyInsert(replyBoardDTO);        
        model.addAttribute("classification",classification);
        
        return "redirect:read?replyBoardPk="+replyBoardPk+"&memberId="+memberId;
    }
    
    /**
     * 상세보기
     * */
    @RequestMapping("/read")
    public String read(int replyBoardPk,String classification, ReplyBoardDTO replyBoardDTO, HttpServletRequest request, Model model) {
        MemberDTO member = null;
        
        try {
            member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(ClassCastException e) {
           member = new MemberDTO();
           member.setMemberId("a");
        }
        boolean state = request.getParameter("state") == null ? true : false;
        
        replyBoardDTO.setReplyBoardWriterId(member.getMemberId());
        replyBoardDTO.setReplyBoardClassification(classification);
        List<ReplyBoardDTO> list = replyBoardService.selectByReplyBoardPK(replyBoardDTO, state);
        model.addAttribute("replyBoardDTO",list);
        model.addAttribute("classification",classification);
        model.addAttribute("replyBoardPk",replyBoardPk);
        model.addAttribute("memberId",member.getMemberId());
        return "replyBoard/read";
    }
    
    /**
     * 수정하기 폼
     * */
    @RequestMapping("/updateForm")
    public String updateForm(int replyBoardPk, String classification, ReplyBoardDTO replyBoardDTO, HttpServletRequest request, Model model){
        replyBoardDTO.setReplyBoardClassification(classification);
        
        List<ReplyBoardDTO> list = replyBoardService.selectByReplyBoardPK(replyBoardDTO, false);
        
        model.addAttribute("classification",classification);
        model.addAttribute("replyBoardDTO",list);
        model.addAttribute("replyBoardPk",replyBoardPk);

        return "replyBoard/updateForm";
    }
    
    /**
     * 게시글 수정하기
     * */
    @RequestMapping("/replyBoardUpdate")
    public String replyBoardUpdate(String classification, int replyBoardPk, String memberId, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        
        replyBoardDTO.setReplyBoardPk(replyBoardPk);
        replyBoardService.replyBoardUpdate(replyBoardDTO, hashTagName);
        
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk+"&memberId="+memberId;
    }
    
    /**
     * 댓글 수정 폼
     * */
    @RequestMapping("/replyUpdateForm")
    public String replyUpdateForm(int replyBoardReplyPk, String classification, int replyBoardPk, Model model, ReplyBoardDTO replyBoardDTODB, HttpServletRequest request) {       
        
        replyBoardDTODB.setReplyBoardClassification(classification);
        
        List<ReplyBoardDTO> list = replyBoardService.selectByReplyBoardPK(replyBoardDTODB, false);
        
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
    public String replyUpdate(String classification, int replyBoardReplyNo,int replyBoardPk, String memberId,ReplyBoardDTO replyBoardDTO, String replyBoardContents, String mentionNickName) {
        replyBoardDTO.setMentionNickName(mentionNickName);
        replyBoardService.replyUpdate(replyBoardDTO);
        
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk+"&memberId="+memberId;
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
    public String replyDelete(int replyBoardReplyPk, String classification, String memberId, ReplyBoardDTO replyBoardDTO, int replyBoardPk) {
        
        replyBoardService.replyDelete(replyBoardReplyPk);
        
        return "redirect:read?replyBoardPk="+replyBoardPk+"&classification="+classification+"&memberId="+memberId;
    }
    
    /**
     * replyBoardList 검색하기
     * */
    @RequestMapping("/replyBoardListSearch")
    public String replyBoardListSearch(int pageNo, String classification, String department, String boardSearch, Model model) {

        model.addAttribute("classification",classification);
        
        int listSize = replyBoardService.boardQuantityByClassificationwithSearch(department, boardSearch, classification);

        List<ReplyBoardDTO> list = replyBoardService.replyBoardListSearch(department, boardSearch, classification, pageNo);
        model.addAttribute("list",list);
        model.addAttribute("listSize",listSize);
        model.addAttribute("department",department);
        model.addAttribute("boardSearch",boardSearch);
        model.addAttribute("pageNo", pageNo);
        return "replyBoard/replyBoardList";
    }
    
    /**
     * 게시글 좋아요하기
     * */
    @RequestMapping("/replyBoardLike")
    @ResponseBody
    public int replyBoardLike(String memberId, ReplyBoardDTO replyBoardDTO) {
        replyBoardDTO.setReplyBoardWriterId(memberId);
        int result = replyBoardService.replyBoardLike(replyBoardDTO);
        
        return result;
    }
    
    /**
     * 게시글 싫어요하기
     * */
    @RequestMapping("/replyBoardDisLike")
    @ResponseBody
    public int replyBoardDisLike(String memberId, ReplyBoardDTO replyBoardDTO) {
        replyBoardDTO.setReplyBoardWriterId(memberId);

        int result = replyBoardService.replyBoardDisLike(replyBoardDTO);
        
        return result;
    }
    
    /**
     * 게시글 좋아요 싫어요 취소
     * */
    @RequestMapping("/replyBoardLikeCancle")
    @ResponseBody
    public int replyBoardLikeCancle(String memberId, ReplyBoardDTO replyBoardDTO) {
        replyBoardDTO.setReplyBoardWriterId(memberId);

        int result = replyBoardService.replyBoardLikeCancle(replyBoardDTO);
        
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
    public String reportPopForm(int replyBoardPkReport, String memberId, Model model) {
        model.addAttribute("replyBoardPkReport",replyBoardPkReport);
        model.addAttribute("memberId",memberId);
        return "/replyBoard/reportPopForm";
    }
    
    /**
     * 신고하기
     * */
    @RequestMapping("/reportPop")
    public String reportPop(String reportContents, int replyBoardPkReport, String otherWords, String memberId, ReportDTO reportDTO) {
        replyBoardService.reportPopInsert(reportContents, replyBoardPkReport, otherWords, memberId);
        return "replyBoard/read";
    }
    
    /**
     * 멘션태그 제안하기
     * */
    @RequestMapping("/mentionSuggest")
    @ResponseBody
    public List<String> mentionSuggest(String keyWord) {
        
        return replyBoardService.mentionSuggest(keyWord);
    }

    /**
     * 모든 닉네임 가오기
     * */
    @RequestMapping("/allNicknames")
    @ResponseBody
    public List<String> allNicknames() {
        
        return replyBoardService.allNicknames();
    }
    
}
