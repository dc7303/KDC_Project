package edu.kosta.kdc.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.PortfolioDAO;
import edu.kosta.kdc.model.dto.PortfolioDTO;

@Service
public class PortfolioServiceImpl implements edu.kosta.kdc.model.service.PortfolioService {

    @Autowired 
    private PortfolioDAO dao;
    
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        return dao.insertPortfolio(portfolioDTO);
    }
    

    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deletePortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public boolean selectByMemberId(String memberId) {
        return dao.selectByMemberId(memberId);
    }


}
