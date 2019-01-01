package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
    /**
     *  전체검색
     */
    List<NoticeBoardDTO> selectAll (String classification, String classRoomCode);
    
    /**
     * 레코드 삽입
     */
    int noticeInsert(NoticeBoardDTO noticeBoard);
    
    /**
     * 제목 검색해서 상세보기
     */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK);
    
    /**
     *  수정하기
     */
    int update(NoticeBoardDTO noticeBoard);
    
    /**
     * 삭제하기
     */
    int delete(int noticeBoardPk);
    
    /**
     *  조회수 증가
     */
    int noticeViewsUpdate(int noticeBoardPk);

    /**
     *  조건검색
     */
    List<NoticeBoardDTO> SelechSerch(String department, String noticeBoardSearch, String classification);

    /**
     * classification 기준으로 컬럼 수량 가져오기.
     *
     * @param classification
     * @return
     */
    public int boardQuantityByClassification(String classification);
     
     /**
     * 메인 페이지에 띄울 공지사항 글 5개 가져오는 메소드
     * 
     * @param
     * @return List<NoticeBoardDTO>
     * */
    List<NoticeBoardDTO> selectFive();

}
