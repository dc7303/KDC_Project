package edu.kosta.kdc.model.dao;

import edu.kosta.kdc.model.dto.PortfolioDTO;

public interface PortfolioDAO {
    //삽입
    int insertPortfolio(PortfolioDTO portfolioDTO);
    
    //조회
    PortfolioDTO selectPortfolioByMemberId(String memberId);
    
    //수정
    int updatePortfolio(PortfolioDTO portfolioDTO);
    
    //삭제
    int deletePortfolioByMemberId(String memberId);
}
