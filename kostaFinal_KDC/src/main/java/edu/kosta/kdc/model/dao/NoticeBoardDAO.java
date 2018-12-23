package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
    /**
     *  전체검색
     */
    List<NoticeBoardDTO> selectAll (NoticeBoardDTO noticeBoard);
    
    /**
     * 레코드 삽입
     */
    int insert(NoticeBoardDTO noticeBoard);
    
    /**
     * 제목 검색해서 상세보기
     */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK);
    
    /**
     *  수정하기
     */
    void update(NoticeBoardDTO noticeBoard);
    
    /**
     * 삭제하기
     */
    void delete(int noticeBoardPk);
    
    /**
     *  조회수 증가
     */
    int noticeViewsUpdate(int noticeBoardPk);

    /**
     *  조건검색
     */
    List<NoticeBoardDTO> SelechSerch(String department, String noticeBoardSearch);

    

    



   

 
    
    
  
    
  
   

    

    
 

}
