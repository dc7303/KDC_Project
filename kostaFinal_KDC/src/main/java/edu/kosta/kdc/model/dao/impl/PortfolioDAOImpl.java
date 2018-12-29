package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dto.PortfolioDTO;
import edu.kosta.kdc.model.dto.PortfolioDetailDTO;

@Repository
public class PortfolioDAOImpl implements edu.kosta.kdc.model.dao.PortfolioDAO {

    @Autowired
    private SqlSession sqlSession;
    
    // 포트폴리오 생성
    @Override
    public int insertPortfolio(PortfolioDTO portfolioDTO) {
        int result = sqlSession.insert("portfolioMapper.insertPortfolio", portfolioDTO);   
        return result;
    }

    //포트폴리오 조회
    @Override
    public PortfolioDTO selectPortfolioByMemberId(String memberId) {
        return sqlSession.selectOne("portfolioMapper.selectPortfolioByMemberId", memberId);
    }

    // 포트폴리오 수정
    @Override
    public int updatePortfolio(PortfolioDTO portfolioDTO) {
        return sqlSession.update("portfolioMapper.updatePortfolio", portfolioDTO);
    }

    // 삭제(미구현)
    @Override
    public int deletePortfolioByMemberId(String memberId) {
        // TODO Auto-generated method stub
        return 0;
    } 

    // 게시된 모든 포트폴리오 조회
    @Override
    public List<PortfolioDTO> selectAll() {
        return sqlSession.selectList("portfolioMapper.selectAll");
    }

    // 포트폴리오 조회(상세포함)
    @Override
    public PortfolioDTO selectAllDetail(String memberId) {
        return sqlSession.selectOne("portfolioMapper.selectAllDetail", memberId);
    }

    //분류별 키워드 검색
    @Override
    public List<PortfolioDTO> selectByKeyword(String keyfield, String keyword) {
        Map<String, String> map = new HashMap<>();
        map.put("keyfield", keyfield);
        map.put("keyword", keyword);
        return sqlSession.selectList("portfolioMapper.selectByKeyword", map);
    }

    

}
