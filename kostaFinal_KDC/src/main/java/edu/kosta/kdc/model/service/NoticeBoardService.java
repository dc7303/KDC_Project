package edu.kosta.kdc.model.service;

import java.util.List;
import java.util.Map;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardService {
    /**
     * 전체검색
     */
    Map<String, Object> selectAll(String classification, int pageNum);

    /**
     * 레코드 삽입
     */
    int noticeInsert(NoticeBoardDTO noticeBoard, String classification);

    /**
     * 제목 선택해서 상세보기
     */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK, boolean state);

    /**
     * 수정하기
     */
    int update(NoticeBoardDTO noticeBoard);

    /**
     * 삭제하기
     */
    int delete(int noticeBoardPk);

    /**
     * 조건검색
     */
    List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch, String classification);

    /**
     * 메인 페이지에 띄울 공지사항 글 5개 가져오는 메소드
     * */
    List<NoticeBoardDTO> selectFive();
    
    /**
     * 페이징 count 수량
     * 
     * @param department
     * @param noticeBoardSearch
     * @param classification
     * @return
     */
    int selectNoticePagingCount(String department, String noticeBoardSearch, String classification);

    /**
     * notice 페이징 select
     * 
     * @param map
     * @return
     */
    Map<String, Object> selectNoticePaging(String department, String noticeBoardSearch, String classification, int pageNum);
    
}
