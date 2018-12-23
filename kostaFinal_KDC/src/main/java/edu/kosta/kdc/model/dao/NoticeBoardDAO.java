package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
    /**
     *  ��ü�˻�
     */
    List<NoticeBoardDTO> selectAll (NoticeBoardDTO noticeBoard);
    
    /**
     * ���ڵ� ����
     */
    int insert(NoticeBoardDTO noticeBoard);
    
    /**
     * ���� �˻��ؼ� �󼼺���
     */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK);
    
    /**
     *  �����ϱ�
     */
    void update(NoticeBoardDTO noticeBoard);
    
    /**
     * �����ϱ�
     */
    void delete(int noticeBoardPk);
    
    /**
     *  ��ȸ�� ����
     */
    int noticeViewsUpdate(int noticeBoardPk);

    /**
     *  ���ǰ˻�
     */
    List<NoticeBoardDTO> SelechSerch(String department, String noticeBoardSearch);

    

    



   

 
    
    
  
    
  
   

    

    
 

}
