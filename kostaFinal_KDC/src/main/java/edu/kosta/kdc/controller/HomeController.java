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
    //���� ����Ʈ�� ������ �湮�� ��
    private int todayUserCount;
    
    @Autowired
    NoticeBoardService noticeBoardService;
    
    @Autowired
    ReplyBoardService replyBoardService;
    
    @Autowired
    AdminService adminService;
    
    @RequestMapping("/")
    public String index(HttpServletRequest request){
        
        //���� ������ ���� �� userCount�� �����Ƿ� ����� �������ش�.
        if(request.getAttribute("userCount")==null) {
            request.setAttribute("userCount", 1);
        }
        //�湮�� �� ����
        todayUserCount++;
        
        //��¥ (��, ��, ��, ��, ��, ��) ����
        String str="";
        
        //��¥ (��, ��, �� �� �ִ�) ����. view�� �Ѹ� �� ��, ��, �� ���ܽ�Ű���� ����.
        String subStr="";
        
        //index.jsp �� �Ѹ� �Խ��� �̸��� ��� ����
        String classification="";
        
        //�������� �Խ��ǿ��� ����ȭ�鿡 ���� 5���� �� ��������
        List<NoticeBoardDTO> generalNoticeListFive = noticeBoardService.selectFive();
        
        //date String�� �� �� �� ���� �����Ƿ�, ��-�� �� ������ subString�ؼ� ����.
        for(int i = 0; i < generalNoticeListFive.size(); i++) {
            str = generalNoticeListFive.get(i).getNoticeBoardDate();
            subStr = str.substring(5, 10);
            generalNoticeListFive.get(i).setNoticeBoardDate(subStr);
        }
        
        //�������� �Խ��� 5���� �� ����
        request.setAttribute("generalNoticeListFive", generalNoticeListFive);
        
        //Tech �Խ��ǿ��� ����ȭ�鿡 ���� 5���� �� ��������
        classification = "tech";
        List<ReplyBoardDTO> techListFive = replyBoardService.selectFiveByTitle(classification);
        
        //date String�� �� �� �� ���� �����Ƿ�, ��-�� �� ������ subString�ؼ� ����.
        for(int i = 0; i < techListFive.size(); i++) {
            str = techListFive.get(i).getReplyBoardDate();
            subStr = str.substring(5, 10);
            techListFive.get(i).setReplyBoardDate(subStr);
        }
        
        //Tech �Խ��� 5���� �� ����
        request.setAttribute("techListFive", techListFive);
        
        //lib �Խ��ǿ��� ����ȭ�鿡 ���� 5���� �� ��������
        classification = "lib";
        List<ReplyBoardDTO> libListFive = replyBoardService.selectFiveByTitle(classification);
        
        //date String�� �� �� �� ���� �����Ƿ�, ��-�� �� ������ subString�ؼ� ����.
        for(int i = 0; i < libListFive.size(); i++) {
            str = libListFive.get(i).getReplyBoardDate();
            subStr = str.substring(5, 10);
            libListFive.get(i).setReplyBoardDate(subStr);
        }
        
        //lib �Խ��� 5���� �� ����
        request.setAttribute("libListFive", libListFive);
        
        return "index";
        
    }

    /**
     * �����ٷ� - ���� ������ �湮�� ���� DB�� �����Ű��, totalUserCount �� ������Ű�� todayUserCount 0���� �ʱ�ȭ �ϴ� �����ٷ�.
     * */
    @Scheduled(cron="0 8 17 * * ?")
    public void doSchedule() {
        
        int result = adminService.userCountIntoDB(todayUserCount);
        
        //DB�� ���������� �־���ٸ�, ������ ���� �ʱ�ȭ �����ش�.
        if(result==1) {
            todayUserCount = 0;
        }
        
    }
    
}
