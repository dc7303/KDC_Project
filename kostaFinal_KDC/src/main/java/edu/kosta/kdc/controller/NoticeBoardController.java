package edu.kosta.kdc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;

@Controller
@RequestMapping("/notice")
public class NoticeBoardController {

    private String path = "C:\\Edu\\KDC\\kdc";

    @Autowired
    private NoticeBoardService noticeBoardService;

    /**
     * ��ü �˻�
     */
    @RequestMapping("/list")
    public String Board(Model model, NoticeBoardDTO noticeBoard, boolean state) {

        List<NoticeBoardDTO> list = noticeBoardService.selectAll(noticeBoard, state);
        model.addAttribute("list", list);
        return "notice/list";
    }

    /**
     * ���� �˻�
     */
    @RequestMapping("/listserch")
    public String SerchList(String department, String noticeBoardSearch, Model model) {
        List<NoticeBoardDTO> list = noticeBoardService.SelectSerch(department, noticeBoardSearch);
        model.addAttribute("list", list);
        if(list==null) {
            
        }
        return "notice/list";
    }

    /**
     * �۾���
     */
    @RequestMapping("/write")
    public String insertForm() {

        return "notice/write";
    }

    /**
     * ���ڵ� ����
     */
    @RequestMapping("/insert")
    public String insert(NoticeBoardDTO noticeBoard) throws Exception {
        
        if(noticeBoard.getFile() != null) {
        MultipartFile file = noticeBoard.getFile();
        String attachment = file.getOriginalFilename();

        noticeBoard.setNoticeBoardAttachment(attachment);
        file.transferTo(new File(path + "/" + attachment));
     
        
        }
  
        noticeBoardService.insert(noticeBoard);
        
        return "redirect:list";
    }

    /**
     * ���� �����ؼ� �󼼺���
     */
    @RequestMapping("/read")
    public String read(int noticeBoardPk, Model model, HttpServletRequest request) throws Exception {
        boolean state = request.getParameter("state") == null ? true : false;

        NoticeBoardDTO noticeBoard = noticeBoardService.selectByNoticeBoardTitle(noticeBoardPk, true);
        model.addAttribute("NoticeBoardDTO", noticeBoard);

        return "notice/read";
    }

    /**
     * �����ϱ� ��
     **/
    @RequestMapping("/updateForm")
    public ModelAndView updateForm(NoticeBoardDTO noticeBoard, int noticeBoardPk, Model model) {

        noticeBoard = noticeBoardService.selectByNoticeBoardTitle(noticeBoardPk, false);
        model.addAttribute("noticeBoardPk", noticeBoardPk);

        return new ModelAndView("notice/update", "NoticeBoardDTO", noticeBoard);
    }

    /**
     * �����ϱ�
     */

    @RequestMapping("/update")
    public String update(int noticeBoardPk, NoticeBoardDTO noticeBoard) throws Exception {
   
        noticeBoardService.update(noticeBoard);
        
        return "redirect:read?noticeBoardPk=" + noticeBoardPk;

    }

    /**
     * �����ϱ�
     */

    @RequestMapping("/delete")
    public String delete(int noticeBoardPk) {

        noticeBoardService.delete(noticeBoardPk);
        return "redirect:list";
    }

    /**
     * �ٿ�ε��ϱ�
     */
    @RequestMapping("/downLoad")
    public ModelAndView downLoad(HttpSession session, String attachment) {
     
        return new ModelAndView("downLoadView", "attachment", new File(path + "/" + attachment));
    }

}
