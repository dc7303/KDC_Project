package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioDetailDAO {
    // 상세 삽입
    int insertDetail(PortfolioDetailDTO portfolioDetailDTO);

    // 상세 조회(by memberid)
    List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId);

    // 상세 수정
    int updateDetail(PortfolioDetailDTO portfolioDetailDTO);

    // 상세 삭제(by PortfolioDetail_pk)
    int deleteDetail(int PortfolioDetailPk);
    
    // 해쉬태그 삽입
    int insertHashTag(String hashTagName);
    
    // 상세 조회(by pk)
    PortfolioDetailDTO selectDetailByPk(int detailPk);
}
