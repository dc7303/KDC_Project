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
     *  전체검색
     */
    List<NoticeBoardDTO>selectAll(NoticeBoardDTO noticeBoard, boolean state);
    
    /**
     *  레코드 삽입
     */
     int insert(NoticeBoardDTO noticeBoard);
    
     /**
      *  제목 선택해서 상세보기
      */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK, boolean state);
    
    /**
     *  수정하기
     */
    void update(NoticeBoardDTO noticeBoard);

    /**
     * 삭제하기
     */
    void delete(int noticeBoardPk);
    
    /**
     *  조건검색
     */
    List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch);




  

    
  
    
    

 

  

}
