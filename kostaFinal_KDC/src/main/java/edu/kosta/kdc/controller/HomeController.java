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
        //�������� �Խ��ǿ��� ����ȭ�鿡 ���� 5���� �� ��������
        List<NoticeBoardDTO> noticeListFive = noticeBoardService.selectFive();
        
        //date String�� �� �� �� ���� �����Ƿ�, ��-�� �� ������ subString�ؼ� ����.
        for(int i=0; i<5; i++) {
            str = noticeListFive.get(i).getNoticeBoardDate();
            subStr = str.substring(5, 10);
            noticeListFive.get(i).setNoticeBoardDate(subStr);
        }
        
        //�������� �Խ��� 5���� �� ����
        request.setAttribute("noticeListFive", noticeListFive);
        
        //Tech �Խ��ǿ��� ����ȭ�鿡 ���� 5���� �� ��������
        title = "tech";
        List<ReplyBoardDTO> techListFive = replyBoardService.selectFiveByTitle(title);
        
        //date String�� �� �� �� ���� �����Ƿ�, ��-�� �� ������ subString�ؼ� ����.
        for(int i=0; i<5 ;i++) {
            str = techListFive.get(i).getReplyBoardDate();
            subStr = str.substring(5, 10);
            techListFive.get(i).setReplyBoardDate(subStr);
        }
        
        //Tech �Խ��� 5���� �� ����
        request.setAttribute("techListFive", techListFive);
        
        
        //lib �Խ��ǿ��� ����ȭ�鿡 ���� 5���� �� ��������
        title = "lib";
        List<ReplyBoardDTO> libListFive = replyBoardService.selectFiveByTitle(title);
        
        //date String�� �� �� �� ���� �����Ƿ�, ��-�� �� ������ subString�ؼ� ����.
        for(int i=0; i<5 ;i++) {
            str = libListFive.get(i).getReplyBoardDate();
            subStr = str.substring(5, 10);
            libListFive.get(i).setReplyBoardDate(subStr);
        }
        
        //lib �Խ��� 5���� �� ����
        request.setAttribute("libListFive", libListFive);
        
        return "index";
        
    }
    
}
