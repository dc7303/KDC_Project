package edu.kosta.kdc.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;

@Controller
@RequestMapping("/notice")
public class NoticeBoardController {

    private String path = "C:\\edu\\final_img";

    @Autowired
    private NoticeBoardService noticeBoardService;

    /**
     * 穿端 伊事
     */
    @RequestMapping("/list")
    public String Board(Model model, String classification, int pageNum) {
        
        System.out.println("ぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜ");
        Map<String, Object> map = noticeBoardService.selectAll(classification, pageNum);
        System.out.println("ぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜ");
        
        model.addAttribute("resultMap", map);
        model.addAttribute("classification", classification);
        System.out.println("ぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜぜ");
        

        return "notice/noticeList";
    }

    /**
     * 繕闇 伊事
     */
    @RequestMapping("/listserch")
    public String SerchList(String department, String boardSearch, String noticeBoardSearch,
            String classification, Model model, int pageNum) {

        Map<String, Object> map = noticeBoardService.selectNoticePaging(department, boardSearch, classification, pageNum);
        
        model.addAttribute("resultMap", map);
        model.addAttribute("classification",classification);
        
        return "notice/noticeList";
    }

    /**
     * 越床奄 廿
     */
    @RequestMapping("/writeForm")
    public String insertForm(String classification, Model model) {

        model.addAttribute("classification", classification);
        
        return "notice/noticeWrite";
    }

    /**
     * 傾坪球 諮脊
     */
    @RequestMapping("/insert")
    public String insert(NoticeBoardDTO noticeBoard, String classification) throws Exception {
        
        if(noticeBoard.getFile() != null) {
            MultipartFile file = noticeBoard.getFile();
            String attachment = file.getOriginalFilename();
    
            noticeBoard.setNoticeBoardAttachment(attachment);
            file.transferTo(new File(path + "/" + attachment));
            
        }
        noticeBoardService.noticeInsert(noticeBoard, classification);
        
        return "redirect:list?classification=" + classification;
    }

    /**
     * 薦鯉 識澱背辞 雌室左奄
     */
    @RequestMapping("/read")
    public String noticeRead(int noticeBoardPk, Model model, HttpServletRequest request) throws Exception {
        
        MemberDTO member = null;
        try {
            member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch(ClassCastException e) {
            member = new MemberDTO();
            member.setMemberId("a");
        }
        NoticeBoardDTO noticeBoard = noticeBoardService.selectByNoticeBoardTitle(noticeBoardPk, true);
        model.addAttribute("NoticeBoardDTO", noticeBoard);
        model.addAttribute("memberId", member.getMemberId());
        return "notice/noticeRead";
    }

    /**
     * 呪舛馬奄 廿
     **/
    @RequestMapping("/updateForm")
    public ModelAndView updateForm(NoticeBoardDTO noticeBoard, int noticeBoardPk, Model model) {

        noticeBoard = noticeBoardService.selectByNoticeBoardTitle(noticeBoardPk, false);
        model.addAttribute("noticeBoardPk", noticeBoardPk);

        return new ModelAndView("notice/noticeUpdate", "NoticeBoardDTO", noticeBoard);
    }

    /**
     * 呪舛馬奄
     */

    @RequestMapping("/update")
    public String update(int noticeBoardPk, NoticeBoardDTO noticeBoard) throws Exception {
   
        noticeBoardService.update(noticeBoard);
        
        return "redirect:read?noticeBoardPk=" + noticeBoardPk;

    }

    /**
     * 肢薦馬奄
     */

    @RequestMapping("/delete")
    public String delete(int noticeBoardPk) {

        noticeBoardService.delete(noticeBoardPk);
        
        return "redirect:list";
    }

    /**
     * 陥錘稽球馬奄
     */
    @RequestMapping("/downLoad")
    public ModelAndView downLoad(HttpSession session, String attachment) {

        return new ModelAndView("downLoadView", "attachment", new File(path + "/" + attachment));
    }

}
