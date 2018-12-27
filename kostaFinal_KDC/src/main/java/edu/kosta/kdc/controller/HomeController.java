package edu.kosta.kdc.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.AdminService;
import edu.kosta.kdc.model.service.NoticeBoardService;
import edu.kosta.kdc.model.service.ReplyBoardService;


@Controller
public class HomeController {
    //오늘 사이트에 접속한 방문자 수
    private int todayUserCount;
    
    @Autowired
    NoticeBoardService noticeBoardService;
    
    @Autowired
    ReplyBoardService replyBoardService;
    
    @Autowired
    AdminService adminService;
    
    @RequestMapping("/")
    public String index(HttpServletRequest request){
        
        //최초 서버를 켰을 때 userCount가 없으므로 만들어 저장해준다.
        if(request.getAttribute("userCount")==null) {
            request.setAttribute("userCount", 1);
        }
        //방문자 수 증가
        todayUserCount++;
        
        //날짜 (연, 월, 일, 시, 분, 초) 변수
        String str="";
        
        //날짜 (연, 월, 일 만 있는) 변수. view에 뿌릴 때 시, 분, 초 제외시키려고 만듦.
        String subStr="";
        
        //index.jsp 에 뿌릴 게시판 이름을 담는 변수
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

    /**
     * 스케줄러 - 오늘 접속한 방문자 수를 DB에 저장시키고, totalUserCount 에 누적시키고 todayUserCount 0으로 초기화 하는 스케줄러.
     * */
    @Scheduled(cron="0 59 23 * * ?")
    public void doSchedule() {
        
        int result = adminService.userCountIntoDB(todayUserCount);
        
        //DB에 성공적으로 넣어줬다면, 접속자 수를 초기화 시켜준다.
        if(result==1) {
            todayUserCount = 0;
        }
        
    }
    
}
