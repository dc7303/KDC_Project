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
    
    // �� ����
    @Override
    public int insertDetail(PortfolioDetailDTO portfolioDetailDTO) {
        int result = sqlSession.insert("portfolioDetailMapper.insertDetail", portfolioDetailDTO);
        return result;
    }

    // �󼼸� ��ȸ(��Ʈ������ ������, by memberID)
    @Override
    public List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId) {
        return sqlSession.selectList("portfolioDetailMapper.selectDetailsByMemberId", memberId);
    }

    // �� ����
    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO) {
        return sqlSession.update("portfolioDetailMapper.updateDetail", portfolioDetailDTO);
    }

    // �� ����
    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        return sqlSession.update("portfolioDetailMapper.deleteDetail", PortfolioDetailPk);
    }

    // �ؽ��±� ����
    @Override
    public int insertHashTag(String hashTagName) {  
        return sqlSession.insert("portfolioDetailMapper.insertHashTag", hashTagName);
    }
    
    // �ؽ��±� ����
    @Override
    public int deleteHashTag(int detailPk) {
        return sqlSession.update("portfolioDetailMapper.deleteHashTag", detailPk);
    }
    
    // �� ��ȸ (by detailPk)
    @Override
    public PortfolioDetailDTO selectDetailByPk(int detailPk) {
        return sqlSession.selectOne("portfolioDetailMapper.selectDetailByPk", detailPk);
    }


}
