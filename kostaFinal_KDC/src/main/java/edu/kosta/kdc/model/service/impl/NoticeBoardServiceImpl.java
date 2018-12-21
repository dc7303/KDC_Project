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
            if(views == 0)throw new KdcException("��ȸ�� ���� �����Դϴ�.");
        }
        
        return list;
    }
 
    /**
     *  ���ڵ� ����
     */
    @Override
    public int insert(NoticeBoardDTO noticeBoard) {
        
        int result = 0;
        //�Խñ� Insert
        result = noticeBoardDAO.insert(noticeBoard);
        if(result == 0) {
            throw new KdcException("�ۼ� �����Դϴ�.");
        }
        
        return result;
        
    }
    
    /**
     *  ���� �����ؼ� �󼼺���
     */
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPk, boolean state) {
        
        //�Խñ� ��ȸ 
        NoticeBoardDTO noticeDTO = noticeBoardDAO.selectByNoticeBoardTitle(noticeBoardPk);
        if(noticeDTO == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        //��ȸ�� ����
        if(state){
            int views = noticeBoardDAO.noticeViewsUpdate(noticeBoardPk);//��ȸ������
            if(views == 0) throw new KdcException("��ȸ�� ���� �����Դϴ�.");
        }
       
        return noticeDTO;
    }
    
    /**
     * �����ϱ�
     */
    @Override
    public int update(NoticeBoardDTO noticeBoard) {
        
        int result = 0;
        //�Խñ� update
        result = noticeBoardDAO.update(noticeBoard);
        if(result == 0) {
            throw new KdcException("������Ʈ �����Դϴ�.");
        }
        
        return result;
    }
    
    /**
     *  �����ϱ�
     */
    @Override
    public int delete(int noticeBoardPk) {
        
        int result = 0;
        //�Խñ� delte
        result = noticeBoardDAO.delete(noticeBoardPk);
        if(result == 0) {
            throw new KdcException("���� �����Դϴ�.");
        }
        
        return result;
    }

    /**
     *   �˻� ����Ʈ
     */
    @Override
    public List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch) {
     
        //�Խñ� ��ȸ
        List<NoticeBoardDTO> list = noticeBoardDAO.SelechSerch(department, noticeBoardSearch);
        if(list == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        return list;
    }

 }
