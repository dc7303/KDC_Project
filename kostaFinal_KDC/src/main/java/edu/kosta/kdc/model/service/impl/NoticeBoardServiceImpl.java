package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;
import edu.kosta.kdc.exception.KdcException;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    @Autowired
    private NoticeBoardDAO noticeBoardDAO;
    
    /**
     *  ��ü�˻�
     */
    @Override
    public List<NoticeBoardDTO> selectAll(NoticeBoardDTO noticeBoard, boolean state) {
     
        List<NoticeBoardDTO> list = noticeBoardDAO.selectAll(noticeBoard);
        if(list == null) {
            throw new KdcException("�Խñ��� ���������ʽ��ϴ�.");
        }
        
        if(state) {
            int views = noticeBoardDAO.noticeViewsUpdate(noticeBoard.getNoticeBoardPk());
            if(views==0)throw new KdcException("��ȸ�� ���� �����Դϴ�.");
        }
        return noticeBoardDAO.selectAll(noticeBoard);
    }
 
    /**
     *  ���ڵ� ����
     */
    @Override
    public int insert(NoticeBoardDTO noticeBoard) {
        return noticeBoardDAO.insert(noticeBoard);
        
    }
    
    /**
     *  ���� �����ؼ� �󼼺���
     */
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPk, boolean state) {
        if(state){
            int views = noticeBoardDAO.noticeViewsUpdate(noticeBoardPk);//��ȸ������
            if(views==0) throw new KdcException("��ȸ�� ���� �����Դϴ�.");
        }
       
        return noticeBoardDAO.selectByNoticeBoardTitle(noticeBoardPk);
    }
    
    /**
     * �����ϱ�
     */
    @Override
    public int update(NoticeBoardDTO noticeBoard) {
        return noticeBoardDAO.update(noticeBoard);
    }
    
    /**
     *  �����ϱ�
     */
    @Override
    public int delete(int noticeBoardPk) {
        return noticeBoardDAO.delete(noticeBoardPk);
    }

    /**
     *   �˻� ����Ʈ
     */
    @Override
    public List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch) {
     
        return noticeBoardDAO.SelechSerch(department, noticeBoardSearch);
    }

 }
