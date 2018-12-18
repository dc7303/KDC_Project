package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioDAO {
    // 삽입
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // 조회
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // 수정
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // 삭제
    int deletePortfolioByMemberId(String memberId);

    
}
