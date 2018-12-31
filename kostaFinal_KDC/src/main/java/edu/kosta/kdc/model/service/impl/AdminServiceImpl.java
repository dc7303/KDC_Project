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
    
    /**
     * �湮�� �� �����ϴ� �޼ҵ�
     * */
    @Override
    public int userCountIntoDB(int todayUserCount) {
        
        return adminDAO.userCountIntoDB(todayUserCount);

    }

    /**
     * �湮�� �� �������� �޼ҵ� (�ֱ� 5��) 
     * */
    @Override
    public List<VisitDTO> visitNumListSelect() {
        
        return adminDAO.visitNumListSelect();
    }

}
