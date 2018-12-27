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
import edu.kosta.kdc.model.dto.ReportDTO;
import edu.kosta.kdc.model.service.ReplyBoardService;

@Controller
@RequestMapping("/reply")
public class ReplyBoardController {

    @Autowired
    private ReplyBoardService replyBoardService;
    
    /**
     * ��ü ����Ʈ ����
     * */
    @RequestMapping(value= {"/tech","/lib","/study"})
    public String list(String classification, Model model) {
        
        model.addAttribute("classification",classification);
        
        List<ReplyBoardDTO> list = replyBoardService.selectAll(classification);
        model.addAttribute("list",list);
        
        return "replyBoard/replyBoardList";
    }
    
    /**
     * �����ϱ� select
     * */
    @RequestMapping(value= {"/dateOrderby","/likeOrderby","/viewOrderby","/replyOrderby"})
    public String SelectOrderby(String classification, String sort, Model model) {
        
        List<ReplyBoardDTO> list = replyBoardService.replyBoardSelectAllOrderBy(classification, sort);
        model.addAttribute("classification",classification);
        model.addAttribute("list",list);
        
        return "replyBoard/replyBoardList";
    }
    
    /**
     * ��� ��
     * */
    @RequestMapping("/write")
    public String writePage(@RequestParam(value="classification") String classification, Model model) {
        
        model.addAttribute("classification",classification);
        
        return "replyBoard/write";
    }
    
    /**
     * �Խñ� ����ϱ�
     * */
    @RequestMapping("/insert")
    public String techBoardInsert(String classification,String memberId, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        replyBoardDTO.setReplyBoardWriterId(memberId);
        replyBoardDTO.setReplyBoardClassification(classification);
        replyBoardService.insertReply(replyBoardDTO, hashTagName);
        
        return "redirect:tech?classification="+classification;
    }
    
    /**
     * ��� ����ϱ�
     * */
    @RequestMapping("/replyInsert")
    public String replyInsert(String classification,int replyBoardPk,String memberId, String mentionInput, String replyContents, ReplyBoardDTO replyBoardDTO, Model model) {
        replyBoardDTO.setMentionNickName(mentionInput);
        replyBoardDTO.setReplyBoardMention(mentionInput);
        replyBoardDTO.setReplyBoardWriterId(memberId);
        replyBoardDTO.setReplyBoardClassification(classification);
        replyBoardDTO.setReplyBoardContents(replyContents);
        replyBoardService.replyInsert(replyBoardDTO);        
        model.addAttribute("classification",classification);
        
        return "redirect:read?replyBoardPk="+replyBoardPk;
    }
    
    /**
     * �󼼺���
     * */
    @RequestMapping("/read")
    public String read(int replyBoardPk,String classification, String memberId, ReplyBoardDTO replyBoardDTO, HttpServletRequest request, Model model) {

        boolean state = request.getParameter("state") == null ? true : false;
        
        replyBoardDTO.setReplyBoardWriterId(memberId);
        replyBoardDTO.setReplyBoardClassification(classification);
        
        List<ReplyBoardDTO> list = replyBoardService.selectByReplyBoardPK(replyBoardDTO, state);

        model.addAttribute("replyBoardDTO",list);
        model.addAttribute("classification",classification);
        model.addAttribute("replyBoardPk",replyBoardPk);
        model.addAttribute("memberId",memberId);
        return "replyBoard/read";
    }
    
    /**
     * �����ϱ� ��
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
     * �Խñ� �����ϱ�
     * */
    @RequestMapping("/replyBoardUpdate")
    public String replyBoardUpdate(String classification, int replyBoardPk, String memberId, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        
        replyBoardDTO.setReplyBoardPk(replyBoardPk);
        replyBoardService.replyBoardUpdate(replyBoardDTO, hashTagName);
        
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk+"&memberId="+memberId;
    }
    
    /**
     * ��� ���� ��
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
     * ��� �����ϱ�
     * */
    @RequestMapping("/replyUpdate")
    public String replyUpdate(String classification, int replyBoardReplyNo,int replyBoardPk, ReplyBoardDTO replyBoardDTO, String replyBoardContents) {
        
        replyBoardService.replyUpdate(replyBoardDTO);
        
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk;
    }
    
