package edu.kosta.kdc.model.dao;

import edu.kosta.kdc.model.dto.PortfolioDTO;

public interface PortfolioDAO {
    //����
    int insertPortfolio(PortfolioDTO portfolioDTO);
    
    //��ȸ
    PortfolioDTO selectPortfolioByMemberId(String memberId);
    
    //����
    int updatePortfolio(PortfolioDTO portfolioDTO);
    
    //����
    int deletePortfolioByMemberId(String memberId);
}
