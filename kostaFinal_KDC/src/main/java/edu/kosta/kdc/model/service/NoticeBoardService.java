package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardService {
    /**
     * 전체검색
     */
    List<NoticeBoardDTO> selectAll(String classification);

    /**
     * 레코드 삽입
     */
    int insert(NoticeBoardDTO noticeBoard);

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
    List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch);

}