    /**
     * �Խñ� �����ϱ�
     * */
    @RequestMapping("/delete")
    public String replyBoardDelete(int replyBoardPk,String classification) {
        System.out.println("controller replyBoardPk : " + replyBoardPk);
        replyBoardService.replyBoardDelete(replyBoardPk);
        
        return "redirect:"+classification+"?classification="+classification;
    }
    
    /**
     * ��� �����ϱ�
     * */
    @RequestMapping("/replyDelete")
    public String replyDelete(int replyBoardReplyPk, String classification, ReplyBoardDTO replyBoardDTO, int replyBoardPk) {
        
        replyBoardService.replyDelete(replyBoardReplyPk);
        
        return "redirect:read?replyBoardPk="+replyBoardPk+"&classification="+classification;
    }
    
    /**
     * replyBoardList �˻��ϱ�
     * */
    @RequestMapping("/replyBoardListSearch")
    public String replyBoardListSearch(@RequestParam(value="classification") String classification, String department, String boardSearch, Model model) {
        
        model.addAttribute("classification",classification);
        
        List<ReplyBoardDTO> list = replyBoardService.replyBoardListSearch(department, boardSearch, classification);
        model.addAttribute("list",list);
        
        return "replyBoard/replyBoardList";
    }
    
    /**
     * �Խñ� ���ƿ��ϱ�
     * */
    @RequestMapping("/replyBoardLike")
    @ResponseBody
    public int replyBoardLike(String memberId, ReplyBoardDTO replyBoardDTO) {
        replyBoardDTO.setReplyBoardWriterId(memberId);
        int result = replyBoardService.replyBoardLike(replyBoardDTO);
        
        return result;
    }
    
    /**
     * �Խñ� �Ⱦ���ϱ�
     * */
    @RequestMapping("/replyBoardDisLike")
    @ResponseBody
    public int replyBoardDisLike(String memberId, ReplyBoardDTO replyBoardDTO) {
        replyBoardDTO.setReplyBoardWriterId(memberId);

        int result = replyBoardService.replyBoardDisLike(replyBoardDTO);
        
        return result;
    }
    
    /**
     * �Խñ� ���ƿ� �Ⱦ�� ���
     * */
    @RequestMapping("/replyBoardLikeCancle")
    @ResponseBody
    public int replyBoardLikeCancle(String memberId, ReplyBoardDTO replyBoardDTO) {
        replyBoardDTO.setReplyBoardWriterId(memberId);

        int result = replyBoardService.replyBoardLikeCancle(replyBoardDTO);
        
        return result;
    }
    
    /**
     * �ؽ��±� �����ϱ�
     * */
    @RequestMapping("/hashtagSuggest")
    @ResponseBody
    public List<String> hashtagSuggest(String keyWord) {
        
        return replyBoardService.hashtagSuggest(keyWord);
    }

    /**
     * �Ű�â ����
     * */
    @RequestMapping("/reportPopForm")
    public String reportPopForm(int replyBoardPkReport, String memberId, Model model) {
        model.addAttribute("replyBoardPkReport",replyBoardPkReport);
        model.addAttribute("memberId",memberId);
        return "/replyBoard/reportPopForm";
    }
    
    /**
     * �Ű��ϱ�
     * */
    @RequestMapping("/reportPop")
    public String reportPop(String reportContents, int replyBoardPkReport, String otherWords, String memberId, ReportDTO reportDTO) {
        replyBoardService.reportPopInsert(reportContents, replyBoardPkReport, otherWords, memberId);
        return "replyBoard/read";
    }
    
    /**
     * ����±� �����ϱ�
     * */
    @RequestMapping("/mentionSuggest")
    @ResponseBody
    public List<String> mentionSuggest(String keyWord) {
        
        return replyBoardService.mentionSuggest(keyWord);
    }

    /**
     * ��� �г��� ������
     * */
    @RequestMapping("/allNicknames")
    @ResponseBody
    public List<String> allNicknames() {
        
        return replyBoardService.allNicknames();
    }
    
}
