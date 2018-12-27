package edu.kosta.kdc.model.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ReplyBoardDAO replyDAO;
    
    @Autowired
    private NoticeBoardDAO noticeDAO;
    
    @Override
    public Map<String, Integer> boardQuantityByClassification() {
        
        String tech = "tech";                   //reply tech Q&A
        String lib = "lib";                     //reply ��� ����
        String study = "study";                 //reply ���͵� ����
        
        String findJobNotice = "findJobNotice";//notice Board ä��Խ���
        
        //���� ���� ��.
        Map<String, Integer> map = new HashMap<>();
        
        //reply ���� �Խ��� �� ��������
        map.put("tech", replyDAO.boardQuantityByClassification(tech));
        map.put("lib", replyDAO.boardQuantityByClassification(lib));
        map.put("study", replyDAO.boardQuantityByClassification(study));
        
        //notice ���� �Խ��� �� ��������
        map.put("findJob", noticeDAO.boardQuantityByClassification(findJobNotice));
        
        return map;
    }

}
