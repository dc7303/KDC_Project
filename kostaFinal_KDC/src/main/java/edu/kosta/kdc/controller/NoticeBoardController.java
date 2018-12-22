package edu.kosta.kdc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;

@Controller
@RequestMapping("/notice")
public class NoticeBoardController {

    private String path = "C:\\edu\\final_img";

    @Autowired
    private NoticeBoardService noticeBoardService;

    /**
     * 전체 검색
     */
    @RequestMapping("/list")
    public String Board(Model model, String classification) {
        
        List<NoticeBoardDTO> list = noticeBoardService.selectAll(classification);
        model.addAttribute("list", list);
        
        return "notice/noticeList";
    }

    /**
     * 조건 검색
     */
    @RequestMapping("/listserch")
    public String SerchList(String department, String noticeBoardSearch, Model model) {

        List<NoticeBoardDTO> list = noticeBoardService.SelectSerch(department, noticeBoardSearch);
        model.addAttribute("list", list);
        
        return "notice/noticeList";
    }

    /**
     * 글쓰기 폼
     */
    @RequestMapping("/writeForm")
    public String insertForm() {

        return "notice/noticeWrite";
    }

    /**
     * 레코드 삽입
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
        
        return "redirect:list";
    }

    /**
     * 제목 선택해서 상세보기
     */
    @RequestMapping("/read")
    public String noticeRead(int noticeBoardPk, Model model, HttpServletRequest request) throws Exception {
        boolean state = request.getParameter("state") == null ? true : false;

        NoticeBoardDTO noticeBoard = noticeBoardService.selectByNoticeBoardTitle(noticeBoardPk, true);
        model.addAttribute("NoticeBoardDTO", noticeBoard);

        return "notice/noticeRead";
    }

    /**
     * 수정하기 폼
     **/
    @RequestMapping("/updateForm")
    public ModelAndView updateForm(NoticeBoardDTO noticeBoard, int noticeBoardPk, Model model) {

        noticeBoard = noticeBoardService.selectByNoticeBoardTitle(noticeBoardPk, false);
        model.addAttribute("noticeBoardPk", noticeBoardPk);

        return new ModelAndView("notice/noticeUpdate", "NoticeBoardDTO", noticeBoard);
    }

    /**
     * 수정하기
     */

    @RequestMapping("/update")
    public String update(int noticeBoardPk, NoticeBoardDTO noticeBoard) throws Exception {
   
        noticeBoardService.update(noticeBoard);
        
        return "redirect:read?noticeBoardPk=" + noticeBoardPk;

    }

    /**
     * 삭제하기
     */

    @RequestMapping("/delete")
    public String delete(int noticeBoardPk) {

        noticeBoardService.delete(noticeBoardPk);
        
        return "redirect:list";
    }

    /**
     * 다운로드하기
     */
    @RequestMapping("/downLoad")
    public ModelAndView downLoad(HttpSession session, String attachment) {

        return new ModelAndView("downLoadView", "attachment", new File(path + "/" + attachment));
    }

}
