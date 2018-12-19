package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId) {
        return sqlSession.selectList("portfolioDetailMapper.selectDetailsByMemberId", memberId);
    }

    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO) {
        return sqlSession.update("portfolioDetailMapper.updateDetail", portfolioDetailDTO);
    }

    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertHashTag(String hashTagName) {  
        return sqlSession.insert("portfolioDetailMapper.insertHashTag", hashTagName);
    }
    
    @Override
    public int deleteHashTag(int detailPk) {
        return sqlSession.delete("portfolioDetailMapper.deleteHashTag", detailPk);
    }
    

    @Override
    public PortfolioDetailDTO selectDetailByPk(int detailPk) {
        return sqlSession.selectOne("portfolioDetailMapper.selectDetailByPk", detailPk);
    }


}
