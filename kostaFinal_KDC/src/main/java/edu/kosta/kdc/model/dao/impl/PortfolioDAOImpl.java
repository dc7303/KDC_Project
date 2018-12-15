package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dto.PortfolioDTO;

@Repository
public class PortfolioDAOImpl implements edu.kosta.kdc.model.dao.PortfolioDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 포트폴리오 생성
     * */
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        int result = sqlSession.insert("portfolioMapper.insertPortfolio", portfolioDTO);
        return result;
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

}
