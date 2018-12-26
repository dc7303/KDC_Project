package edu.kosta.kdc.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;
import edu.kosta.kdc.model.service.ReplyBoardService;


@Controller
public class HomeController {

    @Autowired
    NoticeBoardService noticeBoardService;
    
    @Autowired
    ReplyBoardService replyBoardService;
    
    @RequestMapping("/")
    public String index(HttpServletRequest request){
        
        String str="";
        String subStr="";
        String title="";
        //공지사항 게시판에서 메인화면에 보일 5가지 글 가져오기
        List<NoticeBoardDTO> noticeListFive = noticeBoardService.selectFive();
        
        //date String이 시 분 초 까지 나오므로, 월-일 만 나오게 subString해서 저장.
        for(int i=0; i<5; i++) {
            str = noticeListFive.get(i).getNoticeBoardDate();
            subStr = str.substring(5, 10);
            noticeListFive.get(i).setNoticeBoardDate(subStr);
        }
        
        //공지사항 게시판 5가지 글 저장
        request.setAttribute("noticeListFive", noticeListFive);
        
        //Tech 게시판에서 메인화면에 보일 5가지 글 가져오기
        title = "tech";
        List<ReplyBoardDTO> techListFive = replyBoardService.selectFiveByTitle(title);
        
        //date String이 시 분 초 까지 나오므로, 월-일 만 나오게 subString해서 저장.
        for(int i=0; i<5 ;i++) {
            str = techListFive.get(i).getReplyBoardDate();
            subStr = str.substring(5, 10);
            techListFive.get(i).setReplyBoardDate(subStr);
        }
        
        //Tech 게시판 5가지 글 저장
        request.setAttribute("techListFive", techListFive);
        
        
        //lib 게시판에서 메인화면에 보일 5가지 글 가져오기
        title = "lib";
        List<ReplyBoardDTO> libListFive = replyBoardService.selectFiveByTitle(title);
        
        //date String이 시 분 초 까지 나오므로, 월-일 만 나오게 subString해서 저장.
        for(int i=0; i<5 ;i++) {
            str = libListFive.get(i).getReplyBoardDate();
            subStr = str.substring(5, 10);
            libListFive.get(i).setReplyBoardDate(subStr);
        }
        
        //lib 게시판 5가지 글 저장
        request.setAttribute("libListFive", libListFive);
        
        return "index";
        
    }
    
}
