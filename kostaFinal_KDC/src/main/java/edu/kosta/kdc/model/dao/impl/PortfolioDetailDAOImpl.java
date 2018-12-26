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
    
    // 상세 삽입
    @Override
    public int insertDetail(PortfolioDetailDTO portfolioDetailDTO) {
        int result = sqlSession.insert("portfolioDetailMapper.insertDetail", portfolioDetailDTO);
        return result;
    }

    // 상세만 조회(포트폴리오 미포함, by memberID)
    @Override
    public List<PortfolioDetailDTO> selectDetailsByMemberId(String memberId) {
        return sqlSession.selectList("portfolioDetailMapper.selectDetailsByMemberId", memberId);
    }

    // 상세 수정
    @Override
    public int updateDetail(PortfolioDetailDTO portfolioDetailDTO) {
        return sqlSession.update("portfolioDetailMapper.updateDetail", portfolioDetailDTO);
    }

    // 상세 삭제
    @Override
    public int deleteDetail(int PortfolioDetailPk) {
        return sqlSession.update("portfolioDetailMapper.deleteDetail", PortfolioDetailPk);
    }

    // 해쉬태그 생성
    @Override
    public int insertHashTag(String hashTagName) {  
        return sqlSession.insert("portfolioDetailMapper.insertHashTag", hashTagName);
    }
    
    // 해쉬태그 삭제
    @Override
    public int deleteHashTag(int detailPk) {
        return sqlSession.update("portfolioDetailMapper.deleteHashTag", detailPk);
    }
    
    // 상세 조회 (by detailPk)
    @Override
    public PortfolioDetailDTO selectDetailByPk(int detailPk) {
        return sqlSession.selectOne("portfolioDetailMapper.selectDetailByPk", detailPk);
    }


}
