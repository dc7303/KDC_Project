package edu.kosta.kdc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardService {
    /**
     *  ��ü�˻�
     */
    List<NoticeBoardDTO>selectAll(NoticeBoardDTO noticeBoard, boolean state);
    
    /**
     *  ���ڵ� ����
     */
     int insert(NoticeBoardDTO noticeBoard);
    
     /**
      *  ���� �����ؼ� �󼼺���
      */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK, boolean state);
    
    /**
     *  �����ϱ�
     */
    void update(NoticeBoardDTO noticeBoard);

    /**
     * �����ϱ�
     */
    void delete(int noticeBoardPk);
    
    /**
     *  ���ǰ˻�
     */
    List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch);




  

    
  
    
    

 

  

}
