package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

public interface PortfolioDAO {
    // ����
    int insertPortfolio(PortfolioDTO portfolioDTO);

    // ��ȸ
    PortfolioDTO selectPortfolioByMemberId(String memberId);

    // ����
    int updatePortfolio(PortfolioDTO portfolioDTO);

    // ����
    int deletePortfolioByMemberId(String memberId);

    
}
