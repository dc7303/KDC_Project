package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioDAO {
    // 삽입
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // 조회(id에 해당하는 포트폴리오)
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // 게시된 모든 포트폴리오 조회
    List<PortfolioDTO> selectAll();

    // 수정
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // 삭제
    int deletePortfolioByMemberId(String memberId);

    // 포트폴리오 조회(상세포함)
    PortfolioDTO selectAllDetail(String memberId);

    // 조건으로 검색
    List<PortfolioDTO> selectByKeyword(String keyfield, String keyword);
}
