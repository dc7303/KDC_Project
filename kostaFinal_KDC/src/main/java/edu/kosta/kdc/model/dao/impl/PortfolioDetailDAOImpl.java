package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.PortfolioDetailDAO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

@Repository
public class PortfolioDetailDAOImpl implements PortfolioDetailDAO {

    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public int insertDetail(PortfolioDetailDTO portfolioDetailDTO) {
        int result = sqlSession.insert("portfolioDetailMapper.insertDetail", portfolioDetailDTO);
        return result;
    }

    @Override
    public List<PortfolioDetailDTO> selectDetailByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        // TODO Auto-generated method stub
        return 0;
    }

}
