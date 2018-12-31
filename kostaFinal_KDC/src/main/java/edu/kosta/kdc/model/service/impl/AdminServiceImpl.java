package edu.kosta.kdc.model.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.AdminDAO;
import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.dto.VisitDTO;
import edu.kosta.kdc.model.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ReplyBoardDAO replyDAO;
    
    @Autowired
    private NoticeBoardDAO noticeDAO;
    
    @Autowired
    private AdminDAO adminDAO;
    
    @Override
    public Map<String, Integer> boardQuantityByClassification() {
        
        String tech = "tech";                   //reply tech Q&A
        String lib = "lib";                     //reply 기술 공유
        String study = "study";                 //reply 스터디 모집
        
        String findJobNotice = "findJobNotice";//notice Board 채용게시판
        
        //수량 담을 맵.
        Map<String, Integer> map = new HashMap<>();
        
        //reply 영역 게시판 수 가져오기
        map.put("tech", replyDAO.boardQuantityByClassification(tech));
        map.put("lib", replyDAO.boardQuantityByClassification(lib));
        map.put("study", replyDAO.boardQuantityByClassification(study));
        
        //notice 영역 게시판 수 가져오기
        map.put("findJob", noticeDAO.boardQuantityByClassification(findJobNotice));
        
        return map;
        
    }
    
    /**
     * 방문자 수 저장하는 메소드
     * */
    @Override
    public int userCountIntoDB(int todayUserCount) {
        
        return adminDAO.userCountIntoDB(todayUserCount);

    }

    /**
     * 방문자 수 가져오는 메소드 (최근 5일) 
     * */
    @Override
    public List<VisitDTO> visitNumListSelect() {
        
        return adminDAO.visitNumListSelect();
    }

}
