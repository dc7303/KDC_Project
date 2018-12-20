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
     * ��ü ����Ʈ ����
     * */
    @RequestMapping(value= {"/tech","/lib","/study"})
    public String list(@RequestParam(value="classification") String classification, Model model) {
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
    public String techBoardInsert(String classification, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        for(String s: hashTagName) {
            //System.out.println(s);            
        }
        
        replyBoardDTO.setReplyBoardClassification(classification);
        replyBoardService.insertReply(replyBoardDTO, hashTagName);
        
        return "redirect:tech?classification="+classification;
    }
    
    /**
     * ��� ����ϱ�
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
     * �󼼺���
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
     * �����ϱ� ��
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
     * �Խñ� �����ϱ�
     * */
    @RequestMapping("/replyBoardUpdate")
    public String replyBoardUpdate(String classification, int replyBoardPk, ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        replyBoardDTO.setReplyBoardPk(replyBoardPk);
        replyBoardService.replyBoardUpdate(replyBoardDTO, hashTagName);
        return "redirect:read?classification="+classification+"&replyBoardPk="+replyBoardPk;
    }
    
    /**
     * ��� ���� ��
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
    public int replyBoardLike(int replyBoardPk) {
        int result = replyBoardService.replyBoardLike(replyBoardPk);
        return result;
    }
    
    /**
     * �Խñ� �Ⱦ���ϱ�
     * */
    @RequestMapping("/replyBoardDisLike")
    @ResponseBody
    public int replyBoardDisLike(int replyBoardPk) {
        int result = replyBoardService.replyBoardDisLike(replyBoardPk);
        return result;
    }
    
    /**
     * �Խñ� ���ƿ� �Ⱦ�� ���
     * */
    @RequestMapping("/replyBoardLikeCancle")
    @ResponseBody
    public int replyBoardLikeCancle(int replyBoardPk) {
        int result = replyBoardService.replyBoardLikeCancle(replyBoardPk);
        
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
    public String reportPopForm(int replyBoardPkReport, Model model) {
        model.addAttribute("replyBoardPkReport",replyBoardPkReport);
        return "/replyBoard/reportPopForm";
    }
    
    /**
     * �Ű��ϱ�
     * */
    @RequestMapping("/reportPop")
    public String reportPop(String reportContents, int replyBoardPkReport, String otherWords) {
            replyBoardService.reportPopInsert(reportContents, replyBoardPkReport, otherWords);
            
        return "replyBoard/read";
    }
}
